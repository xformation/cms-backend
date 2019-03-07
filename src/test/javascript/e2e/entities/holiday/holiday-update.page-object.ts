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

  async setHolidayDescInput(holidayDesc) {
    await this.holidayDescInput.sendKeys(holidayDesc);
  }

  async getHolidayDescInput() {
    return this.holidayDescInput.getAttribute('value');
  }

  async setHolidayDateInput(holidayDate) {
    await this.holidayDateInput.sendKeys(holidayDate);
  }

  async getHolidayDateInput() {
    return this.holidayDateInput.getAttribute('value');
  }

  async setHolidayStatusSelect(holidayStatus) {
    await this.holidayStatusSelect.sendKeys(holidayStatus);
  }

  async getHolidayStatusSelect() {
    return this.holidayStatusSelect.element(by.css('option:checked')).getText();
  }

  async holidayStatusSelectLastOption() {
    await this.holidayStatusSelect
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
