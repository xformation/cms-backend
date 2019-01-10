/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentAttendanceComponentsPage from './student-attendance.page-object';
import StudentAttendanceUpdatePage from './student-attendance-update.page-object';

const expect = chai.expect;

describe('StudentAttendance e2e test', () => {
  let navBarPage: NavBarPage;
  let studentAttendanceUpdatePage: StudentAttendanceUpdatePage;
  let studentAttendanceComponentsPage: StudentAttendanceComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load StudentAttendances', async () => {
    navBarPage.getEntityPage('student-attendance');
    studentAttendanceComponentsPage = new StudentAttendanceComponentsPage();
    expect(await studentAttendanceComponentsPage.getTitle().getText()).to.match(/Student Attendances/);
  });

  it('should load create StudentAttendance page', async () => {
    studentAttendanceComponentsPage.clickOnCreateButton();
    studentAttendanceUpdatePage = new StudentAttendanceUpdatePage();
    expect(await studentAttendanceUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.studentAttendance.home.createOrEditLabel/);
  });

  it('should create and save StudentAttendances', async () => {
    studentAttendanceUpdatePage.attendanceStatusSelectLastOption();
    studentAttendanceUpdatePage.setCommentsInput('comments');
    expect(await studentAttendanceUpdatePage.getCommentsInput()).to.match(/comments/);
    studentAttendanceUpdatePage.studentSelectLastOption();
    studentAttendanceUpdatePage.lectureSelectLastOption();
    await studentAttendanceUpdatePage.save();
    expect(await studentAttendanceUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
