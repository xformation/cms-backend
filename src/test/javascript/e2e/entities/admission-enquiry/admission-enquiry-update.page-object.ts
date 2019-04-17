import { element, by, ElementFinder } from 'protractor';

export default class AdmissionEnquiryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.admissionEnquiry.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  studentNameInput: ElementFinder = element(by.css('input#admission-enquiry-studentName'));
  mobileNumberInput: ElementFinder = element(by.css('input#admission-enquiry-mobileNumber'));
  alternateMobileNumberInput: ElementFinder = element(by.css('input#admission-enquiry-alternateMobileNumber'));
  emailInput: ElementFinder = element(by.css('input#admission-enquiry-email'));
  courseApplyingForSelect: ElementFinder = element(by.css('select#admission-enquiry-courseApplyingFor'));
  modeOfEnquirySelect: ElementFinder = element(by.css('select#admission-enquiry-modeOfEnquiry'));
  statusSelect: ElementFinder = element(by.css('select#admission-enquiry-status'));
  descriptionInput: ElementFinder = element(by.css('input#admission-enquiry-description'));
  enquiryDateInput: ElementFinder = element(by.css('input#admission-enquiry-enquiryDate'));
  updatedOnInput: ElementFinder = element(by.css('input#admission-enquiry-updatedOn'));
  updatedByInput: ElementFinder = element(by.css('input#admission-enquiry-updatedBy'));
  branchSelect: ElementFinder = element(by.css('select#admission-enquiry-branch'));
  admissionApplicationSelect: ElementFinder = element(by.css('select#admission-enquiry-admissionApplication'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setStudentNameInput(studentName) {
    await this.studentNameInput.sendKeys(studentName);
  }

  async getStudentNameInput() {
    return this.studentNameInput.getAttribute('value');
  }

  async setMobileNumberInput(mobileNumber) {
    await this.mobileNumberInput.sendKeys(mobileNumber);
  }

  async getMobileNumberInput() {
    return this.mobileNumberInput.getAttribute('value');
  }

  async setAlternateMobileNumberInput(alternateMobileNumber) {
    await this.alternateMobileNumberInput.sendKeys(alternateMobileNumber);
  }

  async getAlternateMobileNumberInput() {
    return this.alternateMobileNumberInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setCourseApplyingForSelect(courseApplyingFor) {
    await this.courseApplyingForSelect.sendKeys(courseApplyingFor);
  }

  async getCourseApplyingForSelect() {
    return this.courseApplyingForSelect.element(by.css('option:checked')).getText();
  }

  async courseApplyingForSelectLastOption() {
    await this.courseApplyingForSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setModeOfEnquirySelect(modeOfEnquiry) {
    await this.modeOfEnquirySelect.sendKeys(modeOfEnquiry);
  }

  async getModeOfEnquirySelect() {
    return this.modeOfEnquirySelect.element(by.css('option:checked')).getText();
  }

  async modeOfEnquirySelectLastOption() {
    await this.modeOfEnquirySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setEnquiryDateInput(enquiryDate) {
    await this.enquiryDateInput.sendKeys(enquiryDate);
  }

  async getEnquiryDateInput() {
    return this.enquiryDateInput.getAttribute('value');
  }

  async setUpdatedOnInput(updatedOn) {
    await this.updatedOnInput.sendKeys(updatedOn);
  }

  async getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  async setUpdatedByInput(updatedBy) {
    await this.updatedByInput.sendKeys(updatedBy);
  }

  async getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  async branchSelectLastOption() {
    await this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async branchSelectOption(option) {
    await this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  async getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  async admissionApplicationSelectLastOption() {
    await this.admissionApplicationSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async admissionApplicationSelectOption(option) {
    await this.admissionApplicationSelect.sendKeys(option);
  }

  getAdmissionApplicationSelect() {
    return this.admissionApplicationSelect;
  }

  async getAdmissionApplicationSelectedOption() {
    return this.admissionApplicationSelect.element(by.css('option:checked')).getText();
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
