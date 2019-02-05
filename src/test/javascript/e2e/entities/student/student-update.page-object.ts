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
  religionSelect: ElementFinder = element(by.css('select#student-religion'));
  casteSelect: ElementFinder = element(by.css('select#student-caste'));
  subCasteInput: ElementFinder = element(by.css('input#student-subCaste'));
  ageInput: ElementFinder = element(by.css('input#student-age'));
  sexSelect: ElementFinder = element(by.css('select#student-sex'));
  bloodGroupSelect: ElementFinder = element(by.css('select#student-bloodGroup'));
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
  relationWithStudentSelect: ElementFinder = element(by.css('select#student-relationWithStudent'));
  nameInput: ElementFinder = element(by.css('input#student-name'));
  middleNameInput: ElementFinder = element(by.css('input#student-middleName'));
  lastNameInput: ElementFinder = element(by.css('input#student-lastName'));
  contactNoInput: ElementFinder = element(by.css('input#student-contactNo'));
  emailAddressInput: ElementFinder = element(by.css('input#student-emailAddress'));
  transportSelect: ElementFinder = element(by.css('select#student-transport'));
  messSelect: ElementFinder = element(by.css('select#student-mess'));
  gymSelect: ElementFinder = element(by.css('select#student-gym'));
  culturalClassSelect: ElementFinder = element(by.css('select#student-culturalClass'));
  librarySelect: ElementFinder = element(by.css('select#student-library'));
  sportsSelect: ElementFinder = element(by.css('select#student-sports'));
  swimmingSelect: ElementFinder = element(by.css('select#student-swimming'));
  extraClassSelect: ElementFinder = element(by.css('select#student-extraClass'));
  handicraftsSelect: ElementFinder = element(by.css('select#student-handicrafts'));
  addSelect: ElementFinder = element(by.css('select#student-add'));
  uploadPhotoInput: ElementFinder = element(by.css('input#student-uploadPhoto'));
  admissionNoInput: ElementFinder = element(by.css('input#student-admissionNo'));
  rollNoInput: ElementFinder = element(by.css('input#student-rollNo'));
  studentTypeSelect: ElementFinder = element(by.css('select#student-studentType'));
  departmentSelect: ElementFinder = element(by.css('select#student-department'));
  batchSelect: ElementFinder = element(by.css('select#student-batch'));
  sectionSelect: ElementFinder = element(by.css('select#student-section'));
  branchSelect: ElementFinder = element(by.css('select#student-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  setStudentNameInput(studentName) {
    this.studentNameInput.sendKeys(studentName);
  }

  getStudentNameInput() {
    return this.studentNameInput.getAttribute('value');
  }

  setStudentMiddleNameInput(studentMiddleName) {
    this.studentMiddleNameInput.sendKeys(studentMiddleName);
  }

  getStudentMiddleNameInput() {
    return this.studentMiddleNameInput.getAttribute('value');
  }

  setStudentLastNameInput(studentLastName) {
    this.studentLastNameInput.sendKeys(studentLastName);
  }

  getStudentLastNameInput() {
    return this.studentLastNameInput.getAttribute('value');
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

  setStudentContactNumberInput(studentContactNumber) {
    this.studentContactNumberInput.sendKeys(studentContactNumber);
  }

  getStudentContactNumberInput() {
    return this.studentContactNumberInput.getAttribute('value');
  }

  setAlternateContactNumberInput(alternateContactNumber) {
    this.alternateContactNumberInput.sendKeys(alternateContactNumber);
  }

  getAlternateContactNumberInput() {
    return this.alternateContactNumberInput.getAttribute('value');
  }

  setStudentEmailAddressInput(studentEmailAddress) {
    this.studentEmailAddressInput.sendKeys(studentEmailAddress);
  }

  getStudentEmailAddressInput() {
    return this.studentEmailAddressInput.getAttribute('value');
  }

  setAlternateEmailAddressInput(alternateEmailAddress) {
    this.alternateEmailAddressInput.sendKeys(alternateEmailAddress);
  }

  getAlternateEmailAddressInput() {
    return this.alternateEmailAddressInput.getAttribute('value');
  }

  setRelationWithStudentSelect(relationWithStudent) {
    this.relationWithStudentSelect.sendKeys(relationWithStudent);
  }

  getRelationWithStudentSelect() {
    return this.relationWithStudentSelect.element(by.css('option:checked')).getText();
  }

  relationWithStudentSelectLastOption() {
    this.relationWithStudentSelect
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

  setTransportSelect(transport) {
    this.transportSelect.sendKeys(transport);
  }

  getTransportSelect() {
    return this.transportSelect.element(by.css('option:checked')).getText();
  }

  transportSelectLastOption() {
    this.transportSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setMessSelect(mess) {
    this.messSelect.sendKeys(mess);
  }

  getMessSelect() {
    return this.messSelect.element(by.css('option:checked')).getText();
  }

  messSelectLastOption() {
    this.messSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setGymSelect(gym) {
    this.gymSelect.sendKeys(gym);
  }

  getGymSelect() {
    return this.gymSelect.element(by.css('option:checked')).getText();
  }

  gymSelectLastOption() {
    this.gymSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCulturalClassSelect(culturalClass) {
    this.culturalClassSelect.sendKeys(culturalClass);
  }

  getCulturalClassSelect() {
    return this.culturalClassSelect.element(by.css('option:checked')).getText();
  }

  culturalClassSelectLastOption() {
    this.culturalClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLibrarySelect(library) {
    this.librarySelect.sendKeys(library);
  }

  getLibrarySelect() {
    return this.librarySelect.element(by.css('option:checked')).getText();
  }

  librarySelectLastOption() {
    this.librarySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSportsSelect(sports) {
    this.sportsSelect.sendKeys(sports);
  }

  getSportsSelect() {
    return this.sportsSelect.element(by.css('option:checked')).getText();
  }

  sportsSelectLastOption() {
    this.sportsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSwimmingSelect(swimming) {
    this.swimmingSelect.sendKeys(swimming);
  }

  getSwimmingSelect() {
    return this.swimmingSelect.element(by.css('option:checked')).getText();
  }

  swimmingSelectLastOption() {
    this.swimmingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setExtraClassSelect(extraClass) {
    this.extraClassSelect.sendKeys(extraClass);
  }

  getExtraClassSelect() {
    return this.extraClassSelect.element(by.css('option:checked')).getText();
  }

  extraClassSelectLastOption() {
    this.extraClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setHandicraftsSelect(handicrafts) {
    this.handicraftsSelect.sendKeys(handicrafts);
  }

  getHandicraftsSelect() {
    return this.handicraftsSelect.element(by.css('option:checked')).getText();
  }

  handicraftsSelectLastOption() {
    this.handicraftsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setAddSelect(add) {
    this.addSelect.sendKeys(add);
  }

  getAddSelect() {
    return this.addSelect.element(by.css('option:checked')).getText();
  }

  addSelectLastOption() {
    this.addSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setUploadPhotoInput(uploadPhoto) {
    this.uploadPhotoInput.sendKeys(uploadPhoto);
  }

  getUploadPhotoInput() {
    return this.uploadPhotoInput.getAttribute('value');
  }

  setAdmissionNoInput(admissionNo) {
    this.admissionNoInput.sendKeys(admissionNo);
  }

  getAdmissionNoInput() {
    return this.admissionNoInput.getAttribute('value');
  }

  setRollNoInput(rollNo) {
    this.rollNoInput.sendKeys(rollNo);
  }

  getRollNoInput() {
    return this.rollNoInput.getAttribute('value');
  }

  setStudentTypeSelect(studentType) {
    this.studentTypeSelect.sendKeys(studentType);
  }

  getStudentTypeSelect() {
    return this.studentTypeSelect.element(by.css('option:checked')).getText();
  }

  studentTypeSelectLastOption() {
    this.studentTypeSelect
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

  batchSelectLastOption() {
    this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  batchSelectOption(option) {
    this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
  }

  sectionSelectLastOption() {
    this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  sectionSelectOption(option) {
    this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
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
