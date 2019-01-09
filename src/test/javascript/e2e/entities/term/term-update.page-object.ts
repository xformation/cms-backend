import { element, by, ElementFinder } from 'protractor';

export default class TermUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.term.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  termsDescInput: ElementFinder = element(by.css('input#term-termsDesc'));
  startDateInput: ElementFinder = element(by.css('input#term-startDate'));
  endDateInput: ElementFinder = element(by.css('input#term-endDate'));
  statusSelect: ElementFinder = element(by.css('select#term-status'));
  academicYearSelect: ElementFinder = element(by.css('select#term-academicYear'));

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

  setStatusSelect(status) {
    this.statusSelect.sendKeys(status);
  }

  getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  statusSelectLastOption() {
    this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
