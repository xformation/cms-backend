/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TeacherComponentsPage from './teacher.page-object';
import TeacherUpdatePage from './teacher-update.page-object';

const expect = chai.expect;

describe('Teacher e2e test', () => {
  let navBarPage: NavBarPage;
  let teacherUpdatePage: TeacherUpdatePage;
  let teacherComponentsPage: TeacherComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Teachers', async () => {
    navBarPage.getEntityPage('teacher');
    teacherComponentsPage = new TeacherComponentsPage();
    expect(await teacherComponentsPage.getTitle().getText()).to.match(/Teachers/);
  });

  it('should load create Teacher page', async () => {
    teacherComponentsPage.clickOnCreateButton();
    teacherUpdatePage = new TeacherUpdatePage();
    expect(await teacherUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.teacher.home.createOrEditLabel/);
  });

  it('should create and save Teachers', async () => {
    teacherUpdatePage.setTeacherNameInput('teacherName');
    expect(await teacherUpdatePage.getTeacherNameInput()).to.match(/teacherName/);
    teacherUpdatePage.periodsSelectLastOption();
    await teacherUpdatePage.save();
    expect(await teacherUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
