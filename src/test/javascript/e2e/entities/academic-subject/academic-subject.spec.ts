/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AcademicSubjectComponentsPage from './academic-subject.page-object';
import AcademicSubjectUpdatePage from './academic-subject-update.page-object';

const expect = chai.expect;

describe('AcademicSubject e2e test', () => {
  let navBarPage: NavBarPage;
  let academicSubjectUpdatePage: AcademicSubjectUpdatePage;
  let academicSubjectComponentsPage: AcademicSubjectComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AcademicSubjects', async () => {
    navBarPage.getEntityPage('academic-subject');
    academicSubjectComponentsPage = new AcademicSubjectComponentsPage();
    expect(await academicSubjectComponentsPage.getTitle().getText()).to.match(/Academic Subjects/);
  });

  it('should load create AcademicSubject page', async () => {
    academicSubjectComponentsPage.clickOnCreateButton();
    academicSubjectUpdatePage = new AcademicSubjectUpdatePage();
    expect(await academicSubjectUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.academicSubject.home.createOrEditLabel/);
  });

  it('should create and save AcademicSubjects', async () => {
    academicSubjectUpdatePage.setSubjectNameInput('subjectName');
    expect(await academicSubjectUpdatePage.getSubjectNameInput()).to.match(/subjectName/);
    const selectedElectiveSub = await academicSubjectUpdatePage.getElectiveSubInput().isSelected();
    if (selectedElectiveSub) {
      academicSubjectUpdatePage.getElectiveSubInput().click();
      expect(await academicSubjectUpdatePage.getElectiveSubInput().isSelected()).to.be.false;
    } else {
      academicSubjectUpdatePage.getElectiveSubInput().click();
      expect(await academicSubjectUpdatePage.getElectiveSubInput().isSelected()).to.be.true;
    }
    academicSubjectUpdatePage.departmentSelectLastOption();
    await academicSubjectUpdatePage.save();
    expect(await academicSubjectUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
