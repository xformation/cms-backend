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
    studentAttendanceUpdatePage.setAttendanceDateInput('01-01-2001');
    expect(await studentAttendanceUpdatePage.getAttendanceDateInput()).to.eq('2001-01-01');
    studentAttendanceUpdatePage.setSNameInput('sName');
    expect(await studentAttendanceUpdatePage.getSNameInput()).to.match(/sName/);
    studentAttendanceUpdatePage.statusSelectLastOption();
    studentAttendanceUpdatePage.setCommentsInput('comments');
    expect(await studentAttendanceUpdatePage.getCommentsInput()).to.match(/comments/);
    studentAttendanceUpdatePage.studentYearSelectLastOption();
    studentAttendanceUpdatePage.departmentsSelectLastOption();
    studentAttendanceUpdatePage.subjectSelectLastOption();
    studentAttendanceUpdatePage.semesterSelectLastOption();
    studentAttendanceUpdatePage.sectionSelectLastOption();
    studentAttendanceUpdatePage.periodsSelectLastOption();
    studentAttendanceUpdatePage.studentSelectLastOption();
    await studentAttendanceUpdatePage.save();
    expect(await studentAttendanceUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});