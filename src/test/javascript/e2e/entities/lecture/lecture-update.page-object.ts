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

  setLecDateInput(lecDate) {
    this.lecDateInput.sendKeys(lecDate);
  }

  getLecDateInput() {
    return this.lecDateInput.getAttribute('value');
  }

  setLastUpdatedOnInput(lastUpdatedOn) {
    this.lastUpdatedOnInput.sendKeys(lastUpdatedOn);
  }

  getLastUpdatedOnInput() {
    return this.lastUpdatedOnInput.getAttribute('value');
  }

  setLastUpdatedByInput(lastUpdatedBy) {
    this.lastUpdatedByInput.sendKeys(lastUpdatedBy);
  }

  getLastUpdatedByInput() {
    return this.lastUpdatedByInput.getAttribute('value');
  }

  setStartTimeInput(startTime) {
    this.startTimeInput.sendKeys(startTime);
  }

  getStartTimeInput() {
    return this.startTimeInput.getAttribute('value');
  }

  setEndTimeInput(endTime) {
    this.endTimeInput.sendKeys(endTime);
  }

  getEndTimeInput() {
    return this.endTimeInput.getAttribute('value');
  }

  attendancemasterSelectLastOption() {
    this.attendancemasterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  attendancemasterSelectOption(option) {
    this.attendancemasterSelect.sendKeys(option);
  }

  getAttendancemasterSelect() {
    return this.attendancemasterSelect;
  }

  getAttendancemasterSelectedOption() {
    return this.attendancemasterSelect.element(by.css('option:checked')).getText();
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
