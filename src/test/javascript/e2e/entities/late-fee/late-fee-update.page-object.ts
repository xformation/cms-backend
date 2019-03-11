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

  setAssignLateFeeSelect(assignLateFee) {
    this.assignLateFeeSelect.sendKeys(assignLateFee);
  }

  getAssignLateFeeSelect() {
    return this.assignLateFeeSelect.element(by.css('option:checked')).getText();
  }

  assignLateFeeSelectLastOption() {
    this.assignLateFeeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLateFeeDaysInput(lateFeeDays) {
    this.lateFeeDaysInput.sendKeys(lateFeeDays);
  }

  getLateFeeDaysInput() {
    return this.lateFeeDaysInput.getAttribute('value');
  }

  setFixedSelect(fixed) {
    this.fixedSelect.sendKeys(fixed);
  }

  getFixedSelect() {
    return this.fixedSelect.element(by.css('option:checked')).getText();
  }

  fixedSelectLastOption() {
    this.fixedSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setPercentageSelect(percentage) {
    this.percentageSelect.sendKeys(percentage);
  }

  getPercentageSelect() {
    return this.percentageSelect.element(by.css('option:checked')).getText();
  }

  percentageSelectLastOption() {
    this.percentageSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setFixedChargesInput(fixedCharges) {
    this.fixedChargesInput.sendKeys(fixedCharges);
  }

  getFixedChargesInput() {
    return this.fixedChargesInput.getAttribute('value');
  }

  setPercentChargesInput(percentCharges) {
    this.percentChargesInput.sendKeys(percentCharges);
  }

  getPercentChargesInput() {
    return this.percentChargesInput.getAttribute('value');
  }

  setLateFeeAssignmentFrequencySelect(lateFeeAssignmentFrequency) {
    this.lateFeeAssignmentFrequencySelect.sendKeys(lateFeeAssignmentFrequency);
  }

  getLateFeeAssignmentFrequencySelect() {
    return this.lateFeeAssignmentFrequencySelect.element(by.css('option:checked')).getText();
  }

  lateFeeAssignmentFrequencySelectLastOption() {
    this.lateFeeAssignmentFrequencySelect
      .all(by.tagName('option'))
      .last()
      .click();
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
