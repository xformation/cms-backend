/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PeriodsComponentsPage from './periods.page-object';
import { PeriodsDeleteDialog } from './periods.page-object';
import PeriodsUpdatePage from './periods-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Periods e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let periodsUpdatePage: PeriodsUpdatePage;
  let periodsComponentsPage: PeriodsComponentsPage;
  let periodsDeleteDialog: PeriodsDeleteDialog;

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

  it('should load Periods', async () => {
    await navBarPage.getEntityPage('periods');
    periodsComponentsPage = new PeriodsComponentsPage();
    expect(await periodsComponentsPage.getTitle().getText()).to.match(/Periods/);
  });

  it('should load create Periods page', async () => {
    await periodsComponentsPage.clickOnCreateButton();
    periodsUpdatePage = new PeriodsUpdatePage();
    expect(await periodsUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.periods.home.createOrEditLabel/);
  });

  it('should create and save Periods', async () => {
    const nbButtonsBeforeCreate = await periodsComponentsPage.countDeleteButtons();

    await periodsUpdatePage.periodsSelectLastOption();
    await periodsUpdatePage.sectionSelectLastOption();
    await waitUntilDisplayed(periodsUpdatePage.getSaveButton());
    await periodsUpdatePage.save();
    await waitUntilHidden(periodsUpdatePage.getSaveButton());
    expect(await periodsUpdatePage.getSaveButton().isPresent()).to.be.false;

    await periodsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await periodsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Periods', async () => {
    await periodsComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await periodsComponentsPage.countDeleteButtons();
    await periodsComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    periodsDeleteDialog = new PeriodsDeleteDialog();
    expect(await periodsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.periods.delete.question/);
    await periodsDeleteDialog.clickOnConfirmButton();

    await periodsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await periodsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
