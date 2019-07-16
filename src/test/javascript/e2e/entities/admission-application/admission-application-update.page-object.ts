import { element, by, ElementFinder } from 'protractor';

export default class AdmissionApplicationUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.admissionApplication.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  admissionStatusSelect: ElementFinder = element(by.css('select#admission-application-admissionStatus'));
  studentNameInput: ElementFinder = element(by.css('input#admission-application-studentName'));
  studentMiddleNameInput: ElementFinder = element(by.css('input#admission-application-studentMiddleName'));
  studentLastNameInput: ElementFinder = element(by.css('input#admission-application-studentLastName'));
  fatherNameInput: ElementFinder = element(by.css('input#admission-application-fatherName'));
  fatherMiddleNameInput: ElementFinder = element(by.css('input#admission-application-fatherMiddleName'));
  fatherLastNameInput: ElementFinder = element(by.css('input#admission-application-fatherLastName'));
  motherNameInput: ElementFinder = element(by.css('input#admission-application-motherName'));
  motherMiddleNameInput: ElementFinder = element(by.css('input#admission-application-motherMiddleName'));
  motherLastNameInput: ElementFinder = element(by.css('input#admission-application-motherLastName'));
  contactNumberInput: ElementFinder = element(by.css('input#admission-application-contactNumber'));
  alternateMobileNumberInput: ElementFinder = element(by.css('input#admission-application-alternateMobileNumber'));
  dateOfBirthInput: ElementFinder = element(by.css('input#admission-application-dateOfBirth'));
  emailInput: ElementFinder = element(by.css('input#admission-application-email'));
  sexSelect: ElementFinder = element(by.css('select#admission-application-sex'));
  commentsInput: ElementFinder = element(by.css('input#admission-application-comments'));
  applicationIdInput: ElementFinder = element(by.css('input#admission-application-applicationId'));
  uploadPhotoInput: ElementFinder = element(by.css('input#admission-application-uploadPhoto'));
  courseSelect: ElementFinder = element(by.css('select#admission-application-course'));
  admissionDateInput: ElementFinder = element(by.css('input#admission-application-admissionDate'));
  admissionEnquirySelect: ElementFinder = element(by.css('select#admission-application-admissionEnquiry'));
  academicHistorySelect: ElementFinder = element(by.css('select#admission-application-academicHistory'));
  documentsSelect: ElementFinder = element(by.css('select#admission-application-documents'));
  branchSelect: ElementFinder = element(by.css('select#admission-application-branch'));
  batchSelect: ElementFinder = element(by.css('select#admission-application-batch'));
  stateSelect: ElementFinder = element(by.css('select#admission-application-state'));
  citySelect: ElementFinder = element(by.css('select#admission-application-city'));
  countrySelect: ElementFinder = element(by.css('select#admission-application-country'));
  departmentSelect: ElementFinder = element(by.css('select#admission-application-department'));
  academicyearSelect: ElementFinder = element(by.css('select#admission-application-academicyear'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAdmissionStatusSelect(admissionStatus) {
    await this.admissionStatusSelect.sendKeys(admissionStatus);
  }

  async getAdmissionStatusSelect() {
    return this.admissionStatusSelect.element(by.css('option:checked')).getText();
  }

  async admissionStatusSelectLastOption() {
    await this.admissionStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setApplicationIdInput(applicationId) {
    await this.applicationIdInput.sendKeys(applicationId);
  }

  async getApplicationIdInput() {
    return this.applicationIdInput.getAttribute('value');
  }

  async setUploadPhotoInput(uploadPhoto) {
    await this.uploadPhotoInput.sendKeys(uploadPhoto);
  }

  async getUploadPhotoInput() {
    return this.uploadPhotoInput.getAttribute('value');
  }

  async setCourseSelect(course) {
    await this.courseSelect.sendKeys(course);
  }

  async getCourseSelect() {
    return this.courseSelect.element(by.css('option:checked')).getText();
  }

  async courseSelectLastOption() {
    await this.courseSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setAdmissionDateInput(admissionDate) {
    await this.admissionDateInput.sendKeys(admissionDate);
  }

  async getAdmissionDateInput() {
    return this.admissionDateInput.getAttribute('value');
  }

  async admissionEnquirySelectLastOption() {
    await this.admissionEnquirySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async admissionEnquirySelectOption(option) {
    await this.admissionEnquirySelect.sendKeys(option);
  }

  getAdmissionEnquirySelect() {
    return this.admissionEnquirySelect;
  }

  async getAdmissionEnquirySelectedOption() {
    return this.admissionEnquirySelect.element(by.css('option:checked')).getText();
  }

  async academicHistorySelectLastOption() {
    await this.academicHistorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicHistorySelectOption(option) {
    await this.academicHistorySelect.sendKeys(option);
  }

  getAcademicHistorySelect() {
    return this.academicHistorySelect;
  }

  async getAcademicHistorySelectedOption() {
    return this.academicHistorySelect.element(by.css('option:checked')).getText();
  }

  async documentsSelectLastOption() {
    await this.documentsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async documentsSelectOption(option) {
    await this.documentsSelect.sendKeys(option);
  }

  getDocumentsSelect() {
    return this.documentsSelect;
  }

  async getDocumentsSelectedOption() {
    return this.documentsSelect.element(by.css('option:checked')).getText();
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

  async academicyearSelectLastOption() {
    await this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicyearSelectOption(option) {
    await this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  async getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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
