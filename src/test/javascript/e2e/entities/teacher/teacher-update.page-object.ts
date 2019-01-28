import { element, by, ElementFinder } from 'protractor';

export default class TeacherUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.teacher.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  teacherNameInput: ElementFinder = element(by.css('input#teacher-teacherName'));
  teacherMiddleNameInput: ElementFinder = element(by.css('input#teacher-teacherMiddleName'));
  teacherLastNameInput: ElementFinder = element(by.css('input#teacher-teacherLastName'));
  fatherNameInput: ElementFinder = element(by.css('input#teacher-fatherName'));
  fatherMiddleNameInput: ElementFinder = element(by.css('input#teacher-fatherMiddleName'));
  fatherLastNameInput: ElementFinder = element(by.css('input#teacher-fatherLastName'));
  motherNameInput: ElementFinder = element(by.css('input#teacher-motherName'));
  motherMiddleNameInput: ElementFinder = element(by.css('input#teacher-motherMiddleName'));
  motherLastNameInput: ElementFinder = element(by.css('input#teacher-motherLastName'));
  aadharNoInput: ElementFinder = element(by.css('input#teacher-aadharNo'));
  dateOfBirthInput: ElementFinder = element(by.css('input#teacher-dateOfBirth'));
  placeOfBirthInput: ElementFinder = element(by.css('input#teacher-placeOfBirth'));
  religionSelect: ElementFinder = element(by.css('select#teacher-religion'));
  casteSelect: ElementFinder = element(by.css('select#teacher-caste'));
  subCasteInput: ElementFinder = element(by.css('input#teacher-subCaste'));
  ageInput: ElementFinder = element(by.css('input#teacher-age'));
  sexSelect: ElementFinder = element(by.css('select#teacher-sex'));
  bloodGroupSelect: ElementFinder = element(by.css('select#teacher-bloodGroup'));
  addressLineOneInput: ElementFinder = element(by.css('input#teacher-addressLineOne'));
  addressLineTwoInput: ElementFinder = element(by.css('input#teacher-addressLineTwo'));
  addressLineThreeInput: ElementFinder = element(by.css('input#teacher-addressLineThree'));
  townInput: ElementFinder = element(by.css('input#teacher-town'));
  stateInput: ElementFinder = element(by.css('input#teacher-state'));
  countryInput: ElementFinder = element(by.css('input#teacher-country'));
  pincodeInput: ElementFinder = element(by.css('input#teacher-pincode'));
  teacherContactNumberInput: ElementFinder = element(by.css('input#teacher-teacherContactNumber'));
  alternateContactNumberInput: ElementFinder = element(by.css('input#teacher-alternateContactNumber'));
  teacherEmailAddressInput: ElementFinder = element(by.css('input#teacher-teacherEmailAddress'));
  alternateEmailAddressInput: ElementFinder = element(by.css('input#teacher-alternateEmailAddress'));
  relationWithStaffSelect: ElementFinder = element(by.css('select#teacher-relationWithStaff'));
  nameInput: ElementFinder = element(by.css('input#teacher-name'));
  middleNameInput: ElementFinder = element(by.css('input#teacher-middleName'));
  lastNameInput: ElementFinder = element(by.css('input#teacher-lastName'));
  contactNoInput: ElementFinder = element(by.css('input#teacher-contactNo'));
  emailAddressInput: ElementFinder = element(by.css('input#teacher-emailAddress'));
  uploadPhotoInput: ElementFinder = element(by.css('input#teacher-uploadPhoto'));
  employeeIdInput: ElementFinder = element(by.css('input#teacher-employeeId'));
  designationInput: ElementFinder = element(by.css('input#teacher-designation'));
  staffTypeSelect: ElementFinder = element(by.css('select#teacher-staffType'));
  departmentSelect: ElementFinder = element(by.css('select#teacher-department'));
  branchSelect: ElementFinder = element(by.css('select#teacher-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTeacherNameInput(teacherName) {
    await this.teacherNameInput.sendKeys(teacherName);
  }

  async getTeacherNameInput() {
    return this.teacherNameInput.getAttribute('value');
  }

  async setTeacherMiddleNameInput(teacherMiddleName) {
    await this.teacherMiddleNameInput.sendKeys(teacherMiddleName);
  }

  async getTeacherMiddleNameInput() {
    return this.teacherMiddleNameInput.getAttribute('value');
  }

  async setTeacherLastNameInput(teacherLastName) {
    await this.teacherLastNameInput.sendKeys(teacherLastName);
  }

  async getTeacherLastNameInput() {
    return this.teacherLastNameInput.getAttribute('value');
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

  async setReligionSelect(religion) {
    await this.religionSelect.sendKeys(religion);
  }

  async getReligionSelect() {
    return this.religionSelect.element(by.css('option:checked')).getText();
  }

  async religionSelectLastOption() {
    await this.religionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCasteSelect(caste) {
    await this.casteSelect.sendKeys(caste);
  }

  async getCasteSelect() {
    return this.casteSelect.element(by.css('option:checked')).getText();
  }

  async casteSelectLastOption() {
    await this.casteSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
  async setBloodGroupSelect(bloodGroup) {
    await this.bloodGroupSelect.sendKeys(bloodGroup);
  }

  async getBloodGroupSelect() {
    return this.bloodGroupSelect.element(by.css('option:checked')).getText();
  }

  async bloodGroupSelectLastOption() {
    await this.bloodGroupSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setTeacherContactNumberInput(teacherContactNumber) {
    await this.teacherContactNumberInput.sendKeys(teacherContactNumber);
  }

  async getTeacherContactNumberInput() {
    return this.teacherContactNumberInput.getAttribute('value');
  }

  async setAlternateContactNumberInput(alternateContactNumber) {
    await this.alternateContactNumberInput.sendKeys(alternateContactNumber);
  }

  async getAlternateContactNumberInput() {
    return this.alternateContactNumberInput.getAttribute('value');
  }

  async setTeacherEmailAddressInput(teacherEmailAddress) {
    await this.teacherEmailAddressInput.sendKeys(teacherEmailAddress);
  }

  async getTeacherEmailAddressInput() {
    return this.teacherEmailAddressInput.getAttribute('value');
  }

  async setAlternateEmailAddressInput(alternateEmailAddress) {
    await this.alternateEmailAddressInput.sendKeys(alternateEmailAddress);
  }

  async getAlternateEmailAddressInput() {
    return this.alternateEmailAddressInput.getAttribute('value');
  }

  async setRelationWithStaffSelect(relationWithStaff) {
    await this.relationWithStaffSelect.sendKeys(relationWithStaff);
  }

  async getRelationWithStaffSelect() {
    return this.relationWithStaffSelect.element(by.css('option:checked')).getText();
  }

  async relationWithStaffSelectLastOption() {
    await this.relationWithStaffSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setEmployeeIdInput(employeeId) {
    await this.employeeIdInput.sendKeys(employeeId);
  }

  async getEmployeeIdInput() {
    return this.employeeIdInput.getAttribute('value');
  }

  async setDesignationInput(designation) {
    await this.designationInput.sendKeys(designation);
  }

  async getDesignationInput() {
    return this.designationInput.getAttribute('value');
  }

  async setStaffTypeSelect(staffType) {
    await this.staffTypeSelect.sendKeys(staffType);
  }

  async getStaffTypeSelect() {
    return this.staffTypeSelect.element(by.css('option:checked')).getText();
  }

  async staffTypeSelectLastOption() {
    await this.staffTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
