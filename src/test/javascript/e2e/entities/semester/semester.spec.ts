/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SemesterComponentsPage from './semester.page-object';
import SemesterUpdatePage from './semester-update.page-object';

const expect = chai.expect;

describe('Semester e2e test', () => {
  let navBarPage: NavBarPage;
  let semesterUpdatePage: SemesterUpdatePage;
  let semesterComponentsPage: SemesterComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Semesters', async () => {
    navBarPage.getEntityPage('semester');
    semesterComponentsPage = new SemesterComponentsPage();
    expect(await semesterComponentsPage.getTitle().getText()).to.match(/Semesters/);
  });

  it('should load create Semester page', async () => {
    semesterComponentsPage.clickOnCreateButton();
    semesterUpdatePage = new SemesterUpdatePage();
    expect(await semesterUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.semester.home.createOrEditLabel/);
  });

  it('should create and save Semesters', async () => {
    semesterUpdatePage.semSelectLastOption();
    await semesterUpdatePage.save();
    expect(await semesterUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
