import { element, by, ElementFinder } from 'protractor';

export default class DueDateUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.dueDate.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  paymentMethodInput: ElementFinder = element(by.css('input#due-date-paymentMethod'));
  installmentsInput: ElementFinder = element(by.css('input#due-date-installments'));
  dayDescInput: ElementFinder = element(by.css('input#due-date-dayDesc'));
  paymentDateInput: ElementFinder = element(by.css('input#due-date-paymentDate'));
  frequencySelect: ElementFinder = element(by.css('select#due-date-frequency'));
  collegeSelect: ElementFinder = element(by.css('select#due-date-college'));
  branchSelect: ElementFinder = element(by.css('select#due-date-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setPaymentMethodInput(paymentMethod) {
    await this.paymentMethodInput.sendKeys(paymentMethod);
  }

  async getPaymentMethodInput() {
    return this.paymentMethodInput.getAttribute('value');
  }

  async setInstallmentsInput(installments) {
    await this.installmentsInput.sendKeys(installments);
  }

  async getInstallmentsInput() {
    return this.installmentsInput.getAttribute('value');
  }

  async setDayDescInput(dayDesc) {
    await this.dayDescInput.sendKeys(dayDesc);
  }

  async getDayDescInput() {
    return this.dayDescInput.getAttribute('value');
  }

  async setPaymentDateInput(paymentDate) {
    await this.paymentDateInput.sendKeys(paymentDate);
  }

  async getPaymentDateInput() {
    return this.paymentDateInput.getAttribute('value');
  }

  async setFrequencySelect(frequency) {
    await this.frequencySelect.sendKeys(frequency);
  }

  async getFrequencySelect() {
    return this.frequencySelect.element(by.css('option:checked')).getText();
  }

  async frequencySelectLastOption() {
    await this.frequencySelect
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
