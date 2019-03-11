import { element, by, ElementFinder } from 'protractor';

export default class FeeDetailsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.feeDetails.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  feeParticularsNameInput: ElementFinder = element(by.css('input#fee-details-feeParticularsName'));
  feeParticularDescInput: ElementFinder = element(by.css('input#fee-details-feeParticularDesc'));
  studentTypeSelect: ElementFinder = element(by.css('select#fee-details-studentType'));
  genderSelect: ElementFinder = element(by.css('select#fee-details-gender'));
  amountInput: ElementFinder = element(by.css('input#fee-details-amount'));
  feeCategorySelect: ElementFinder = element(by.css('select#fee-details-feeCategory'));
  batchSelect: ElementFinder = element(by.css('select#fee-details-batch'));
  facilitySelect: ElementFinder = element(by.css('select#fee-details-facility'));
  transportRouteSelect: ElementFinder = element(by.css('select#fee-details-transportRoute'));
  collegeSelect: ElementFinder = element(by.css('select#fee-details-college'));
  departmentSelect: ElementFinder = element(by.css('select#fee-details-department'));
  branchSelect: ElementFinder = element(by.css('select#fee-details-branch'));
  academicYearSelect: ElementFinder = element(by.css('select#fee-details-academicYear'));

  getPageTitle() {
    return this.pageTitle;
  }

  setFeeParticularsNameInput(feeParticularsName) {
    this.feeParticularsNameInput.sendKeys(feeParticularsName);
  }

  getFeeParticularsNameInput() {
    return this.feeParticularsNameInput.getAttribute('value');
  }

  setFeeParticularDescInput(feeParticularDesc) {
    this.feeParticularDescInput.sendKeys(feeParticularDesc);
  }

  getFeeParticularDescInput() {
    return this.feeParticularDescInput.getAttribute('value');
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
  setGenderSelect(gender) {
    this.genderSelect.sendKeys(gender);
  }

  getGenderSelect() {
    return this.genderSelect.element(by.css('option:checked')).getText();
  }

  genderSelectLastOption() {
    this.genderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setAmountInput(amount) {
    this.amountInput.sendKeys(amount);
  }

  getAmountInput() {
    return this.amountInput.getAttribute('value');
  }

  feeCategorySelectLastOption() {
    this.feeCategorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  feeCategorySelectOption(option) {
    this.feeCategorySelect.sendKeys(option);
  }

  getFeeCategorySelect() {
    return this.feeCategorySelect;
  }

  getFeeCategorySelectedOption() {
    return this.feeCategorySelect.element(by.css('option:checked')).getText();
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

  facilitySelectLastOption() {
    this.facilitySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  facilitySelectOption(option) {
    this.facilitySelect.sendKeys(option);
  }

  getFacilitySelect() {
    return this.facilitySelect;
  }

  getFacilitySelectedOption() {
    return this.facilitySelect.element(by.css('option:checked')).getText();
  }

  transportRouteSelectLastOption() {
    this.transportRouteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  transportRouteSelectOption(option) {
    this.transportRouteSelect.sendKeys(option);
  }

  getTransportRouteSelect() {
    return this.transportRouteSelect;
  }

  getTransportRouteSelectedOption() {
    return this.transportRouteSelect.element(by.css('option:checked')).getText();
  }

  collegeSelectLastOption() {
    this.collegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  collegeSelectOption(option) {
    this.collegeSelect.sendKeys(option);
  }

  getCollegeSelect() {
    return this.collegeSelect;
  }

  getCollegeSelectedOption() {
    return this.collegeSelect.element(by.css('option:checked')).getText();
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

  academicYearSelectLastOption() {
    this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicYearSelectOption(option) {
    this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
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
