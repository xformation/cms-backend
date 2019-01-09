import { element, by, ElementFinder } from 'protractor';

export default class StudentAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  attendanceDateInput: ElementFinder = element(by.css('input#student-attendance-attendanceDate'));
  statusSelect: ElementFinder = element(by.css('select#student-attendance-status'));
  commentsInput: ElementFinder = element(by.css('input#student-attendance-comments'));
  studentSelect: ElementFinder = element(by.css('select#student-attendance-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  setAttendanceDateInput(attendanceDate) {
    this.attendanceDateInput.sendKeys(attendanceDate);
  }

  getAttendanceDateInput() {
    return this.attendanceDateInput.getAttribute('value');
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
