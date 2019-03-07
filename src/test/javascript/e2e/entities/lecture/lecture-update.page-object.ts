import { element, by, ElementFinder } from 'protractor';

export default class LectureUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.lecture.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  lecDateInput: ElementFinder = element(by.css('input#lecture-lecDate'));
  lastUpdatedOnInput: ElementFinder = element(by.css('input#lecture-lastUpdatedOn'));
  lastUpdatedByInput: ElementFinder = element(by.css('input#lecture-lastUpdatedBy'));
  startTimeInput: ElementFinder = element(by.css('input#lecture-startTime'));
  endTimeInput: ElementFinder = element(by.css('input#lecture-endTime'));
  attendancemasterSelect: ElementFinder = element(by.css('select#lecture-attendancemaster'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setLecDateInput(lecDate) {
    await this.lecDateInput.sendKeys(lecDate);
  }

  async getLecDateInput() {
    return this.lecDateInput.getAttribute('value');
  }

  async setLastUpdatedOnInput(lastUpdatedOn) {
    await this.lastUpdatedOnInput.sendKeys(lastUpdatedOn);
  }

  async getLastUpdatedOnInput() {
    return this.lastUpdatedOnInput.getAttribute('value');
  }

  async setLastUpdatedByInput(lastUpdatedBy) {
    await this.lastUpdatedByInput.sendKeys(lastUpdatedBy);
  }

  async getLastUpdatedByInput() {
    return this.lastUpdatedByInput.getAttribute('value');
  }

  async setStartTimeInput(startTime) {
    await this.startTimeInput.sendKeys(startTime);
  }

  async getStartTimeInput() {
    return this.startTimeInput.getAttribute('value');
  }

  async setEndTimeInput(endTime) {
    await this.endTimeInput.sendKeys(endTime);
  }

  async getEndTimeInput() {
    return this.endTimeInput.getAttribute('value');
  }

  async attendancemasterSelectLastOption() {
    await this.attendancemasterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async attendancemasterSelectOption(option) {
    await this.attendancemasterSelect.sendKeys(option);
  }

  getAttendancemasterSelect() {
    return this.attendancemasterSelect;
  }

  async getAttendancemasterSelectedOption() {
    return this.attendancemasterSelect.element(by.css('option:checked')).getText();
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
