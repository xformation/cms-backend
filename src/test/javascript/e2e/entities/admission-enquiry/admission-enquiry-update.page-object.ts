import { element, by, ElementFinder } from 'protractor';

export default class AdmissionEnquiryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.admissionEnquiry.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  studentNameInput: ElementFinder = element(by.css('input#admission-enquiry-studentName'));
  studentMiddleNameInput: ElementFinder = element(by.css('input#admission-enquiry-studentMiddleName'));
  studentLastNameInput: ElementFinder = element(by.css('input#admission-enquiry-studentLastName'));
  fatherNameInput: ElementFinder = element(by.css('input#admission-enquiry-fatherName'));
  fatherMiddleNameInput: ElementFinder = element(by.css('input#admission-enquiry-fatherMiddleName'));
  fatherLastNameInput: ElementFinder = element(by.css('input#admission-enquiry-fatherLastName'));
  motherNameInput: ElementFinder = element(by.css('input#admission-enquiry-motherName'));
  motherMiddleNameInput: ElementFinder = element(by.css('input#admission-enquiry-motherMiddleName'));
  motherLastNameInput: ElementFinder = element(by.css('input#admission-enquiry-motherLastName'));
  contactNumberInput: ElementFinder = element(by.css('input#admission-enquiry-contactNumber'));
  alternateMobileNumberInput: ElementFinder = element(by.css('input#admission-enquiry-alternateMobileNumber'));
  dateOfBirthInput: ElementFinder = element(by.css('input#admission-enquiry-dateOfBirth'));
  emailInput: ElementFinder = element(by.css('input#admission-enquiry-email'));
  sexSelect: ElementFinder = element(by.css('select#admission-enquiry-sex'));
  commentsInput: ElementFinder = element(by.css('input#admission-enquiry-comments'));
  courseApplyingForSelect: ElementFinder = element(by.css('select#admission-enquiry-courseApplyingFor'));
  highestQualificationInput: ElementFinder = element(by.css('input#admission-enquiry-highestQualification'));
  modeOfEnquirySelect: ElementFinder = element(by.css('select#admission-enquiry-modeOfEnquiry'));
  statusSelect: ElementFinder = element(by.css('select#admission-enquiry-status'));
  descriptionInput: ElementFinder = element(by.css('input#admission-enquiry-description'));
  enquiryDateInput: ElementFinder = element(by.css('input#admission-enquiry-enquiryDate'));
  updatedOnInput: ElementFinder = element(by.css('input#admission-enquiry-updatedOn'));
  updatedByInput: ElementFinder = element(by.css('input#admission-enquiry-updatedBy'));
  branchSelect: ElementFinder = element(by.css('select#admission-enquiry-branch'));
  departmentSelect: ElementFinder = element(by.css('select#admission-enquiry-department'));
  batchSelect: ElementFinder = element(by.css('select#admission-enquiry-batch'));
  stateSelect: ElementFinder = element(by.css('select#admission-enquiry-state'));
  citySelect: ElementFinder = element(by.css('select#admission-enquiry-city'));
  countrySelect: ElementFinder = element(by.css('select#admission-enquiry-country'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setStudentNameInput(studentName) {
    await this.studentNameInput.sendKeys(studentName);
  }

  async getStudentNameInput() {
    return this.studentNameInput.getAttribute('value');
  }

  async setStudentMiddleNameInput(studentMiddleName) {
    await this.studentMiddleNameInput.sendKeys(studentMiddleName);
  }

  async getStudentMiddleNameInput() {
    return this.studentMiddleNameInput.getAttribute('value');
  }

  async setStudentLastNameInput(studentLastName) {
    await this.studentLastNameInput.sendKeys(studentLastName);
  }

  async getStudentLastNameInput() {
    return this.studentLastNameInput.getAttribute('value');
  }

  async setFatherNameInput(fatherName) {
    await this.fatherNameInput.sendKeys(fatherName);
  }

  async getFatherNameInput() {
    return this.fatherNameInput.getAttribute('value');
  }

  async setFatherMiddleNameInput(fatherMiddleName) {
    await this.fatherMiddleNameInput.sendKeys(fatherMiddleName);
  }

  async getFatherMiddleNameInput() {
    return this.fatherMiddleNameInput.getAttribute('value');
  }

  async setFatherLastNameInput(fatherLastName) {
    await this.fatherLastNameInput.sendKeys(fatherLastName);
  }

  async getFatherLastNameInput() {
    return this.fatherLastNameInput.getAttribute('value');
  }

  async setMotherNameInput(motherName) {
    await this.motherNameInput.sendKeys(motherName);
  }

  async getMotherNameInput() {
    return this.motherNameInput.getAttribute('value');
  }

  async setMotherMiddleNameInput(motherMiddleName) {
    await this.motherMiddleNameInput.sendKeys(motherMiddleName);
  }

  async getMotherMiddleNameInput() {
    return this.motherMiddleNameInput.getAttribute('value');
  }

  async setMotherLastNameInput(motherLastName) {
    await this.motherLastNameInput.sendKeys(motherLastName);
  }

  async getMotherLastNameInput() {
    return this.motherLastNameInput.getAttribute('value');
  }

  async setContactNumberInput(contactNumber) {
    await this.contactNumberInput.sendKeys(contactNumber);
  }

  async getContactNumberInput() {
    return this.contactNumberInput.getAttribute('value');
  }

  async setAlternateMobileNumberInput(alternateMobileNumber) {
    await this.alternateMobileNumberInput.sendKeys(alternateMobileNumber);
  }

  async getAlternateMobileNumberInput() {
    return this.alternateMobileNumberInput.getAttribute('value');
  }

  async setDateOfBirthInput(dateOfBirth) {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setSexSelect(sex) {
    await this.sexSelect.sendKeys(sex);
  }

  async getSexSelect() {
    return this.sexSelect.element(by.css('option:checked')).getText();
  }

  async sexSelectLastOption() {
    await this.sexSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
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
  async setHighestQualificationInput(highestQualification) {
    await this.highestQualificationInput.sendKeys(highestQualification);
  }

  async getHighestQualificationInput() {
    return this.highestQualificationInput.getAttribute('value');
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

  async departmentSelectLastOption() {
    await this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentSelectOption(option) {
    await this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  async getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  async batchSelectLastOption() {
    await this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async batchSelectOption(option) {
    await this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  async getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
  }

  async stateSelectLastOption() {
    await this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async stateSelectOption(option) {
    await this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  async getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
  }

  async citySelectLastOption() {
    await this.citySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async citySelectOption(option) {
    await this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  async getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
  }

  async countrySelectLastOption() {
    await this.countrySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async countrySelectOption(option) {
    await this.countrySelect.sendKeys(option);
  }

  getCountrySelect() {
    return this.countrySelect;
  }

  async getCountrySelectedOption() {
    return this.countrySelect.element(by.css('option:checked')).getText();
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
