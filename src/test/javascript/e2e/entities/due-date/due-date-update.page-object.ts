import { element, by, ElementFinder } from 'protractor';

export default class DueDateUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.dueDate.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  paymentMethodInput: ElementFinder = element(by.css('input#due-date-paymentMethod'));
  installmentsInput: ElementFinder = element(by.css('input#due-date-installments'));
  dayDescInput: ElementFinder = element(by.css('input#due-date-dayDesc'));
  frequencySelect: ElementFinder = element(by.css('select#due-date-frequency'));
  collegeSelect: ElementFinder = element(by.css('select#due-date-college'));
  branchSelect: ElementFinder = element(by.css('select#due-date-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  setPaymentMethodInput(paymentMethod) {
    this.paymentMethodInput.sendKeys(paymentMethod);
  }

  getPaymentMethodInput() {
    return this.paymentMethodInput.getAttribute('value');
  }

  setInstallmentsInput(installments) {
    this.installmentsInput.sendKeys(installments);
  }

  getInstallmentsInput() {
    return this.installmentsInput.getAttribute('value');
  }

  setDayDescInput(dayDesc) {
    this.dayDescInput.sendKeys(dayDesc);
  }

  getDayDescInput() {
    return this.dayDescInput.getAttribute('value');
  }

  setFrequencySelect(frequency) {
    this.frequencySelect.sendKeys(frequency);
  }

  getFrequencySelect() {
    return this.frequencySelect.element(by.css('option:checked')).getText();
  }

  frequencySelectLastOption() {
    this.frequencySelect
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
