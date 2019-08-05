import { element, by, ElementFinder } from 'protractor';

export default class EmployeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.employee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  employeeNameInput: ElementFinder = element(by.css('input#employee-employeeName'));
  designationInput: ElementFinder = element(by.css('input#employee-designation'));
  joiningDateInput: ElementFinder = element(by.css('input#employee-joiningDate'));
  jobEndDateInput: ElementFinder = element(by.css('input#employee-jobEndDate'));
  resignationDateInput: ElementFinder = element(by.css('input#employee-resignationDate'));
  resignationAcceptanceDateInput: ElementFinder = element(by.css('input#employee-resignationAcceptanceDate'));
  aadharNoInput: ElementFinder = element(by.css('input#employee-aadharNo'));
  panNoInput: ElementFinder = element(by.css('input#employee-panNo'));
  passportNoInput: ElementFinder = element(by.css('input#employee-passportNo'));
  primaryContactNoInput: ElementFinder = element(by.css('input#employee-primaryContactNo'));
  secondaryContactNoInput: ElementFinder = element(by.css('input#employee-secondaryContactNo'));
  employeeFatherNameInput: ElementFinder = element(by.css('input#employee-employeeFatherName'));
  employeeMotherNameInput: ElementFinder = element(by.css('input#employee-employeeMotherName'));
  primaryAddressInput: ElementFinder = element(by.css('input#employee-primaryAddress'));
  secondaryAddressInput: ElementFinder = element(by.css('input#employee-secondaryAddress'));
  employeeAddressInput: ElementFinder = element(by.css('input#employee-employeeAddress'));
  personalMailIdInput: ElementFinder = element(by.css('input#employee-personalMailId'));
  officialMailIdInput: ElementFinder = element(by.css('input#employee-officialMailId'));
  disabilitySelect: ElementFinder = element(by.css('select#employee-disability'));
  drivingLicenceNoInput: ElementFinder = element(by.css('input#employee-drivingLicenceNo'));
  drivingLicenceValidityInput: ElementFinder = element(by.css('input#employee-drivingLicenceValidity'));
  genderInput: ElementFinder = element(by.css('input#employee-gender'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setEmployeeNameInput(employeeName) {
    await this.employeeNameInput.sendKeys(employeeName);
  }

  async getEmployeeNameInput() {
    return this.employeeNameInput.getAttribute('value');
  }

  async setDesignationInput(designation) {
    await this.designationInput.sendKeys(designation);
  }

  async getDesignationInput() {
    return this.designationInput.getAttribute('value');
  }

  async setJoiningDateInput(joiningDate) {
    await this.joiningDateInput.sendKeys(joiningDate);
  }

  async getJoiningDateInput() {
    return this.joiningDateInput.getAttribute('value');
  }

  async setJobEndDateInput(jobEndDate) {
    await this.jobEndDateInput.sendKeys(jobEndDate);
  }

  async getJobEndDateInput() {
    return this.jobEndDateInput.getAttribute('value');
  }

  async setResignationDateInput(resignationDate) {
    await this.resignationDateInput.sendKeys(resignationDate);
  }

  async getResignationDateInput() {
    return this.resignationDateInput.getAttribute('value');
  }

  async setResignationAcceptanceDateInput(resignationAcceptanceDate) {
    await this.resignationAcceptanceDateInput.sendKeys(resignationAcceptanceDate);
  }

  async getResignationAcceptanceDateInput() {
    return this.resignationAcceptanceDateInput.getAttribute('value');
  }

  async setAadharNoInput(aadharNo) {
    await this.aadharNoInput.sendKeys(aadharNo);
  }

  async getAadharNoInput() {
    return this.aadharNoInput.getAttribute('value');
  }

  async setPanNoInput(panNo) {
    await this.panNoInput.sendKeys(panNo);
  }

  async getPanNoInput() {
    return this.panNoInput.getAttribute('value');
  }

  async setPassportNoInput(passportNo) {
    await this.passportNoInput.sendKeys(passportNo);
  }

  async getPassportNoInput() {
    return this.passportNoInput.getAttribute('value');
  }

  async setPrimaryContactNoInput(primaryContactNo) {
    await this.primaryContactNoInput.sendKeys(primaryContactNo);
  }

  async getPrimaryContactNoInput() {
    return this.primaryContactNoInput.getAttribute('value');
  }

  async setSecondaryContactNoInput(secondaryContactNo) {
    await this.secondaryContactNoInput.sendKeys(secondaryContactNo);
  }

  async getSecondaryContactNoInput() {
    return this.secondaryContactNoInput.getAttribute('value');
  }

  async setEmployeeFatherNameInput(employeeFatherName) {
    await this.employeeFatherNameInput.sendKeys(employeeFatherName);
  }

  async getEmployeeFatherNameInput() {
    return this.employeeFatherNameInput.getAttribute('value');
  }

  async setEmployeeMotherNameInput(employeeMotherName) {
    await this.employeeMotherNameInput.sendKeys(employeeMotherName);
  }

  async getEmployeeMotherNameInput() {
    return this.employeeMotherNameInput.getAttribute('value');
  }

  async setPrimaryAddressInput(primaryAddress) {
    await this.primaryAddressInput.sendKeys(primaryAddress);
  }

  async getPrimaryAddressInput() {
    return this.primaryAddressInput.getAttribute('value');
  }

  async setSecondaryAddressInput(secondaryAddress) {
    await this.secondaryAddressInput.sendKeys(secondaryAddress);
  }

  async getSecondaryAddressInput() {
    return this.secondaryAddressInput.getAttribute('value');
  }

  async setEmployeeAddressInput(employeeAddress) {
    await this.employeeAddressInput.sendKeys(employeeAddress);
  }

  async getEmployeeAddressInput() {
    return this.employeeAddressInput.getAttribute('value');
  }

  async setPersonalMailIdInput(personalMailId) {
    await this.personalMailIdInput.sendKeys(personalMailId);
  }

  async getPersonalMailIdInput() {
    return this.personalMailIdInput.getAttribute('value');
  }

  async setOfficialMailIdInput(officialMailId) {
    await this.officialMailIdInput.sendKeys(officialMailId);
  }

  async getOfficialMailIdInput() {
    return this.officialMailIdInput.getAttribute('value');
  }

  async setDisabilitySelect(disability) {
    await this.disabilitySelect.sendKeys(disability);
  }

  async getDisabilitySelect() {
    return this.disabilitySelect.element(by.css('option:checked')).getText();
  }

  async disabilitySelectLastOption() {
    await this.disabilitySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setDrivingLicenceNoInput(drivingLicenceNo) {
    await this.drivingLicenceNoInput.sendKeys(drivingLicenceNo);
  }

  async getDrivingLicenceNoInput() {
    return this.drivingLicenceNoInput.getAttribute('value');
  }

  async setDrivingLicenceValidityInput(drivingLicenceValidity) {
    await this.drivingLicenceValidityInput.sendKeys(drivingLicenceValidity);
  }

  async getDrivingLicenceValidityInput() {
    return this.drivingLicenceValidityInput.getAttribute('value');
  }

  async setGenderInput(gender) {
    await this.genderInput.sendKeys(gender);
  }

  async getGenderInput() {
    return this.genderInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
