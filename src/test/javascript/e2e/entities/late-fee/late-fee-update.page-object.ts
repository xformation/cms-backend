import { element, by, ElementFinder } from 'protractor';

export default class LateFeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.lateFee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  isAutoLateFeeInput: ElementFinder = element(by.css('input#late-fee-isAutoLateFee'));
  lateFeeDaysInput: ElementFinder = element(by.css('input#late-fee-lateFeeDays'));
  chargeTypeInput: ElementFinder = element(by.css('input#late-fee-chargeType'));
  fixedChargesInput: ElementFinder = element(by.css('input#late-fee-fixedCharges'));
  percentChargesInput: ElementFinder = element(by.css('input#late-fee-percentCharges'));
  lateFeeFrequencyInput: ElementFinder = element(by.css('input#late-fee-lateFeeFrequency'));
  lateFeeRepeatDaysInput: ElementFinder = element(by.css('input#late-fee-lateFeeRepeatDays'));
  collegeSelect: ElementFinder = element(by.css('select#late-fee-college'));
  branchSelect: ElementFinder = element(by.css('select#late-fee-branch'));
  academicYearSelect: ElementFinder = element(by.css('select#late-fee-academicYear'));
  termSelect: ElementFinder = element(by.css('select#late-fee-term'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIsAutoLateFeeInput(isAutoLateFee) {
    await this.isAutoLateFeeInput.sendKeys(isAutoLateFee);
  }

  async getIsAutoLateFeeInput() {
    return this.isAutoLateFeeInput.getAttribute('value');
  }

  async setLateFeeDaysInput(lateFeeDays) {
    await this.lateFeeDaysInput.sendKeys(lateFeeDays);
  }

  async getLateFeeDaysInput() {
    return this.lateFeeDaysInput.getAttribute('value');
  }

  async setChargeTypeInput(chargeType) {
    await this.chargeTypeInput.sendKeys(chargeType);
  }

  async getChargeTypeInput() {
    return this.chargeTypeInput.getAttribute('value');
  }

  async setFixedChargesInput(fixedCharges) {
    await this.fixedChargesInput.sendKeys(fixedCharges);
  }

  async getFixedChargesInput() {
    return this.fixedChargesInput.getAttribute('value');
  }

  async setPercentChargesInput(percentCharges) {
    await this.percentChargesInput.sendKeys(percentCharges);
  }

  async getPercentChargesInput() {
    return this.percentChargesInput.getAttribute('value');
  }

  async setLateFeeFrequencyInput(lateFeeFrequency) {
    await this.lateFeeFrequencyInput.sendKeys(lateFeeFrequency);
  }

  async getLateFeeFrequencyInput() {
    return this.lateFeeFrequencyInput.getAttribute('value');
  }

  async setLateFeeRepeatDaysInput(lateFeeRepeatDays) {
    await this.lateFeeRepeatDaysInput.sendKeys(lateFeeRepeatDays);
  }

  async getLateFeeRepeatDaysInput() {
    return this.lateFeeRepeatDaysInput.getAttribute('value');
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

  async termSelectLastOption() {
    await this.termSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async termSelectOption(option) {
    await this.termSelect.sendKeys(option);
  }

  getTermSelect() {
    return this.termSelect;
  }

  async getTermSelectedOption() {
    return this.termSelect.element(by.css('option:checked')).getText();
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
