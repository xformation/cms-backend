/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import HolidayComponentsPage from './holiday.page-object';
import { HolidayDeleteDialog } from './holiday.page-object';
import HolidayUpdatePage from './holiday-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Holiday e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let holidayUpdatePage: HolidayUpdatePage;
  let holidayComponentsPage: HolidayComponentsPage;
  let holidayDeleteDialog: HolidayDeleteDialog;

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

  it('should load Holidays', async () => {
    await navBarPage.getEntityPage('holiday');
    holidayComponentsPage = new HolidayComponentsPage();
    expect(await holidayComponentsPage.getTitle().getText()).to.match(/Holidays/);
  });

  it('should load create Holiday page', async () => {
    await holidayComponentsPage.clickOnCreateButton();
    holidayUpdatePage = new HolidayUpdatePage();
    expect(await holidayUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Holiday/);
  });

  it('should create and save Holidays', async () => {
    const nbButtonsBeforeCreate = await holidayComponentsPage.countDeleteButtons();

    await holidayUpdatePage.setHolidayDescInput('holidayDesc');
    expect(await holidayUpdatePage.getHolidayDescInput()).to.match(/holidayDesc/);
    await holidayUpdatePage.setHolidayDateInput('01-01-2001');
    expect(await holidayUpdatePage.getHolidayDateInput()).to.eq('2001-01-01');
    await holidayUpdatePage.holidayStatusSelectLastOption();
    await holidayUpdatePage.academicyearSelectLastOption();
    await waitUntilDisplayed(holidayUpdatePage.getSaveButton());
    await holidayUpdatePage.save();
    await waitUntilHidden(holidayUpdatePage.getSaveButton());
    expect(await holidayUpdatePage.getSaveButton().isPresent()).to.be.false;

    await holidayComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await holidayComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Holiday', async () => {
    await holidayComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await holidayComponentsPage.countDeleteButtons();
    await holidayComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    holidayDeleteDialog = new HolidayDeleteDialog();
    expect(await holidayDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.holiday.delete.question/);
    await holidayDeleteDialog.clickOnConfirmButton();

    await holidayComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await holidayComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
