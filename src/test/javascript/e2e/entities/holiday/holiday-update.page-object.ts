import { element, by, ElementFinder } from 'protractor';

export default class HolidayUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.holiday.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  srNoInput: ElementFinder = element(by.css('input#holiday-srNo'));
  sHolidayInput: ElementFinder = element(by.css('input#holiday-sHoliday'));
  aDateInput: ElementFinder = element(by.css('input#holiday-aDate'));
  statusSelect: ElementFinder = element(by.css('select#holiday-status'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSrNoInput(srNo) {
    await this.srNoInput.sendKeys(srNo);
  }

  async getSrNoInput() {
    return this.srNoInput.getAttribute('value');
  }

  async setSHolidayInput(sHoliday) {
    await this.sHolidayInput.sendKeys(sHoliday);
  }

  async getSHolidayInput() {
    return this.sHolidayInput.getAttribute('value');
  }

  async setADateInput(aDate) {
    await this.aDateInput.sendKeys(aDate);
  }

  async getADateInput() {
    return this.aDateInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
