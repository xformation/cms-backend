import { element, by, ElementFinder } from 'protractor';

export default class StudentUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.student.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  studentNameInput: ElementFinder = element(by.css('input#student-studentName'));
  attendanceInput: ElementFinder = element(by.css('input#student-attendance'));
  electiveSubSelect: ElementFinder = element(by.css('select#student-electiveSub'));
  teacherSelect: ElementFinder = element(by.css('select#student-teacher'));

  getPageTitle() {
    return this.pageTitle;
  }

  setStudentNameInput(studentName) {
    this.studentNameInput.sendKeys(studentName);
  }

  getStudentNameInput() {
    return this.studentNameInput.getAttribute('value');
  }

  getAttendanceInput() {
    return this.attendanceInput;
  }
  setElectiveSubSelect(electiveSub) {
    this.electiveSubSelect.sendKeys(electiveSub);
  }

  getElectiveSubSelect() {
    return this.electiveSubSelect.element(by.css('option:checked')).getText();
  }

  electiveSubSelectLastOption() {
    this.electiveSubSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  teacherSelectLastOption() {
    this.teacherSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  teacherSelectOption(option) {
    this.teacherSelect.sendKeys(option);
  }

  getTeacherSelect() {
    return this.teacherSelect;
  }

  getTeacherSelectedOption() {
    return this.teacherSelect.element(by.css('option:checked')).getText();
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
