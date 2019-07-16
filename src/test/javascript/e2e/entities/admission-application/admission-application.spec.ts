/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AdmissionApplicationComponentsPage from './admission-application.page-object';
import { AdmissionApplicationDeleteDialog } from './admission-application.page-object';
import AdmissionApplicationUpdatePage from './admission-application-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AdmissionApplication e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let admissionApplicationUpdatePage: AdmissionApplicationUpdatePage;
  let admissionApplicationComponentsPage: AdmissionApplicationComponentsPage;
  let admissionApplicationDeleteDialog: AdmissionApplicationDeleteDialog;

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

  it('should load AdmissionApplications', async () => {
    await navBarPage.getEntityPage('admission-application');
    admissionApplicationComponentsPage = new AdmissionApplicationComponentsPage();
    expect(await admissionApplicationComponentsPage.getTitle().getText()).to.match(/Admission Applications/);
  });

  it('should load create AdmissionApplication page', async () => {
    await admissionApplicationComponentsPage.clickOnCreateButton();
    admissionApplicationUpdatePage = new AdmissionApplicationUpdatePage();
    expect(await admissionApplicationUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdmissionApplication/);
  });

  it('should create and save AdmissionApplications', async () => {
    const nbButtonsBeforeCreate = await admissionApplicationComponentsPage.countDeleteButtons();

    await admissionApplicationUpdatePage.admissionStatusSelectLastOption();
    await admissionApplicationUpdatePage.setStudentNameInput('studentName');
    expect(await admissionApplicationUpdatePage.getStudentNameInput()).to.match(/studentName/);
    await admissionApplicationUpdatePage.setStudentMiddleNameInput('studentMiddleName');
    expect(await admissionApplicationUpdatePage.getStudentMiddleNameInput()).to.match(/studentMiddleName/);
    await admissionApplicationUpdatePage.setStudentLastNameInput('studentLastName');
    expect(await admissionApplicationUpdatePage.getStudentLastNameInput()).to.match(/studentLastName/);
    await admissionApplicationUpdatePage.setFatherNameInput('fatherName');
    expect(await admissionApplicationUpdatePage.getFatherNameInput()).to.match(/fatherName/);
    await admissionApplicationUpdatePage.setFatherMiddleNameInput('fatherMiddleName');
    expect(await admissionApplicationUpdatePage.getFatherMiddleNameInput()).to.match(/fatherMiddleName/);
    await admissionApplicationUpdatePage.setFatherLastNameInput('fatherLastName');
    expect(await admissionApplicationUpdatePage.getFatherLastNameInput()).to.match(/fatherLastName/);
    await admissionApplicationUpdatePage.setMotherNameInput('motherName');
    expect(await admissionApplicationUpdatePage.getMotherNameInput()).to.match(/motherName/);
    await admissionApplicationUpdatePage.setMotherMiddleNameInput('motherMiddleName');
    expect(await admissionApplicationUpdatePage.getMotherMiddleNameInput()).to.match(/motherMiddleName/);
    await admissionApplicationUpdatePage.setMotherLastNameInput('motherLastName');
    expect(await admissionApplicationUpdatePage.getMotherLastNameInput()).to.match(/motherLastName/);
    await admissionApplicationUpdatePage.setContactNumberInput('contactNumber');
    expect(await admissionApplicationUpdatePage.getContactNumberInput()).to.match(/contactNumber/);
    await admissionApplicationUpdatePage.setAlternateMobileNumberInput('alternateMobileNumber');
    expect(await admissionApplicationUpdatePage.getAlternateMobileNumberInput()).to.match(/alternateMobileNumber/);
    await admissionApplicationUpdatePage.setDateOfBirthInput('01-01-2001');
    expect(await admissionApplicationUpdatePage.getDateOfBirthInput()).to.eq('2001-01-01');
    await admissionApplicationUpdatePage.setEmailInput('email');
    expect(await admissionApplicationUpdatePage.getEmailInput()).to.match(/email/);
    await admissionApplicationUpdatePage.sexSelectLastOption();
    await admissionApplicationUpdatePage.setCommentsInput('comments');
    expect(await admissionApplicationUpdatePage.getCommentsInput()).to.match(/comments/);
    await admissionApplicationUpdatePage.setApplicationIdInput('5');
    expect(await admissionApplicationUpdatePage.getApplicationIdInput()).to.eq('5');
    await admissionApplicationUpdatePage.setUploadPhotoInput('uploadPhoto');
    expect(await admissionApplicationUpdatePage.getUploadPhotoInput()).to.match(/uploadPhoto/);
    await admissionApplicationUpdatePage.courseSelectLastOption();
    await admissionApplicationUpdatePage.setAdmissionDateInput('01-01-2001');
    expect(await admissionApplicationUpdatePage.getAdmissionDateInput()).to.eq('2001-01-01');
    await admissionApplicationUpdatePage.admissionEnquirySelectLastOption();
    await admissionApplicationUpdatePage.academicHistorySelectLastOption();
    await admissionApplicationUpdatePage.documentsSelectLastOption();
    await admissionApplicationUpdatePage.branchSelectLastOption();
    await admissionApplicationUpdatePage.batchSelectLastOption();
    await admissionApplicationUpdatePage.stateSelectLastOption();
    await admissionApplicationUpdatePage.citySelectLastOption();
    await admissionApplicationUpdatePage.countrySelectLastOption();
    await admissionApplicationUpdatePage.departmentSelectLastOption();
    await admissionApplicationUpdatePage.academicyearSelectLastOption();
    await waitUntilDisplayed(admissionApplicationUpdatePage.getSaveButton());
    await admissionApplicationUpdatePage.save();
    await waitUntilHidden(admissionApplicationUpdatePage.getSaveButton());
    expect(await admissionApplicationUpdatePage.getSaveButton().isPresent()).to.be.false;

    await admissionApplicationComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await admissionApplicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AdmissionApplication', async () => {
    await admissionApplicationComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await admissionApplicationComponentsPage.countDeleteButtons();
    await admissionApplicationComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    admissionApplicationDeleteDialog = new AdmissionApplicationDeleteDialog();
    expect(await admissionApplicationDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /cmsApp.admissionApplication.delete.question/
    );
    await admissionApplicationDeleteDialog.clickOnConfirmButton();

    await admissionApplicationComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await admissionApplicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
