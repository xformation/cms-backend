/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentYearComponentsPage from './student-year.page-object';
import StudentYearUpdatePage from './student-year-update.page-object';

const expect = chai.expect;

describe('StudentYear e2e test', () => {
  let navBarPage: NavBarPage;
  let studentYearUpdatePage: StudentYearUpdatePage;
  let studentYearComponentsPage: StudentYearComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load StudentYears', async () => {
    navBarPage.getEntityPage('student-year');
    studentYearComponentsPage = new StudentYearComponentsPage();
    expect(await studentYearComponentsPage.getTitle().getText()).to.match(/Student Years/);
  });

  it('should load create StudentYear page', async () => {
    studentYearComponentsPage.clickOnCreateButton();
    studentYearUpdatePage = new StudentYearUpdatePage();
    expect(await studentYearUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.studentYear.home.createOrEditLabel/);
  });

  it('should create and save StudentYears', async () => {
    studentYearUpdatePage.sYearSelectLastOption();
    await studentYearUpdatePage.save();
    expect(await studentYearUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
