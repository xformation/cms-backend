/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DueDateComponentsPage from './due-date.page-object';
import { DueDateDeleteDialog } from './due-date.page-object';
import DueDateUpdatePage from './due-date-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('DueDate e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dueDateUpdatePage: DueDateUpdatePage;
  let dueDateComponentsPage: DueDateComponentsPage;
  let dueDateDeleteDialog: DueDateDeleteDialog;

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

  it('should load DueDates', async () => {
    await navBarPage.getEntityPage('due-date');
    dueDateComponentsPage = new DueDateComponentsPage();
    expect(await dueDateComponentsPage.getTitle().getText()).to.match(/Due Dates/);
  });

  it('should load create DueDate page', async () => {
    await dueDateComponentsPage.clickOnCreateButton();
    dueDateUpdatePage = new DueDateUpdatePage();
    expect(await dueDateUpdatePage.getPageTitle().getText()).to.match(/Create or edit a DueDate/);
  });

  it('should create and save DueDates', async () => {
    const nbButtonsBeforeCreate = await dueDateComponentsPage.countDeleteButtons();

    await dueDateUpdatePage.setPaymentMethodInput('paymentMethod');
    expect(await dueDateUpdatePage.getPaymentMethodInput()).to.match(/paymentMethod/);
    await dueDateUpdatePage.setInstallmentsInput('5');
    expect(await dueDateUpdatePage.getInstallmentsInput()).to.eq('5');
    await dueDateUpdatePage.setDayDescInput('dayDesc');
    expect(await dueDateUpdatePage.getDayDescInput()).to.match(/dayDesc/);
    await dueDateUpdatePage.frequencySelectLastOption();
    await dueDateUpdatePage.collegeSelectLastOption();
    await dueDateUpdatePage.branchSelectLastOption();
    await waitUntilDisplayed(dueDateUpdatePage.getSaveButton());
    await dueDateUpdatePage.save();
    await waitUntilHidden(dueDateUpdatePage.getSaveButton());
    expect(await dueDateUpdatePage.getSaveButton().isPresent()).to.be.false;

    await dueDateComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await dueDateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last DueDate', async () => {
    await dueDateComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await dueDateComponentsPage.countDeleteButtons();
    await dueDateComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    dueDateDeleteDialog = new DueDateDeleteDialog();
    expect(await dueDateDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.dueDate.delete.question/);
    await dueDateDeleteDialog.clickOnConfirmButton();

    await dueDateComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await dueDateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
