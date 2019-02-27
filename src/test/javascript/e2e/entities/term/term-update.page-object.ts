import { element, by, ElementFinder } from 'protractor';

export default class TermUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.term.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  termsDescInput: ElementFinder = element(by.css('input#term-termsDesc'));
  startDateInput: ElementFinder = element(by.css('input#term-startDate'));
  endDateInput: ElementFinder = element(by.css('input#term-endDate'));
  termStatusSelect: ElementFinder = element(by.css('select#term-termStatus'));
  academicyearSelect: ElementFinder = element(by.css('select#term-academicyear'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTermsDescInput(termsDesc) {
    await this.termsDescInput.sendKeys(termsDesc);
  }

  async getTermsDescInput() {
    return this.termsDescInput.getAttribute('value');
  }

  async setStartDateInput(startDate) {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput() {
    return this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate) {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput() {
    return this.endDateInput.getAttribute('value');
  }

  async setTermStatusSelect(termStatus) {
    await this.termStatusSelect.sendKeys(termStatus);
  }

  async getTermStatusSelect() {
    return this.termStatusSelect.element(by.css('option:checked')).getText();
  }

  async termStatusSelectLastOption() {
    await this.termStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async academicyearSelectLastOption() {
    await this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicyearSelectOption(option) {
    await this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  async getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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
