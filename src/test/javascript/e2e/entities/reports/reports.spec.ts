/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ReportsComponentsPage from './reports.page-object';
import { ReportsDeleteDialog } from './reports.page-object';
import ReportsUpdatePage from './reports-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Reports e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let reportsUpdatePage: ReportsUpdatePage;
  let reportsComponentsPage: ReportsComponentsPage;
  let reportsDeleteDialog: ReportsDeleteDialog;

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

  it('should load Reports', async () => {
    await navBarPage.getEntityPage('reports');
    reportsComponentsPage = new ReportsComponentsPage();
    expect(await reportsComponentsPage.getTitle().getText()).to.match(/Reports/);
  });

  it('should load create Reports page', async () => {
    await reportsComponentsPage.clickOnCreateButton();
    reportsUpdatePage = new ReportsUpdatePage();
    expect(await reportsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Reports/);
  });

  it('should create and save Reports', async () => {
    const nbButtonsBeforeCreate = await reportsComponentsPage.countDeleteButtons();

    await reportsUpdatePage.setDescInput('desc');
    expect(await reportsUpdatePage.getDescInput()).to.match(/desc/);
    await waitUntilDisplayed(reportsUpdatePage.getSaveButton());
    await reportsUpdatePage.save();
    await waitUntilHidden(reportsUpdatePage.getSaveButton());
    expect(await reportsUpdatePage.getSaveButton().isPresent()).to.be.false;

    await reportsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await reportsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Reports', async () => {
    await reportsComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await reportsComponentsPage.countDeleteButtons();
    await reportsComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    reportsDeleteDialog = new ReportsDeleteDialog();
    expect(await reportsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.reports.delete.question/);
    await reportsDeleteDialog.clickOnConfirmButton();

    await reportsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await reportsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
