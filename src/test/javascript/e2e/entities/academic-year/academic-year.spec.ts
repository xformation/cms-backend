/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AcademicYearComponentsPage from './academic-year.page-object';
import AcademicYearUpdatePage from './academic-year-update.page-object';

const expect = chai.expect;

describe('AcademicYear e2e test', () => {
  let navBarPage: NavBarPage;
  let academicYearUpdatePage: AcademicYearUpdatePage;
  let academicYearComponentsPage: AcademicYearComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AcademicYears', async () => {
    navBarPage.getEntityPage('academic-year');
    academicYearComponentsPage = new AcademicYearComponentsPage();
    expect(await academicYearComponentsPage.getTitle().getText()).to.match(/Academic Years/);
  });

  it('should load create AcademicYear page', async () => {
    academicYearComponentsPage.clickOnCreateButton();
    academicYearUpdatePage = new AcademicYearUpdatePage();
    expect(await academicYearUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.academicYear.home.createOrEditLabel/);
  });

  it('should create and save AcademicYears', async () => {
    academicYearUpdatePage.setYearInput('5');
    expect(await academicYearUpdatePage.getYearInput()).to.eq('5');
    academicYearUpdatePage.setStartDateInput('01-01-2001');
    expect(await academicYearUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    academicYearUpdatePage.setEndDateInput('01-01-2001');
    expect(await academicYearUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    academicYearUpdatePage.setDescInput('desc');
    expect(await academicYearUpdatePage.getDescInput()).to.match(/desc/);
    await academicYearUpdatePage.save();
    expect(await academicYearUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
