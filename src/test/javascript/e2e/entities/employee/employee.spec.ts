/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import EmployeeComponentsPage from './employee.page-object';
import { EmployeeDeleteDialog } from './employee.page-object';
import EmployeeUpdatePage from './employee-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Employee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let employeeUpdatePage: EmployeeUpdatePage;
  let employeeComponentsPage: EmployeeComponentsPage;
  let employeeDeleteDialog: EmployeeDeleteDialog;

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

  it('should load Employees', async () => {
    await navBarPage.getEntityPage('employee');
    employeeComponentsPage = new EmployeeComponentsPage();
    expect(await employeeComponentsPage.getTitle().getText()).to.match(/Employees/);
  });

  it('should load create Employee page', async () => {
    await employeeComponentsPage.clickOnCreateButton();
    employeeUpdatePage = new EmployeeUpdatePage();
    expect(await employeeUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Employee/);
  });

  it('should create and save Employees', async () => {
    const nbButtonsBeforeCreate = await employeeComponentsPage.countDeleteButtons();

    await employeeUpdatePage.setEmployeeNameInput('employeeName');
    expect(await employeeUpdatePage.getEmployeeNameInput()).to.match(/employeeName/);
    await employeeUpdatePage.setDesignationInput('designation');
    expect(await employeeUpdatePage.getDesignationInput()).to.match(/designation/);
    await employeeUpdatePage.setJoiningDateInput('01-01-2001');
    expect(await employeeUpdatePage.getJoiningDateInput()).to.eq('2001-01-01');
    await employeeUpdatePage.setJobEndDateInput('01-01-2001');
    expect(await employeeUpdatePage.getJobEndDateInput()).to.eq('2001-01-01');
    await employeeUpdatePage.setResignationDateInput('01-01-2001');
    expect(await employeeUpdatePage.getResignationDateInput()).to.eq('2001-01-01');
    await employeeUpdatePage.setResignationAcceptanceDateInput('01-01-2001');
    expect(await employeeUpdatePage.getResignationAcceptanceDateInput()).to.eq('2001-01-01');
    await employeeUpdatePage.setAadharNoInput('aadharNo');
    expect(await employeeUpdatePage.getAadharNoInput()).to.match(/aadharNo/);
    await employeeUpdatePage.setPanNoInput('panNo');
    expect(await employeeUpdatePage.getPanNoInput()).to.match(/panNo/);
    await employeeUpdatePage.setPassportNoInput('passportNo');
    expect(await employeeUpdatePage.getPassportNoInput()).to.match(/passportNo/);
    await employeeUpdatePage.setPrimaryContactNoInput('primaryContactNo');
    expect(await employeeUpdatePage.getPrimaryContactNoInput()).to.match(/primaryContactNo/);
    await employeeUpdatePage.setSecondaryContactNoInput('secondaryContactNo');
    expect(await employeeUpdatePage.getSecondaryContactNoInput()).to.match(/secondaryContactNo/);
    await employeeUpdatePage.setEmployeeFatherNameInput('employeeFatherName');
    expect(await employeeUpdatePage.getEmployeeFatherNameInput()).to.match(/employeeFatherName/);
    await employeeUpdatePage.setEmployeeMotherNameInput('employeeMotherName');
    expect(await employeeUpdatePage.getEmployeeMotherNameInput()).to.match(/employeeMotherName/);
    await employeeUpdatePage.setPrimaryAddressInput('primaryAddress');
    expect(await employeeUpdatePage.getPrimaryAddressInput()).to.match(/primaryAddress/);
    await employeeUpdatePage.setSecondaryAddressInput('secondaryAddress');
    expect(await employeeUpdatePage.getSecondaryAddressInput()).to.match(/secondaryAddress/);
    await employeeUpdatePage.setEmployeeAddressInput('employeeAddress');
    expect(await employeeUpdatePage.getEmployeeAddressInput()).to.match(/employeeAddress/);
    await employeeUpdatePage.setPersonalMailIdInput('personalMailId');
    expect(await employeeUpdatePage.getPersonalMailIdInput()).to.match(/personalMailId/);
    await employeeUpdatePage.setOfficialMailIdInput('officialMailId');
    expect(await employeeUpdatePage.getOfficialMailIdInput()).to.match(/officialMailId/);
    await employeeUpdatePage.disabilitySelectLastOption();
    await employeeUpdatePage.setDrivingLicenceNoInput('drivingLicenceNo');
    expect(await employeeUpdatePage.getDrivingLicenceNoInput()).to.match(/drivingLicenceNo/);
    await employeeUpdatePage.setDrivingLicenceValidityInput('01-01-2001');
    expect(await employeeUpdatePage.getDrivingLicenceValidityInput()).to.eq('2001-01-01');
    await employeeUpdatePage.setGenderInput('gender');
    expect(await employeeUpdatePage.getGenderInput()).to.match(/gender/);
    await waitUntilDisplayed(employeeUpdatePage.getSaveButton());
    await employeeUpdatePage.save();
    await waitUntilHidden(employeeUpdatePage.getSaveButton());
    expect(await employeeUpdatePage.getSaveButton().isPresent()).to.be.false;

    await employeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await employeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Employee', async () => {
    await employeeComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await employeeComponentsPage.countDeleteButtons();
    await employeeComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    employeeDeleteDialog = new EmployeeDeleteDialog();
    expect(await employeeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.employee.delete.question/);
    await employeeDeleteDialog.clickOnConfirmButton();

    await employeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await employeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
