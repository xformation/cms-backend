/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AuthorizedSignatoryComponentsPage from './authorized-signatory.page-object';
import { AuthorizedSignatoryDeleteDialog } from './authorized-signatory.page-object';
import AuthorizedSignatoryUpdatePage from './authorized-signatory-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AuthorizedSignatory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let authorizedSignatoryUpdatePage: AuthorizedSignatoryUpdatePage;
  let authorizedSignatoryComponentsPage: AuthorizedSignatoryComponentsPage;
  let authorizedSignatoryDeleteDialog: AuthorizedSignatoryDeleteDialog;

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

  it('should load AuthorizedSignatories', async () => {
    await navBarPage.getEntityPage('authorized-signatory');
    authorizedSignatoryComponentsPage = new AuthorizedSignatoryComponentsPage();
    expect(await authorizedSignatoryComponentsPage.getTitle().getText()).to.match(/Authorized Signatories/);
  });

  it('should load create AuthorizedSignatory page', async () => {
    await authorizedSignatoryComponentsPage.clickOnCreateButton();
    authorizedSignatoryUpdatePage = new AuthorizedSignatoryUpdatePage();
    expect(await authorizedSignatoryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AuthorizedSignatory/);
  });

  it('should create and save AuthorizedSignatories', async () => {
    const nbButtonsBeforeCreate = await authorizedSignatoryComponentsPage.countDeleteButtons();

    await authorizedSignatoryUpdatePage.setSignatoryNameInput('signatoryName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryNameInput()).to.match(/signatoryName/);
    await authorizedSignatoryUpdatePage.setSignatoryFatherNameInput('signatoryFatherName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryFatherNameInput()).to.match(/signatoryFatherName/);
    await authorizedSignatoryUpdatePage.setSignatoryDesignationInput('signatoryDesignation');
    expect(await authorizedSignatoryUpdatePage.getSignatoryDesignationInput()).to.match(/signatoryDesignation/);
    await authorizedSignatoryUpdatePage.setAddressInput('address');
    expect(await authorizedSignatoryUpdatePage.getAddressInput()).to.match(/address/);
    await authorizedSignatoryUpdatePage.setEmailInput('email');
    expect(await authorizedSignatoryUpdatePage.getEmailInput()).to.match(/email/);
    await authorizedSignatoryUpdatePage.setPanCardNumberInput('panCardNumber');
    expect(await authorizedSignatoryUpdatePage.getPanCardNumberInput()).to.match(/panCardNumber/);
    await authorizedSignatoryUpdatePage.branchSelectLastOption();
    await authorizedSignatoryUpdatePage.collegeSelectLastOption();
    await waitUntilDisplayed(authorizedSignatoryUpdatePage.getSaveButton());
    await authorizedSignatoryUpdatePage.save();
    await waitUntilHidden(authorizedSignatoryUpdatePage.getSaveButton());
    expect(await authorizedSignatoryUpdatePage.getSaveButton().isPresent()).to.be.false;

    await authorizedSignatoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await authorizedSignatoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AuthorizedSignatory', async () => {
    await authorizedSignatoryComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await authorizedSignatoryComponentsPage.countDeleteButtons();
    await authorizedSignatoryComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    authorizedSignatoryDeleteDialog = new AuthorizedSignatoryDeleteDialog();
    expect(await authorizedSignatoryDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /cmsApp.authorizedSignatory.delete.question/
    );
    await authorizedSignatoryDeleteDialog.clickOnConfirmButton();

    await authorizedSignatoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await authorizedSignatoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
