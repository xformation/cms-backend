import { element, by, ElementFinder } from 'protractor';

export default class HolidayUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.holiday.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  holidayDescInput: ElementFinder = element(by.css('input#holiday-holidayDesc'));
  holidayDateInput: ElementFinder = element(by.css('input#holiday-holidayDate'));
  holidayStatusSelect: ElementFinder = element(by.css('select#holiday-holidayStatus'));
  academicyearSelect: ElementFinder = element(by.css('select#holiday-academicyear'));

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

  setHolidayStatusSelect(holidayStatus) {
    this.holidayStatusSelect.sendKeys(holidayStatus);
  }

  getHolidayStatusSelect() {
    return this.holidayStatusSelect.element(by.css('option:checked')).getText();
  }

  holidayStatusSelectLastOption() {
    this.holidayStatusSelect
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
