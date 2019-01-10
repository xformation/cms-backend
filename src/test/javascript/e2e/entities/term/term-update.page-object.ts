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

  setTermsDescInput(termsDesc) {
    this.termsDescInput.sendKeys(termsDesc);
  }

  getTermsDescInput() {
    return this.termsDescInput.getAttribute('value');
  }

  setStartDateInput(startDate) {
    this.startDateInput.sendKeys(startDate);
  }

  getStartDateInput() {
    return this.startDateInput.getAttribute('value');
  }

  setEndDateInput(endDate) {
    this.endDateInput.sendKeys(endDate);
  }

  getEndDateInput() {
    return this.endDateInput.getAttribute('value');
  }

  setTermStatusSelect(termStatus) {
    this.termStatusSelect.sendKeys(termStatus);
  }

  getTermStatusSelect() {
    return this.termStatusSelect.element(by.css('option:checked')).getText();
  }

  termStatusSelectLastOption() {
    this.termStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  academicyearSelectLastOption() {
    this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicyearSelectOption(option) {
    this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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
