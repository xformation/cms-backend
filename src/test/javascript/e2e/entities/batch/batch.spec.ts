/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BatchComponentsPage from './batch.page-object';
import { BatchDeleteDialog } from './batch.page-object';
import BatchUpdatePage from './batch-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Batch e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let batchUpdatePage: BatchUpdatePage;
  let batchComponentsPage: BatchComponentsPage;
  let batchDeleteDialog: BatchDeleteDialog;

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

  it('should load Batches', async () => {
    await navBarPage.getEntityPage('batch');
    batchComponentsPage = new BatchComponentsPage();
    expect(await batchComponentsPage.getTitle().getText()).to.match(/Batches/);
  });

  it('should load create Batch page', async () => {
    await batchComponentsPage.clickOnCreateButton();
    batchUpdatePage = new BatchUpdatePage();
    expect(await batchUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Batch/);
  });

  it('should create and save Batches', async () => {
    const nbButtonsBeforeCreate = await batchComponentsPage.countDeleteButtons();

    await batchUpdatePage.batchSelectLastOption();
    await batchUpdatePage.departmentSelectLastOption();
    await waitUntilDisplayed(batchUpdatePage.getSaveButton());
    await batchUpdatePage.save();
    await waitUntilHidden(batchUpdatePage.getSaveButton());
    expect(await batchUpdatePage.getSaveButton().isPresent()).to.be.false;

    await batchComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await batchComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Batch', async () => {
    await batchComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await batchComponentsPage.countDeleteButtons();
    await batchComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    batchDeleteDialog = new BatchDeleteDialog();
    expect(await batchDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.batch.delete.question/);
    await batchDeleteDialog.clickOnConfirmButton();

    await batchComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await batchComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
