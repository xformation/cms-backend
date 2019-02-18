import { element, by, ElementFinder } from 'protractor';

export default class AdminAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.adminAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  attendanceStatusSelect: ElementFinder = element(by.css('select#admin-attendance-attendanceStatus'));
  commentsInput: ElementFinder = element(by.css('input#admin-attendance-comments'));
  studentSelect: ElementFinder = element(by.css('select#admin-attendance-student'));
  lectureSelect: ElementFinder = element(by.css('select#admin-attendance-lecture'));

  getPageTitle() {
    return this.pageTitle;
  }

  setAttendanceStatusSelect(attendanceStatus) {
    this.attendanceStatusSelect.sendKeys(attendanceStatus);
  }

  getAttendanceStatusSelect() {
    return this.attendanceStatusSelect.element(by.css('option:checked')).getText();
  }

  attendanceStatusSelectLastOption() {
    this.attendanceStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCommentsInput(comments) {
    this.commentsInput.sendKeys(comments);
  }

  getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  studentSelectLastOption() {
    this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  studentSelectOption(option) {
    this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
  }

  lectureSelectLastOption() {
    this.lectureSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  lectureSelectOption(option) {
    this.lectureSelect.sendKeys(option);
  }

  getLectureSelect() {
    return this.lectureSelect;
  }

  getLectureSelectedOption() {
    return this.lectureSelect.element(by.css('option:checked')).getText();
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
