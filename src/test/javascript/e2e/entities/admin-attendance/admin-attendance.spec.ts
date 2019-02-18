/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdminAttendanceComponentsPage from './admin-attendance.page-object';
import AdminAttendanceUpdatePage from './admin-attendance-update.page-object';

const expect = chai.expect;

describe('AdminAttendance e2e test', () => {
  let navBarPage: NavBarPage;
  let adminAttendanceUpdatePage: AdminAttendanceUpdatePage;
  let adminAttendanceComponentsPage: AdminAttendanceComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AdminAttendances', async () => {
    navBarPage.getEntityPage('admin-attendance');
    adminAttendanceComponentsPage = new AdminAttendanceComponentsPage();
    expect(await adminAttendanceComponentsPage.getTitle().getText()).to.match(/Admin Attendances/);
  });

  it('should load create AdminAttendance page', async () => {
    adminAttendanceComponentsPage.clickOnCreateButton();
    adminAttendanceUpdatePage = new AdminAttendanceUpdatePage();
    expect(await adminAttendanceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdminAttendance/);
  });

  it('should create and save AdminAttendances', async () => {
    adminAttendanceUpdatePage.attendanceStatusSelectLastOption();
    adminAttendanceUpdatePage.setCommentsInput('comments');
    expect(await adminAttendanceUpdatePage.getCommentsInput()).to.match(/comments/);
    adminAttendanceUpdatePage.studentSelectLastOption();
    adminAttendanceUpdatePage.lectureSelectLastOption();
    await adminAttendanceUpdatePage.save();
    expect(await adminAttendanceUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
