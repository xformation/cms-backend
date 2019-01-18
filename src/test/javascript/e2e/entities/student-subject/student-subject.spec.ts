/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentSubjectComponentsPage from './student-subject.page-object';
import StudentSubjectUpdatePage from './student-subject-update.page-object';

const expect = chai.expect;

describe('StudentSubject e2e test', () => {
  let navBarPage: NavBarPage;
  let studentSubjectUpdatePage: StudentSubjectUpdatePage;
  let studentSubjectComponentsPage: StudentSubjectComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load StudentSubjects', async () => {
    navBarPage.getEntityPage('student-subject');
    studentSubjectComponentsPage = new StudentSubjectComponentsPage();
    expect(await studentSubjectComponentsPage.getTitle().getText()).to.match(/Student Subjects/);
  });

  it('should load create StudentSubject page', async () => {
    studentSubjectComponentsPage.clickOnCreateButton();
    studentSubjectUpdatePage = new StudentSubjectUpdatePage();
    expect(await studentSubjectUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.studentSubject.home.createOrEditLabel/);
  });

  it('should create and save StudentSubjects', async () => {
    studentSubjectUpdatePage.setCommentsInput('comments');
    expect(await studentSubjectUpdatePage.getCommentsInput()).to.match(/comments/);
    studentSubjectUpdatePage.setLastupdatedDateInput('01-01-2001');
    expect(await studentSubjectUpdatePage.getLastupdatedDateInput()).to.eq('2001-01-01');
    studentSubjectUpdatePage.studentSelectLastOption();
    studentSubjectUpdatePage.subjectSelectLastOption();
    await studentSubjectUpdatePage.save();
    expect(await studentSubjectUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
