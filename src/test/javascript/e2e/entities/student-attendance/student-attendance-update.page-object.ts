import { element, by, ElementFinder } from 'protractor';

export default class StudentAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  attendanceDateInput: ElementFinder = element(by.css('input#student-attendance-attendanceDate'));
  sNameInput: ElementFinder = element(by.css('input#student-attendance-sName'));
  statusSelect: ElementFinder = element(by.css('select#student-attendance-status'));
  commentsInput: ElementFinder = element(by.css('input#student-attendance-comments'));
  studentYearSelect: ElementFinder = element(by.css('select#student-attendance-studentYear'));
  departmentsSelect: ElementFinder = element(by.css('select#student-attendance-departments'));
  subjectSelect: ElementFinder = element(by.css('select#student-attendance-subject'));
  semesterSelect: ElementFinder = element(by.css('select#student-attendance-semester'));
  sectionSelect: ElementFinder = element(by.css('select#student-attendance-section'));
  periodsSelect: ElementFinder = element(by.css('select#student-attendance-periods'));
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

  setSNameInput(sName) {
    this.sNameInput.sendKeys(sName);
  }

  getSNameInput() {
    return this.sNameInput.getAttribute('value');
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

  studentYearSelectLastOption() {
    this.studentYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  studentYearSelectOption(option) {
    this.studentYearSelect.sendKeys(option);
  }

  getStudentYearSelect() {
    return this.studentYearSelect;
  }

  getStudentYearSelectedOption() {
    return this.studentYearSelect.element(by.css('option:checked')).getText();
  }

  departmentsSelectLastOption() {
    this.departmentsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentsSelectOption(option) {
    this.departmentsSelect.sendKeys(option);
  }

  getDepartmentsSelect() {
    return this.departmentsSelect;
  }

  getDepartmentsSelectedOption() {
    return this.departmentsSelect.element(by.css('option:checked')).getText();
  }

  subjectSelectLastOption() {
    this.subjectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  subjectSelectOption(option) {
    this.subjectSelect.sendKeys(option);
  }

  getSubjectSelect() {
    return this.subjectSelect;
  }

  getSubjectSelectedOption() {
    return this.subjectSelect.element(by.css('option:checked')).getText();
  }

  semesterSelectLastOption() {
    this.semesterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  semesterSelectOption(option) {
    this.semesterSelect.sendKeys(option);
  }

  getSemesterSelect() {
    return this.semesterSelect;
  }

  getSemesterSelectedOption() {
    return this.semesterSelect.element(by.css('option:checked')).getText();
  }

  sectionSelectLastOption() {
    this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  sectionSelectOption(option) {
    this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
  }

  periodsSelectLastOption() {
    this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  periodsSelectOption(option) {
    this.periodsSelect.sendKeys(option);
  }

  getPeriodsSelect() {
    return this.periodsSelect;
  }

  getPeriodsSelectedOption() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
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
