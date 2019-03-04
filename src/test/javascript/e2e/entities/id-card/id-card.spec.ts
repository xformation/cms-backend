/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import IdCardComponentsPage from './id-card.page-object';
import { IdCardDeleteDialog } from './id-card.page-object';
import IdCardUpdatePage from './id-card-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('IdCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let idCardUpdatePage: IdCardUpdatePage;
  let idCardComponentsPage: IdCardComponentsPage;
  let idCardDeleteDialog: IdCardDeleteDialog;

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

  it('should load IdCards', async () => {
    await navBarPage.getEntityPage('id-card');
    idCardComponentsPage = new IdCardComponentsPage();
    expect(await idCardComponentsPage.getTitle().getText()).to.match(/Id Cards/);
  });

  it('should load create IdCard page', async () => {
    await idCardComponentsPage.clickOnCreateButton();
    idCardUpdatePage = new IdCardUpdatePage();
    expect(await idCardUpdatePage.getPageTitle().getText()).to.match(/Create or edit a IdCard/);
  });

  it('should create and save IdCards', async () => {
    const nbButtonsBeforeCreate = await idCardComponentsPage.countDeleteButtons();

    await idCardUpdatePage.setDescInput('desc');
    expect(await idCardUpdatePage.getDescInput()).to.match(/desc/);
    await waitUntilDisplayed(idCardUpdatePage.getSaveButton());
    await idCardUpdatePage.save();
    await waitUntilHidden(idCardUpdatePage.getSaveButton());
    expect(await idCardUpdatePage.getSaveButton().isPresent()).to.be.false;

    await idCardComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await idCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last IdCard', async () => {
    await idCardComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await idCardComponentsPage.countDeleteButtons();
    await idCardComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    idCardDeleteDialog = new IdCardDeleteDialog();
    expect(await idCardDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.idCard.delete.question/);
    await idCardDeleteDialog.clickOnConfirmButton();

    await idCardComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await idCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
