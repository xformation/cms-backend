import { element, by, ElementFinder } from 'protractor';

export default class AcademicYearUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicYear.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  yearInput: ElementFinder = element(by.css('input#academic-year-year'));
  startDateInput: ElementFinder = element(by.css('input#academic-year-startDate'));
  endDateInput: ElementFinder = element(by.css('input#academic-year-endDate'));
  statusSelect: ElementFinder = element(by.css('select#academic-year-status'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setYearInput(year) {
    await this.yearInput.sendKeys(year);
  }

  async getYearInput() {
    return this.yearInput.getAttribute('value');
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
