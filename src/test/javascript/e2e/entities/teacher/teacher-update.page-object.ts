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

  setTeacherNameInput(teacherName) {
    this.teacherNameInput.sendKeys(teacherName);
  }

  getTeacherNameInput() {
    return this.teacherNameInput.getAttribute('value');
  }

  setTeacherMiddleNameInput(teacherMiddleName) {
    this.teacherMiddleNameInput.sendKeys(teacherMiddleName);
  }

  getTeacherMiddleNameInput() {
    return this.teacherMiddleNameInput.getAttribute('value');
  }

  setTeacherLastNameInput(teacherLastName) {
    this.teacherLastNameInput.sendKeys(teacherLastName);
  }

  getTeacherLastNameInput() {
    return this.teacherLastNameInput.getAttribute('value');
  }

  setFatherNameInput(fatherName) {
    this.fatherNameInput.sendKeys(fatherName);
  }

  getFatherNameInput() {
    return this.fatherNameInput.getAttribute('value');
  }

  setFatherMiddleNameInput(fatherMiddleName) {
    this.fatherMiddleNameInput.sendKeys(fatherMiddleName);
  }

  getFatherMiddleNameInput() {
    return this.fatherMiddleNameInput.getAttribute('value');
  }

  setFatherLastNameInput(fatherLastName) {
    this.fatherLastNameInput.sendKeys(fatherLastName);
  }

  getFatherLastNameInput() {
    return this.fatherLastNameInput.getAttribute('value');
  }

  setMotherNameInput(motherName) {
    this.motherNameInput.sendKeys(motherName);
  }

  getMotherNameInput() {
    return this.motherNameInput.getAttribute('value');
  }

  setMotherMiddleNameInput(motherMiddleName) {
    this.motherMiddleNameInput.sendKeys(motherMiddleName);
  }

  getMotherMiddleNameInput() {
    return this.motherMiddleNameInput.getAttribute('value');
  }

  setMotherLastNameInput(motherLastName) {
    this.motherLastNameInput.sendKeys(motherLastName);
  }

  getMotherLastNameInput() {
    return this.motherLastNameInput.getAttribute('value');
  }

  setAadharNoInput(aadharNo) {
    this.aadharNoInput.sendKeys(aadharNo);
  }

  getAadharNoInput() {
    return this.aadharNoInput.getAttribute('value');
  }

  setDateOfBirthInput(dateOfBirth) {
    this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  setPlaceOfBirthInput(placeOfBirth) {
    this.placeOfBirthInput.sendKeys(placeOfBirth);
  }

  getPlaceOfBirthInput() {
    return this.placeOfBirthInput.getAttribute('value');
  }

  setReligionSelect(religion) {
    this.religionSelect.sendKeys(religion);
  }

  getReligionSelect() {
    return this.religionSelect.element(by.css('option:checked')).getText();
  }

  religionSelectLastOption() {
    this.religionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCasteSelect(caste) {
    this.casteSelect.sendKeys(caste);
  }

  getCasteSelect() {
    return this.casteSelect.element(by.css('option:checked')).getText();
  }

  casteSelectLastOption() {
    this.casteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSubCasteInput(subCaste) {
    this.subCasteInput.sendKeys(subCaste);
  }

  getSubCasteInput() {
    return this.subCasteInput.getAttribute('value');
  }

  setAgeInput(age) {
    this.ageInput.sendKeys(age);
  }

  getAgeInput() {
    return this.ageInput.getAttribute('value');
  }

  setSexSelect(sex) {
    this.sexSelect.sendKeys(sex);
  }

  getSexSelect() {
    return this.sexSelect.element(by.css('option:checked')).getText();
  }

  sexSelectLastOption() {
    this.sexSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setBloodGroupSelect(bloodGroup) {
    this.bloodGroupSelect.sendKeys(bloodGroup);
  }

  getBloodGroupSelect() {
    return this.bloodGroupSelect.element(by.css('option:checked')).getText();
  }

  bloodGroupSelectLastOption() {
    this.bloodGroupSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setAddressLineOneInput(addressLineOne) {
    this.addressLineOneInput.sendKeys(addressLineOne);
  }

  getAddressLineOneInput() {
    return this.addressLineOneInput.getAttribute('value');
  }

  setAddressLineTwoInput(addressLineTwo) {
    this.addressLineTwoInput.sendKeys(addressLineTwo);
  }

  getAddressLineTwoInput() {
    return this.addressLineTwoInput.getAttribute('value');
  }

  setAddressLineThreeInput(addressLineThree) {
    this.addressLineThreeInput.sendKeys(addressLineThree);
  }

  getAddressLineThreeInput() {
    return this.addressLineThreeInput.getAttribute('value');
  }

  setTownInput(town) {
    this.townInput.sendKeys(town);
  }

  getTownInput() {
    return this.townInput.getAttribute('value');
  }

  setStateInput(state) {
    this.stateInput.sendKeys(state);
  }

  getStateInput() {
    return this.stateInput.getAttribute('value');
  }

  setCountryInput(country) {
    this.countryInput.sendKeys(country);
  }

  getCountryInput() {
    return this.countryInput.getAttribute('value');
  }

  setPincodeInput(pincode) {
    this.pincodeInput.sendKeys(pincode);
  }

  getPincodeInput() {
    return this.pincodeInput.getAttribute('value');
  }

  setTeacherContactNumberInput(teacherContactNumber) {
    this.teacherContactNumberInput.sendKeys(teacherContactNumber);
  }

  getTeacherContactNumberInput() {
    return this.teacherContactNumberInput.getAttribute('value');
  }

  setAlternateContactNumberInput(alternateContactNumber) {
    this.alternateContactNumberInput.sendKeys(alternateContactNumber);
  }

  getAlternateContactNumberInput() {
    return this.alternateContactNumberInput.getAttribute('value');
  }

  setTeacherEmailAddressInput(teacherEmailAddress) {
    this.teacherEmailAddressInput.sendKeys(teacherEmailAddress);
  }

  getTeacherEmailAddressInput() {
    return this.teacherEmailAddressInput.getAttribute('value');
  }

  setAlternateEmailAddressInput(alternateEmailAddress) {
    this.alternateEmailAddressInput.sendKeys(alternateEmailAddress);
  }

  getAlternateEmailAddressInput() {
    return this.alternateEmailAddressInput.getAttribute('value');
  }

  setRelationWithStaffSelect(relationWithStaff) {
    this.relationWithStaffSelect.sendKeys(relationWithStaff);
  }

  getRelationWithStaffSelect() {
    return this.relationWithStaffSelect.element(by.css('option:checked')).getText();
  }

  relationWithStaffSelectLastOption() {
    this.relationWithStaffSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setNameInput(name) {
    this.nameInput.sendKeys(name);
  }

  getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  setMiddleNameInput(middleName) {
    this.middleNameInput.sendKeys(middleName);
  }

  getMiddleNameInput() {
    return this.middleNameInput.getAttribute('value');
  }

  setLastNameInput(lastName) {
    this.lastNameInput.sendKeys(lastName);
  }

  getLastNameInput() {
    return this.lastNameInput.getAttribute('value');
  }

  setContactNoInput(contactNo) {
    this.contactNoInput.sendKeys(contactNo);
  }

  getContactNoInput() {
    return this.contactNoInput.getAttribute('value');
  }

  setEmailAddressInput(emailAddress) {
    this.emailAddressInput.sendKeys(emailAddress);
  }

  getEmailAddressInput() {
    return this.emailAddressInput.getAttribute('value');
  }

  setUploadPhotoInput(uploadPhoto) {
    this.uploadPhotoInput.sendKeys(uploadPhoto);
  }

  getUploadPhotoInput() {
    return this.uploadPhotoInput.getAttribute('value');
  }

  setEmployeeIdInput(employeeId) {
    this.employeeIdInput.sendKeys(employeeId);
  }

  getEmployeeIdInput() {
    return this.employeeIdInput.getAttribute('value');
  }

  setDesignationInput(designation) {
    this.designationInput.sendKeys(designation);
  }

  getDesignationInput() {
    return this.designationInput.getAttribute('value');
  }

  setStaffTypeSelect(staffType) {
    this.staffTypeSelect.sendKeys(staffType);
  }

  getStaffTypeSelect() {
    return this.staffTypeSelect.element(by.css('option:checked')).getText();
  }

  staffTypeSelectLastOption() {
    this.staffTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
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
