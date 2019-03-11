/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdmissionApplicationComponentsPage from './admission-application.page-object';
import AdmissionApplicationUpdatePage from './admission-application-update.page-object';

const expect = chai.expect;

describe('AdmissionApplication e2e test', () => {
  let navBarPage: NavBarPage;
  let admissionApplicationUpdatePage: AdmissionApplicationUpdatePage;
  let admissionApplicationComponentsPage: AdmissionApplicationComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AdmissionApplications', async () => {
    navBarPage.getEntityPage('admission-application');
    admissionApplicationComponentsPage = new AdmissionApplicationComponentsPage();
    expect(await admissionApplicationComponentsPage.getTitle().getText()).to.match(/Admission Applications/);
  });

  it('should load create AdmissionApplication page', async () => {
    admissionApplicationComponentsPage.clickOnCreateButton();
    admissionApplicationUpdatePage = new AdmissionApplicationUpdatePage();
    expect(await admissionApplicationUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdmissionApplication/);
  });

  it('should create and save AdmissionApplications', async () => {
    admissionApplicationUpdatePage.admissionStatusSelectLastOption();
    admissionApplicationUpdatePage.courseSelectLastOption();
    admissionApplicationUpdatePage.setDateInput('01-01-2001');
    expect(await admissionApplicationUpdatePage.getDateInput()).to.eq('2001-01-01');
    admissionApplicationUpdatePage.setCommentsInput('comments');
    expect(await admissionApplicationUpdatePage.getCommentsInput()).to.match(/comments/);
    admissionApplicationUpdatePage.studentSelectLastOption();
    await admissionApplicationUpdatePage.save();
    expect(await admissionApplicationUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
