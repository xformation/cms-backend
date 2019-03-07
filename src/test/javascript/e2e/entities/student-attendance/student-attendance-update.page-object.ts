import { element, by, ElementFinder } from 'protractor';

export default class StudentAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  attendanceStatusSelect: ElementFinder = element(by.css('select#student-attendance-attendanceStatus'));
  commentsInput: ElementFinder = element(by.css('input#student-attendance-comments'));
  studentSelect: ElementFinder = element(by.css('select#student-attendance-student'));
  lectureSelect: ElementFinder = element(by.css('select#student-attendance-lecture'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAttendanceStatusSelect(attendanceStatus) {
    await this.attendanceStatusSelect.sendKeys(attendanceStatus);
  }

  async getAttendanceStatusSelect() {
    return this.attendanceStatusSelect.element(by.css('option:checked')).getText();
  }

  async attendanceStatusSelectLastOption() {
    await this.attendanceStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  async studentSelectLastOption() {
    await this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async studentSelectOption(option) {
    await this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  async getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
  }

  async lectureSelectLastOption() {
    await this.lectureSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async lectureSelectOption(option) {
    await this.lectureSelect.sendKeys(option);
  }

  getLectureSelect() {
    return this.lectureSelect;
  }

  async getLectureSelectedOption() {
    return this.lectureSelect.element(by.css('option:checked')).getText();
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
