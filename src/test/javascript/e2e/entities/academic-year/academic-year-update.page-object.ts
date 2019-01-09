import { element, by, ElementFinder } from 'protractor';

export default class AcademicYearUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicYear.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  yearInput: ElementFinder = element(by.css('input#academic-year-year'));
  startDateInput: ElementFinder = element(by.css('input#academic-year-startDate'));
  endDateInput: ElementFinder = element(by.css('input#academic-year-endDate'));

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
