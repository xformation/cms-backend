import { element, by, ElementFinder } from 'protractor';

export default class StudentAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  attendanceDateInput: ElementFinder = element(by.css('input#student-attendance-attendanceDate'));
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

  async setAttendanceDateInput(attendanceDate) {
    await this.attendanceDateInput.sendKeys(attendanceDate);
  }

  async getAttendanceDateInput() {
    return this.attendanceDateInput.getAttribute('value');
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
  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  async studentYearSelectLastOption() {
    await this.studentYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async studentYearSelectOption(option) {
    await this.studentYearSelect.sendKeys(option);
  }

  getStudentYearSelect() {
    return this.studentYearSelect;
  }

  async getStudentYearSelectedOption() {
    return this.studentYearSelect.element(by.css('option:checked')).getText();
  }

  async departmentsSelectLastOption() {
    await this.departmentsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentsSelectOption(option) {
    await this.departmentsSelect.sendKeys(option);
  }

  getDepartmentsSelect() {
    return this.departmentsSelect;
  }

  async getDepartmentsSelectedOption() {
    return this.departmentsSelect.element(by.css('option:checked')).getText();
  }

  async subjectSelectLastOption() {
    await this.subjectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async subjectSelectOption(option) {
    await this.subjectSelect.sendKeys(option);
  }

  getSubjectSelect() {
    return this.subjectSelect;
  }

  async getSubjectSelectedOption() {
    return this.subjectSelect.element(by.css('option:checked')).getText();
  }

  async semesterSelectLastOption() {
    await this.semesterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async semesterSelectOption(option) {
    await this.semesterSelect.sendKeys(option);
  }

  getSemesterSelect() {
    return this.semesterSelect;
  }

  async getSemesterSelectedOption() {
    return this.semesterSelect.element(by.css('option:checked')).getText();
  }

  async sectionSelectLastOption() {
    await this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sectionSelectOption(option) {
    await this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  async getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
  }

  async periodsSelectLastOption() {
    await this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async periodsSelectOption(option) {
    await this.periodsSelect.sendKeys(option);
  }

  getPeriodsSelect() {
    return this.periodsSelect;
  }

  async getPeriodsSelectedOption() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
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
