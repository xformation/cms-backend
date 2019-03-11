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

  setStudentNameInput(studentName) {
    this.studentNameInput.sendKeys(studentName);
  }

  getStudentNameInput() {
    return this.studentNameInput.getAttribute('value');
  }

  setMobileNumberInput(mobileNumber) {
    this.mobileNumberInput.sendKeys(mobileNumber);
  }

  getMobileNumberInput() {
    return this.mobileNumberInput.getAttribute('value');
  }

  setAlternateMobileNumberInput(alternateMobileNumber) {
    this.alternateMobileNumberInput.sendKeys(alternateMobileNumber);
  }

  getAlternateMobileNumberInput() {
    return this.alternateMobileNumberInput.getAttribute('value');
  }

  setEmailInput(email) {
    this.emailInput.sendKeys(email);
  }

  getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  setCourseApplyingForSelect(courseApplyingFor) {
    this.courseApplyingForSelect.sendKeys(courseApplyingFor);
  }

  getCourseApplyingForSelect() {
    return this.courseApplyingForSelect.element(by.css('option:checked')).getText();
  }

  courseApplyingForSelectLastOption() {
    this.courseApplyingForSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setModeOfEnquirySelect(modeOfEnquiry) {
    this.modeOfEnquirySelect.sendKeys(modeOfEnquiry);
  }

  getModeOfEnquirySelect() {
    return this.modeOfEnquirySelect.element(by.css('option:checked')).getText();
  }

  modeOfEnquirySelectLastOption() {
    this.modeOfEnquirySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setStatusSelect(status) {
    this.statusSelect.sendKeys(status);
  }

  getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  statusSelectLastOption() {
    this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setDescriptionInput(description) {
    this.descriptionInput.sendKeys(description);
  }

  getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  setEnquiryDateInput(enquiryDate) {
    this.enquiryDateInput.sendKeys(enquiryDate);
  }

  getEnquiryDateInput() {
    return this.enquiryDateInput.getAttribute('value');
  }

  setUpdatedOnInput(updatedOn) {
    this.updatedOnInput.sendKeys(updatedOn);
  }

  getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  setUpdatedByInput(updatedBy) {
    this.updatedByInput.sendKeys(updatedBy);
  }

  getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  branchSelectLastOption() {
    this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  branchSelectOption(option) {
    this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  admissionApplicationSelectLastOption() {
    this.admissionApplicationSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  admissionApplicationSelectOption(option) {
    this.admissionApplicationSelect.sendKeys(option);
  }

  getAdmissionApplicationSelect() {
    return this.admissionApplicationSelect;
  }

  getAdmissionApplicationSelectedOption() {
    return this.admissionApplicationSelect.element(by.css('option:checked')).getText();
  }

  save() {
    return this.saveButton.click();
  }

  cancel() {
    this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
