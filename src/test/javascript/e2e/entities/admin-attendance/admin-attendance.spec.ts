/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AdminAttendanceComponentsPage from './admin-attendance.page-object';
import { AdminAttendanceDeleteDialog } from './admin-attendance.page-object';
import AdminAttendanceUpdatePage from './admin-attendance-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AdminAttendance e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let adminAttendanceUpdatePage: AdminAttendanceUpdatePage;
  let adminAttendanceComponentsPage: AdminAttendanceComponentsPage;
  let adminAttendanceDeleteDialog: AdminAttendanceDeleteDialog;

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

  it('should load AdminAttendances', async () => {
    await navBarPage.getEntityPage('admin-attendance');
    adminAttendanceComponentsPage = new AdminAttendanceComponentsPage();
    expect(await adminAttendanceComponentsPage.getTitle().getText()).to.match(/Admin Attendances/);
  });

  it('should load create AdminAttendance page', async () => {
    await adminAttendanceComponentsPage.clickOnCreateButton();
    adminAttendanceUpdatePage = new AdminAttendanceUpdatePage();
    expect(await adminAttendanceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdminAttendance/);
  });

  it('should create and save AdminAttendances', async () => {
    const nbButtonsBeforeCreate = await adminAttendanceComponentsPage.countDeleteButtons();

    await adminAttendanceUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await adminAttendanceUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    await adminAttendanceUpdatePage.setUpdatedByInput('updatedBy');
    expect(await adminAttendanceUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    await adminAttendanceUpdatePage.lectureSelectLastOption();
    await adminAttendanceUpdatePage.branchSelectLastOption();
    await adminAttendanceUpdatePage.collegeSelectLastOption();
    await adminAttendanceUpdatePage.departmentSelectLastOption();
    await adminAttendanceUpdatePage.academicyearSelectLastOption();
    await adminAttendanceUpdatePage.sectionSelectLastOption();
    await adminAttendanceUpdatePage.studentSelectLastOption();
    await waitUntilDisplayed(adminAttendanceUpdatePage.getSaveButton());
    await adminAttendanceUpdatePage.save();
    await waitUntilHidden(adminAttendanceUpdatePage.getSaveButton());
    expect(await adminAttendanceUpdatePage.getSaveButton().isPresent()).to.be.false;

    await adminAttendanceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await adminAttendanceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AdminAttendance', async () => {
    await adminAttendanceComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await adminAttendanceComponentsPage.countDeleteButtons();
    await adminAttendanceComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    adminAttendanceDeleteDialog = new AdminAttendanceDeleteDialog();
    expect(await adminAttendanceDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.adminAttendance.delete.question/);
    await adminAttendanceDeleteDialog.clickOnConfirmButton();

    await adminAttendanceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await adminAttendanceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
