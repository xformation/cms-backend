import { element, by, ElementFinder } from 'protractor';

export default class AcademicYearUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicYear.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  yearInput: ElementFinder = element(by.css('input#academic-year-year'));
  startDateInput: ElementFinder = element(by.css('input#academic-year-startDate'));
  endDateInput: ElementFinder = element(by.css('input#academic-year-endDate'));
  holidaySelect: ElementFinder = element(by.css('select#academic-year-holiday'));
  termSelect: ElementFinder = element(by.css('select#academic-year-term'));

  getPageTitle() {
    return this.pageTitle;
  }

  setYearInput(year) {
    this.yearInput.sendKeys(year);
  }

  getYearInput() {
    return this.yearInput.getAttribute('value');
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

  holidaySelectLastOption() {
    this.holidaySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  holidaySelectOption(option) {
    this.holidaySelect.sendKeys(option);
  }

  getHolidaySelect() {
    return this.holidaySelect;
  }

  getHolidaySelectedOption() {
    return this.holidaySelect.element(by.css('option:checked')).getText();
  }

  termSelectLastOption() {
    this.termSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  termSelectOption(option) {
    this.termSelect.sendKeys(option);
  }

  getTermSelect() {
    return this.termSelect;
  }

  getTermSelectedOption() {
    return this.termSelect.element(by.css('option:checked')).getText();
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
