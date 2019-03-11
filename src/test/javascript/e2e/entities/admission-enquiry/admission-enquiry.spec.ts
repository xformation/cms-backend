/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdmissionEnquiryComponentsPage from './admission-enquiry.page-object';
import AdmissionEnquiryUpdatePage from './admission-enquiry-update.page-object';

const expect = chai.expect;

describe('AdmissionEnquiry e2e test', () => {
  let navBarPage: NavBarPage;
  let admissionEnquiryUpdatePage: AdmissionEnquiryUpdatePage;
  let admissionEnquiryComponentsPage: AdmissionEnquiryComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AdmissionEnquiries', async () => {
    navBarPage.getEntityPage('admission-enquiry');
    admissionEnquiryComponentsPage = new AdmissionEnquiryComponentsPage();
    expect(await admissionEnquiryComponentsPage.getTitle().getText()).to.match(/Admission Enquiries/);
  });

  it('should load create AdmissionEnquiry page', async () => {
    admissionEnquiryComponentsPage.clickOnCreateButton();
    admissionEnquiryUpdatePage = new AdmissionEnquiryUpdatePage();
    expect(await admissionEnquiryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdmissionEnquiry/);
  });

  it('should create and save AdmissionEnquiries', async () => {
    admissionEnquiryUpdatePage.setStudentNameInput('studentName');
    expect(await admissionEnquiryUpdatePage.getStudentNameInput()).to.match(/studentName/);
    admissionEnquiryUpdatePage.setMobileNumberInput('mobileNumber');
    expect(await admissionEnquiryUpdatePage.getMobileNumberInput()).to.match(/mobileNumber/);
    admissionEnquiryUpdatePage.setAlternateMobileNumberInput('alternateMobileNumber');
    expect(await admissionEnquiryUpdatePage.getAlternateMobileNumberInput()).to.match(/alternateMobileNumber/);
    admissionEnquiryUpdatePage.setEmailInput('email');
    expect(await admissionEnquiryUpdatePage.getEmailInput()).to.match(/email/);
    admissionEnquiryUpdatePage.courseApplyingForSelectLastOption();
    admissionEnquiryUpdatePage.modeOfEnquirySelectLastOption();
    admissionEnquiryUpdatePage.statusSelectLastOption();
    admissionEnquiryUpdatePage.setDescriptionInput('description');
    expect(await admissionEnquiryUpdatePage.getDescriptionInput()).to.match(/description/);
    admissionEnquiryUpdatePage.setEnquiryDateInput('01-01-2001');
    expect(await admissionEnquiryUpdatePage.getEnquiryDateInput()).to.eq('2001-01-01');
    admissionEnquiryUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await admissionEnquiryUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    admissionEnquiryUpdatePage.setUpdatedByInput('updatedBy');
    expect(await admissionEnquiryUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    admissionEnquiryUpdatePage.branchSelectLastOption();
    admissionEnquiryUpdatePage.admissionApplicationSelectLastOption();
    await admissionEnquiryUpdatePage.save();
    expect(await admissionEnquiryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
