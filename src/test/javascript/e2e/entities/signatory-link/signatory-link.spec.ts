/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SignatoryLinkComponentsPage from './signatory-link.page-object';
import { SignatoryLinkDeleteDialog } from './signatory-link.page-object';
import SignatoryLinkUpdatePage from './signatory-link-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('SignatoryLink e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let signatoryLinkUpdatePage: SignatoryLinkUpdatePage;
  let signatoryLinkComponentsPage: SignatoryLinkComponentsPage;
  let signatoryLinkDeleteDialog: SignatoryLinkDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();

    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load SignatoryLinks', async () => {
    await navBarPage.getEntityPage('signatory-link');
    signatoryLinkComponentsPage = new SignatoryLinkComponentsPage();
    expect(await signatoryLinkComponentsPage.getTitle().getText()).to.match(/Signatory Links/);
  });

  it('should load create SignatoryLink page', async () => {
    await signatoryLinkComponentsPage.clickOnCreateButton();
    signatoryLinkUpdatePage = new SignatoryLinkUpdatePage();
    expect(await signatoryLinkUpdatePage.getPageTitle().getText()).to.match(/Create or edit a SignatoryLink/);
  });

  it('should create and save SignatoryLinks', async () => {
    const nbButtonsBeforeCreate = await signatoryLinkComponentsPage.countDeleteButtons();

    await signatoryLinkUpdatePage.setDescInput('desc');
    expect(await signatoryLinkUpdatePage.getDescInput()).to.match(/desc/);
    await signatoryLinkUpdatePage.authorizedSignatorySelectLastOption();
    await signatoryLinkUpdatePage.legalEntitySelectLastOption();
    await waitUntilDisplayed(signatoryLinkUpdatePage.getSaveButton());
    await signatoryLinkUpdatePage.save();
    await waitUntilHidden(signatoryLinkUpdatePage.getSaveButton());
    expect(await signatoryLinkUpdatePage.getSaveButton().isPresent()).to.be.false;

    await signatoryLinkComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await signatoryLinkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last SignatoryLink', async () => {
    await signatoryLinkComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await signatoryLinkComponentsPage.countDeleteButtons();
    await signatoryLinkComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    signatoryLinkDeleteDialog = new SignatoryLinkDeleteDialog();
    expect(await signatoryLinkDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.signatoryLink.delete.question/);
    await signatoryLinkDeleteDialog.clickOnConfirmButton();

    await signatoryLinkComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await signatoryLinkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
