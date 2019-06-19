/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FacilityComponentsPage from './facility.page-object';
import { FacilityDeleteDialog } from './facility.page-object';
import FacilityUpdatePage from './facility-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Facility e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let facilityUpdatePage: FacilityUpdatePage;
  let facilityComponentsPage: FacilityComponentsPage;
  let facilityDeleteDialog: FacilityDeleteDialog;

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

  it('should load Facilities', async () => {
    await navBarPage.getEntityPage('facility');
    facilityComponentsPage = new FacilityComponentsPage();
    expect(await facilityComponentsPage.getTitle().getText()).to.match(/Facilities/);
  });

  it('should load create Facility page', async () => {
    await facilityComponentsPage.clickOnCreateButton();
    facilityUpdatePage = new FacilityUpdatePage();
    expect(await facilityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Facility/);
  });

  it('should create and save Facilities', async () => {
    const nbButtonsBeforeCreate = await facilityComponentsPage.countDeleteButtons();

    await facilityUpdatePage.setNameInput('name');
    expect(await facilityUpdatePage.getNameInput()).to.match(/name/);
    await facilityUpdatePage.statusSelectLastOption();
    await facilityUpdatePage.setStartDateInput('01-01-2001');
    expect(await facilityUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    await facilityUpdatePage.setEndDateInput('01-01-2001');
    expect(await facilityUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    await facilityUpdatePage.setSuspandStartDateInput('01-01-2001');
    expect(await facilityUpdatePage.getSuspandStartDateInput()).to.eq('2001-01-01');
    await facilityUpdatePage.setSuspandEndDateInput('01-01-2001');
    expect(await facilityUpdatePage.getSuspandEndDateInput()).to.eq('2001-01-01');
    await facilityUpdatePage.academicYearSelectLastOption();
    await facilityUpdatePage.branchSelectLastOption();
    await waitUntilDisplayed(facilityUpdatePage.getSaveButton());
    await facilityUpdatePage.save();
    await waitUntilHidden(facilityUpdatePage.getSaveButton());
    expect(await facilityUpdatePage.getSaveButton().isPresent()).to.be.false;

    await facilityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await facilityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Facility', async () => {
    await facilityComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await facilityComponentsPage.countDeleteButtons();
    await facilityComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    facilityDeleteDialog = new FacilityDeleteDialog();
    expect(await facilityDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.facility.delete.question/);
    await facilityDeleteDialog.clickOnConfirmButton();

    await facilityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await facilityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
