import { element, by, ElementFinder } from 'protractor';

export default class LateFeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.lateFee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  assignLateFeeSelect: ElementFinder = element(by.css('select#late-fee-assignLateFee'));
  lateFeeDaysInput: ElementFinder = element(by.css('input#late-fee-lateFeeDays'));
  fixedSelect: ElementFinder = element(by.css('select#late-fee-fixed'));
  percentageSelect: ElementFinder = element(by.css('select#late-fee-percentage'));
  fixedChargesInput: ElementFinder = element(by.css('input#late-fee-fixedCharges'));
  percentChargesInput: ElementFinder = element(by.css('input#late-fee-percentCharges'));
  lateFeeAssignmentFrequencySelect: ElementFinder = element(by.css('select#late-fee-lateFeeAssignmentFrequency'));
  collegeSelect: ElementFinder = element(by.css('select#late-fee-college'));
  branchSelect: ElementFinder = element(by.css('select#late-fee-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAssignLateFeeSelect(assignLateFee) {
    await this.assignLateFeeSelect.sendKeys(assignLateFee);
  }

  async getAssignLateFeeSelect() {
    return this.assignLateFeeSelect.element(by.css('option:checked')).getText();
  }

  async assignLateFeeSelectLastOption() {
    await this.assignLateFeeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setLateFeeDaysInput(lateFeeDays) {
    await this.lateFeeDaysInput.sendKeys(lateFeeDays);
  }

  async getLateFeeDaysInput() {
    return this.lateFeeDaysInput.getAttribute('value');
  }

  async setFixedSelect(fixed) {
    await this.fixedSelect.sendKeys(fixed);
  }

  async getFixedSelect() {
    return this.fixedSelect.element(by.css('option:checked')).getText();
  }

  async fixedSelectLastOption() {
    await this.fixedSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setPercentageSelect(percentage) {
    await this.percentageSelect.sendKeys(percentage);
  }

  async getPercentageSelect() {
    return this.percentageSelect.element(by.css('option:checked')).getText();
  }

  async percentageSelectLastOption() {
    await this.percentageSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setLateFeeAssignmentFrequencySelect(lateFeeAssignmentFrequency) {
    await this.lateFeeAssignmentFrequencySelect.sendKeys(lateFeeAssignmentFrequency);
  }

  async getLateFeeAssignmentFrequencySelect() {
    return this.lateFeeAssignmentFrequencySelect.element(by.css('option:checked')).getText();
  }

  async lateFeeAssignmentFrequencySelectLastOption() {
    await this.lateFeeAssignmentFrequencySelect
      .all(by.tagName('option'))
      .last()
      .click();
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
