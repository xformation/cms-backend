import { element, by, ElementFinder } from 'protractor';

export default class HolidayUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.holiday.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  holidayDescInput: ElementFinder = element(by.css('input#holiday-holidayDesc'));
  holidayDateInput: ElementFinder = element(by.css('input#holiday-holidayDate'));
  statusSelect: ElementFinder = element(by.css('select#holiday-status'));
  academicYearSelect: ElementFinder = element(by.css('select#holiday-academicYear'));

  getPageTitle() {
    return this.pageTitle;
  }

  setHolidayDescInput(holidayDesc) {
    this.holidayDescInput.sendKeys(holidayDesc);
  }

  getHolidayDescInput() {
    return this.holidayDescInput.getAttribute('value');
  }

  setHolidayDateInput(holidayDate) {
    this.holidayDateInput.sendKeys(holidayDate);
  }

  getHolidayDateInput() {
    return this.holidayDateInput.getAttribute('value');
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
