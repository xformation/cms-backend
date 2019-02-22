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

  async setFeeParticularsNameInput(feeParticularsName) {
    await this.feeParticularsNameInput.sendKeys(feeParticularsName);
  }

  async getFeeParticularsNameInput() {
    return this.feeParticularsNameInput.getAttribute('value');
  }

  async setFeeParticularDescInput(feeParticularDesc) {
    await this.feeParticularDescInput.sendKeys(feeParticularDesc);
  }

  async getFeeParticularDescInput() {
    return this.feeParticularDescInput.getAttribute('value');
  }

  async setStudentTypeSelect(studentType) {
    await this.studentTypeSelect.sendKeys(studentType);
  }

  async getStudentTypeSelect() {
    return this.studentTypeSelect.element(by.css('option:checked')).getText();
  }

  async studentTypeSelectLastOption() {
    await this.studentTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setGenderSelect(gender) {
    await this.genderSelect.sendKeys(gender);
  }

  async getGenderSelect() {
    return this.genderSelect.element(by.css('option:checked')).getText();
  }

  async genderSelectLastOption() {
    await this.genderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setAmountInput(amount) {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput() {
    return this.amountInput.getAttribute('value');
  }

  async feeCategorySelectLastOption() {
    await this.feeCategorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async feeCategorySelectOption(option) {
    await this.feeCategorySelect.sendKeys(option);
  }

  getFeeCategorySelect() {
    return this.feeCategorySelect;
  }

  async getFeeCategorySelectedOption() {
    return this.feeCategorySelect.element(by.css('option:checked')).getText();
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

  async facilitySelectLastOption() {
    await this.facilitySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async facilitySelectOption(option) {
    await this.facilitySelect.sendKeys(option);
  }

  getFacilitySelect() {
    return this.facilitySelect;
  }

  async getFacilitySelectedOption() {
    return this.facilitySelect.element(by.css('option:checked')).getText();
  }

  async transportRouteSelectLastOption() {
    await this.transportRouteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async transportRouteSelectOption(option) {
    await this.transportRouteSelect.sendKeys(option);
  }

  getTransportRouteSelect() {
    return this.transportRouteSelect;
  }

  async getTransportRouteSelectedOption() {
    return this.transportRouteSelect.element(by.css('option:checked')).getText();
  }

  async collegeSelectLastOption() {
    await this.collegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async collegeSelectOption(option) {
    await this.collegeSelect.sendKeys(option);
  }

  getCollegeSelect() {
    return this.collegeSelect;
  }

  async getCollegeSelectedOption() {
    return this.collegeSelect.element(by.css('option:checked')).getText();
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

  async academicYearSelectLastOption() {
    await this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicYearSelectOption(option) {
    await this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  async getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
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
