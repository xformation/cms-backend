/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AcademicHistoryComponentsPage from './academic-history.page-object';
import AcademicHistoryUpdatePage from './academic-history-update.page-object';

const expect = chai.expect;

describe('AcademicHistory e2e test', () => {
  let navBarPage: NavBarPage;
  let academicHistoryUpdatePage: AcademicHistoryUpdatePage;
  let academicHistoryComponentsPage: AcademicHistoryComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AcademicHistories', async () => {
    navBarPage.getEntityPage('academic-history');
    academicHistoryComponentsPage = new AcademicHistoryComponentsPage();
    expect(await academicHistoryComponentsPage.getTitle().getText()).to.match(/Academic Histories/);
  });

  it('should load create AcademicHistory page', async () => {
    academicHistoryComponentsPage.clickOnCreateButton();
    academicHistoryUpdatePage = new AcademicHistoryUpdatePage();
    expect(await academicHistoryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AcademicHistory/);
  });

  it('should create and save AcademicHistories', async () => {
    academicHistoryUpdatePage.setQualificationInput('qualification');
    expect(await academicHistoryUpdatePage.getQualificationInput()).to.match(/qualification/);
    academicHistoryUpdatePage.setYearOfPassingInput('yearOfPassing');
    expect(await academicHistoryUpdatePage.getYearOfPassingInput()).to.match(/yearOfPassing/);
    academicHistoryUpdatePage.setInstitutionInput('institution');
    expect(await academicHistoryUpdatePage.getInstitutionInput()).to.match(/institution/);
    academicHistoryUpdatePage.setUniversityInput('university');
    expect(await academicHistoryUpdatePage.getUniversityInput()).to.match(/university/);
    academicHistoryUpdatePage.setEnrollmentNoInput('5');
    expect(await academicHistoryUpdatePage.getEnrollmentNoInput()).to.eq('5');
    academicHistoryUpdatePage.setScoreInput('5');
    expect(await academicHistoryUpdatePage.getScoreInput()).to.eq('5');
    academicHistoryUpdatePage.setPercentageInput('5');
    expect(await academicHistoryUpdatePage.getPercentageInput()).to.eq('5');
    academicHistoryUpdatePage.studentSelectLastOption();
    await academicHistoryUpdatePage.save();
    expect(await academicHistoryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
