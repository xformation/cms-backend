/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import StudentAttendanceComponentsPage from './student-attendance.page-object';
import { StudentAttendanceDeleteDialog } from './student-attendance.page-object';
import StudentAttendanceUpdatePage from './student-attendance-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('StudentAttendance e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studentAttendanceUpdatePage: StudentAttendanceUpdatePage;
  let studentAttendanceComponentsPage: StudentAttendanceComponentsPage;
  let studentAttendanceDeleteDialog: StudentAttendanceDeleteDialog;

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

  it('should load StudentAttendances', async () => {
    await navBarPage.getEntityPage('student-attendance');
    studentAttendanceComponentsPage = new StudentAttendanceComponentsPage();
    expect(await studentAttendanceComponentsPage.getTitle().getText()).to.match(/Student Attendances/);
  });

  it('should load create StudentAttendance page', async () => {
    await studentAttendanceComponentsPage.clickOnCreateButton();
    studentAttendanceUpdatePage = new StudentAttendanceUpdatePage();
    expect(await studentAttendanceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a StudentAttendance/);
  });

  it('should create and save StudentAttendances', async () => {
    const nbButtonsBeforeCreate = await studentAttendanceComponentsPage.countDeleteButtons();

    await studentAttendanceUpdatePage.attendanceStatusSelectLastOption();
    await studentAttendanceUpdatePage.setCommentsInput('comments');
    expect(await studentAttendanceUpdatePage.getCommentsInput()).to.match(/comments/);
    await studentAttendanceUpdatePage.studentSelectLastOption();
    await studentAttendanceUpdatePage.lectureSelectLastOption();
    await waitUntilDisplayed(studentAttendanceUpdatePage.getSaveButton());
    await studentAttendanceUpdatePage.save();
    await waitUntilHidden(studentAttendanceUpdatePage.getSaveButton());
    expect(await studentAttendanceUpdatePage.getSaveButton().isPresent()).to.be.false;

    await studentAttendanceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await studentAttendanceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last StudentAttendance', async () => {
    await studentAttendanceComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await studentAttendanceComponentsPage.countDeleteButtons();
    await studentAttendanceComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    studentAttendanceDeleteDialog = new StudentAttendanceDeleteDialog();
    expect(await studentAttendanceDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.studentAttendance.delete.question/);
    await studentAttendanceDeleteDialog.clickOnConfirmButton();

    await studentAttendanceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await studentAttendanceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
