/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AttendanceMasterComponentsPage from './attendance-master.page-object';
import AttendanceMasterUpdatePage from './attendance-master-update.page-object';

const expect = chai.expect;

describe('AttendanceMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let attendanceMasterUpdatePage: AttendanceMasterUpdatePage;
  let attendanceMasterComponentsPage: AttendanceMasterComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AttendanceMasters', async () => {
    navBarPage.getEntityPage('attendance-master');
    attendanceMasterComponentsPage = new AttendanceMasterComponentsPage();
    expect(await attendanceMasterComponentsPage.getTitle().getText()).to.match(/Attendance Masters/);
  });

  it('should load create AttendanceMaster page', async () => {
    attendanceMasterComponentsPage.clickOnCreateButton();
    attendanceMasterUpdatePage = new AttendanceMasterUpdatePage();
    expect(await attendanceMasterUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AttendanceMaster/);
  });

  it('should create and save AttendanceMasters', async () => {
    attendanceMasterUpdatePage.setDescInput('desc');
    expect(await attendanceMasterUpdatePage.getDescInput()).to.match(/desc/);
    attendanceMasterUpdatePage.teachSelectLastOption();
    attendanceMasterUpdatePage.batchSelectLastOption();
    attendanceMasterUpdatePage.sectionSelectLastOption();
    await attendanceMasterUpdatePage.save();
    expect(await attendanceMasterUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
