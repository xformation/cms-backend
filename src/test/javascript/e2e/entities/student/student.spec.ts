/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentComponentsPage from './student.page-object';
import StudentUpdatePage from './student-update.page-object';

const expect = chai.expect;

describe('Student e2e test', () => {
  let navBarPage: NavBarPage;
  let studentUpdatePage: StudentUpdatePage;
  let studentComponentsPage: StudentComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Students', async () => {
    navBarPage.getEntityPage('student');
    studentComponentsPage = new StudentComponentsPage();
    expect(await studentComponentsPage.getTitle().getText()).to.match(/Students/);
  });

  it('should load create Student page', async () => {
    studentComponentsPage.clickOnCreateButton();
    studentUpdatePage = new StudentUpdatePage();
    expect(await studentUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.student.home.createOrEditLabel/);
  });

  it('should create and save Students', async () => {
    studentUpdatePage.setStudentNameInput('studentName');
    expect(await studentUpdatePage.getStudentNameInput()).to.match(/studentName/);
    studentUpdatePage.batchSelectLastOption();
    studentUpdatePage.sectionSelectLastOption();
    studentUpdatePage.branchSelectLastOption();
    studentUpdatePage.departmentSelectLastOption();
    await studentUpdatePage.save();
    expect(await studentUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
