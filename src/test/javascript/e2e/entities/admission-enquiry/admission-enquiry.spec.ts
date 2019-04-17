/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AdmissionEnquiryComponentsPage from './admission-enquiry.page-object';
import { AdmissionEnquiryDeleteDialog } from './admission-enquiry.page-object';
import AdmissionEnquiryUpdatePage from './admission-enquiry-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AdmissionEnquiry e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let admissionEnquiryUpdatePage: AdmissionEnquiryUpdatePage;
  let admissionEnquiryComponentsPage: AdmissionEnquiryComponentsPage;
  let admissionEnquiryDeleteDialog: AdmissionEnquiryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load AdmissionEnquiries', async () => {
    await navBarPage.getEntityPage('admission-enquiry');
    admissionEnquiryComponentsPage = new AdmissionEnquiryComponentsPage();
    expect(await admissionEnquiryComponentsPage.getTitle().getText()).to.match(/Admission Enquiries/);
  });

  it('should load create AdmissionEnquiry page', async () => {
    await admissionEnquiryComponentsPage.clickOnCreateButton();
    admissionEnquiryUpdatePage = new AdmissionEnquiryUpdatePage();
    expect(await admissionEnquiryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdmissionEnquiry/);
  });

  it('should create and save AdmissionEnquiries', async () => {
    const nbButtonsBeforeCreate = await admissionEnquiryComponentsPage.countDeleteButtons();

    await admissionEnquiryUpdatePage.setStudentNameInput('studentName');
    expect(await admissionEnquiryUpdatePage.getStudentNameInput()).to.match(/studentName/);
    await admissionEnquiryUpdatePage.setMobileNumberInput('mobileNumber');
    expect(await admissionEnquiryUpdatePage.getMobileNumberInput()).to.match(/mobileNumber/);
    await admissionEnquiryUpdatePage.setAlternateMobileNumberInput('alternateMobileNumber');
    expect(await admissionEnquiryUpdatePage.getAlternateMobileNumberInput()).to.match(/alternateMobileNumber/);
    await admissionEnquiryUpdatePage.setEmailInput('email');
    expect(await admissionEnquiryUpdatePage.getEmailInput()).to.match(/email/);
    await admissionEnquiryUpdatePage.courseApplyingForSelectLastOption();
    await admissionEnquiryUpdatePage.modeOfEnquirySelectLastOption();
    await admissionEnquiryUpdatePage.statusSelectLastOption();
    await admissionEnquiryUpdatePage.setDescriptionInput('description');
    expect(await admissionEnquiryUpdatePage.getDescriptionInput()).to.match(/description/);
    await admissionEnquiryUpdatePage.setEnquiryDateInput('01-01-2001');
    expect(await admissionEnquiryUpdatePage.getEnquiryDateInput()).to.eq('2001-01-01');
    await admissionEnquiryUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await admissionEnquiryUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    await admissionEnquiryUpdatePage.setUpdatedByInput('updatedBy');
    expect(await admissionEnquiryUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    await admissionEnquiryUpdatePage.branchSelectLastOption();
    await admissionEnquiryUpdatePage.admissionApplicationSelectLastOption();
    await waitUntilDisplayed(admissionEnquiryUpdatePage.getSaveButton());
    await admissionEnquiryUpdatePage.save();
    await waitUntilHidden(admissionEnquiryUpdatePage.getSaveButton());
    expect(await admissionEnquiryUpdatePage.getSaveButton().isPresent()).to.be.false;

    await admissionEnquiryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await admissionEnquiryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AdmissionEnquiry', async () => {
    await admissionEnquiryComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await admissionEnquiryComponentsPage.countDeleteButtons();
    await admissionEnquiryComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    admissionEnquiryDeleteDialog = new AdmissionEnquiryDeleteDialog();
    expect(await admissionEnquiryDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.admissionEnquiry.delete.question/);
    await admissionEnquiryDeleteDialog.clickOnConfirmButton();

    await admissionEnquiryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await admissionEnquiryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
