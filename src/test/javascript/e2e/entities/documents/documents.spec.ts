/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DocumentsComponentsPage from './documents.page-object';
import { DocumentsDeleteDialog } from './documents.page-object';
import DocumentsUpdatePage from './documents-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Documents e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let documentsUpdatePage: DocumentsUpdatePage;
  let documentsComponentsPage: DocumentsComponentsPage;
  let documentsDeleteDialog: DocumentsDeleteDialog;

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

  it('should load Documents', async () => {
    await navBarPage.getEntityPage('documents');
    documentsComponentsPage = new DocumentsComponentsPage();
    expect(await documentsComponentsPage.getTitle().getText()).to.match(/Documents/);
  });

  it('should load create Documents page', async () => {
    await documentsComponentsPage.clickOnCreateButton();
    documentsUpdatePage = new DocumentsUpdatePage();
    expect(await documentsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Documents/);
  });

  it('should create and save Documents', async () => {
    const nbButtonsBeforeCreate = await documentsComponentsPage.countDeleteButtons();

    await documentsUpdatePage.setDescInput('desc');
    expect(await documentsUpdatePage.getDescInput()).to.match(/desc/);
    await waitUntilDisplayed(documentsUpdatePage.getSaveButton());
    await documentsUpdatePage.save();
    await waitUntilHidden(documentsUpdatePage.getSaveButton());
    expect(await documentsUpdatePage.getSaveButton().isPresent()).to.be.false;

    await documentsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await documentsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Documents', async () => {
    await documentsComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await documentsComponentsPage.countDeleteButtons();
    await documentsComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    documentsDeleteDialog = new DocumentsDeleteDialog();
    expect(await documentsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.documents.delete.question/);
    await documentsDeleteDialog.clickOnConfirmButton();

    await documentsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await documentsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
