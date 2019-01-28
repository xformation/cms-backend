import { element, by, ElementFinder } from 'protractor';

export default class StudentUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.student.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  studentNameInput: ElementFinder = element(by.css('input#student-studentName'));
  studentMiddleNameInput: ElementFinder = element(by.css('input#student-studentMiddleName'));
  studentLastNameInput: ElementFinder = element(by.css('input#student-studentLastName'));
  fatherNameInput: ElementFinder = element(by.css('input#student-fatherName'));
  fatherMiddleNameInput: ElementFinder = element(by.css('input#student-fatherMiddleName'));
  fatherLastNameInput: ElementFinder = element(by.css('input#student-fatherLastName'));
  motherNameInput: ElementFinder = element(by.css('input#student-motherName'));
  motherMiddleNameInput: ElementFinder = element(by.css('input#student-motherMiddleName'));
  motherLastNameInput: ElementFinder = element(by.css('input#student-motherLastName'));
  aadharNoInput: ElementFinder = element(by.css('input#student-aadharNo'));
  dateOfBirthInput: ElementFinder = element(by.css('input#student-dateOfBirth'));
  placeOfBirthInput: ElementFinder = element(by.css('input#student-placeOfBirth'));
  religionInput: ElementFinder = element(by.css('input#student-religion'));
  casteInput: ElementFinder = element(by.css('input#student-caste'));
  subCasteInput: ElementFinder = element(by.css('input#student-subCaste'));
  ageInput: ElementFinder = element(by.css('input#student-age'));
  sexSelect: ElementFinder = element(by.css('select#student-sex'));
  bloodGroupInput: ElementFinder = element(by.css('input#student-bloodGroup'));
  addressLineOneInput: ElementFinder = element(by.css('input#student-addressLineOne'));
  addressLineTwoInput: ElementFinder = element(by.css('input#student-addressLineTwo'));
  addressLineThreeInput: ElementFinder = element(by.css('input#student-addressLineThree'));
  townInput: ElementFinder = element(by.css('input#student-town'));
  stateInput: ElementFinder = element(by.css('input#student-state'));
  countryInput: ElementFinder = element(by.css('input#student-country'));
  pincodeInput: ElementFinder = element(by.css('input#student-pincode'));
  studentContactNumberInput: ElementFinder = element(by.css('input#student-studentContactNumber'));
  alternateContactNumberInput: ElementFinder = element(by.css('input#student-alternateContactNumber'));
  studentEmailAddressInput: ElementFinder = element(by.css('input#student-studentEmailAddress'));
  alternateEmailAddressInput: ElementFinder = element(by.css('input#student-alternateEmailAddress'));
  relationWithStudentInput: ElementFinder = element(by.css('input#student-relationWithStudent'));
  nameInput: ElementFinder = element(by.css('input#student-name'));
  middleNameInput: ElementFinder = element(by.css('input#student-middleName'));
  lastNameInput: ElementFinder = element(by.css('input#student-lastName'));
  contactNoInput: ElementFinder = element(by.css('input#student-contactNo'));
  emailAddressInput: ElementFinder = element(by.css('input#student-emailAddress'));
  uploadPhotoInput: ElementFinder = element(by.css('input#student-uploadPhoto'));
  admissionNoInput: ElementFinder = element(by.css('input#student-admissionNo'));
  rollNoInput: ElementFinder = element(by.css('input#student-rollNo'));
  studentTypeInput: ElementFinder = element(by.css('input#student-studentType'));
  departmentSelect: ElementFinder = element(by.css('select#student-department'));
  batchSelect: ElementFinder = element(by.css('select#student-batch'));
  sectionSelect: ElementFinder = element(by.css('select#student-section'));
  branchSelect: ElementFinder = element(by.css('select#student-branch'));

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

  async setAadharNoInput(aadharNo) {
    await this.aadharNoInput.sendKeys(aadharNo);
  }

  async getAadharNoInput() {
    return this.aadharNoInput.getAttribute('value');
  }

  async setDateOfBirthInput(dateOfBirth) {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  async setPlaceOfBirthInput(placeOfBirth) {
    await this.placeOfBirthInput.sendKeys(placeOfBirth);
  }

  async getPlaceOfBirthInput() {
    return this.placeOfBirthInput.getAttribute('value');
  }

  async setReligionInput(religion) {
    await this.religionInput.sendKeys(religion);
  }

  async getReligionInput() {
    return this.religionInput.getAttribute('value');
  }

  async setCasteInput(caste) {
    await this.casteInput.sendKeys(caste);
  }

  async getCasteInput() {
    return this.casteInput.getAttribute('value');
  }

  async setSubCasteInput(subCaste) {
    await this.subCasteInput.sendKeys(subCaste);
  }

  async getSubCasteInput() {
    return this.subCasteInput.getAttribute('value');
  }

  async setAgeInput(age) {
    await this.ageInput.sendKeys(age);
  }

  async getAgeInput() {
    return this.ageInput.getAttribute('value');
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
  async setBloodGroupInput(bloodGroup) {
    await this.bloodGroupInput.sendKeys(bloodGroup);
  }

  async getBloodGroupInput() {
    return this.bloodGroupInput.getAttribute('value');
  }

  async setAddressLineOneInput(addressLineOne) {
    await this.addressLineOneInput.sendKeys(addressLineOne);
  }

  async getAddressLineOneInput() {
    return this.addressLineOneInput.getAttribute('value');
  }

  async setAddressLineTwoInput(addressLineTwo) {
    await this.addressLineTwoInput.sendKeys(addressLineTwo);
  }

  async getAddressLineTwoInput() {
    return this.addressLineTwoInput.getAttribute('value');
  }

  async setAddressLineThreeInput(addressLineThree) {
    await this.addressLineThreeInput.sendKeys(addressLineThree);
  }

  async getAddressLineThreeInput() {
    return this.addressLineThreeInput.getAttribute('value');
  }

  async setTownInput(town) {
    await this.townInput.sendKeys(town);
  }

  async getTownInput() {
    return this.townInput.getAttribute('value');
  }

  async setStateInput(state) {
    await this.stateInput.sendKeys(state);
  }

  async getStateInput() {
    return this.stateInput.getAttribute('value');
  }

  async setCountryInput(country) {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput() {
    return this.countryInput.getAttribute('value');
  }

  async setPincodeInput(pincode) {
    await this.pincodeInput.sendKeys(pincode);
  }

  async getPincodeInput() {
    return this.pincodeInput.getAttribute('value');
  }

  async setStudentContactNumberInput(studentContactNumber) {
    await this.studentContactNumberInput.sendKeys(studentContactNumber);
  }

  async getStudentContactNumberInput() {
    return this.studentContactNumberInput.getAttribute('value');
  }

  async setAlternateContactNumberInput(alternateContactNumber) {
    await this.alternateContactNumberInput.sendKeys(alternateContactNumber);
  }

  async getAlternateContactNumberInput() {
    return this.alternateContactNumberInput.getAttribute('value');
  }

  async setStudentEmailAddressInput(studentEmailAddress) {
    await this.studentEmailAddressInput.sendKeys(studentEmailAddress);
  }

  async getStudentEmailAddressInput() {
    return this.studentEmailAddressInput.getAttribute('value');
  }

  async setAlternateEmailAddressInput(alternateEmailAddress) {
    await this.alternateEmailAddressInput.sendKeys(alternateEmailAddress);
  }

  async getAlternateEmailAddressInput() {
    return this.alternateEmailAddressInput.getAttribute('value');
  }

  async setRelationWithStudentInput(relationWithStudent) {
    await this.relationWithStudentInput.sendKeys(relationWithStudent);
  }

  async getRelationWithStudentInput() {
    return this.relationWithStudentInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  async setMiddleNameInput(middleName) {
    await this.middleNameInput.sendKeys(middleName);
  }

  async getMiddleNameInput() {
    return this.middleNameInput.getAttribute('value');
  }

  async setLastNameInput(lastName) {
    await this.lastNameInput.sendKeys(lastName);
  }

  async getLastNameInput() {
    return this.lastNameInput.getAttribute('value');
  }

  async setContactNoInput(contactNo) {
    await this.contactNoInput.sendKeys(contactNo);
  }

  async getContactNoInput() {
    return this.contactNoInput.getAttribute('value');
  }

  async setEmailAddressInput(emailAddress) {
    await this.emailAddressInput.sendKeys(emailAddress);
  }

  async getEmailAddressInput() {
    return this.emailAddressInput.getAttribute('value');
  }

  async setUploadPhotoInput(uploadPhoto) {
    await this.uploadPhotoInput.sendKeys(uploadPhoto);
  }

  async getUploadPhotoInput() {
    return this.uploadPhotoInput.getAttribute('value');
  }

  async setAdmissionNoInput(admissionNo) {
    await this.admissionNoInput.sendKeys(admissionNo);
  }

  async getAdmissionNoInput() {
    return this.admissionNoInput.getAttribute('value');
  }

  async setRollNoInput(rollNo) {
    await this.rollNoInput.sendKeys(rollNo);
  }

  async getRollNoInput() {
    return this.rollNoInput.getAttribute('value');
  }

  async setStudentTypeInput(studentType) {
    await this.studentTypeInput.sendKeys(studentType);
  }

  async getStudentTypeInput() {
    return this.studentTypeInput.getAttribute('value');
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

  async sectionSelectLastOption() {
    await this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sectionSelectOption(option) {
    await this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  async getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
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
