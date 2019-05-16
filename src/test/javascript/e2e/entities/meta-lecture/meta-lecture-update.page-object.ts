import { element, by, ElementFinder } from 'protractor';

export default class MetaLectureUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.metaLecture.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  weekDayInput: ElementFinder = element(by.css('input#meta-lecture-weekDay'));
  startTimeInput: ElementFinder = element(by.css('input#meta-lecture-startTime'));
  endTimeInput: ElementFinder = element(by.css('input#meta-lecture-endTime'));
  branchSelect: ElementFinder = element(by.css('select#meta-lecture-branch'));
  departmentSelect: ElementFinder = element(by.css('select#meta-lecture-department'));
  subjectSelect: ElementFinder = element(by.css('select#meta-lecture-subject'));
  teacherSelect: ElementFinder = element(by.css('select#meta-lecture-teacher'));
  termSelect: ElementFinder = element(by.css('select#meta-lecture-term'));
  academicyearSelect: ElementFinder = element(by.css('select#meta-lecture-academicyear'));
  sectionSelect: ElementFinder = element(by.css('select#meta-lecture-section'));
  batchSelect: ElementFinder = element(by.css('select#meta-lecture-batch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setWeekDayInput(weekDay) {
    await this.weekDayInput.sendKeys(weekDay);
  }

  async getWeekDayInput() {
    return this.weekDayInput.getAttribute('value');
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

  async branchSelectLastOption() {
    await this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async branchSelectOption(option) {
    await this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  async getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  async departmentSelectLastOption() {
    await this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentSelectOption(option) {
    await this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  async getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
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

  async teacherSelectLastOption() {
    await this.teacherSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async teacherSelectOption(option) {
    await this.teacherSelect.sendKeys(option);
  }

  getTeacherSelect() {
    return this.teacherSelect;
  }

  async getTeacherSelectedOption() {
    return this.teacherSelect.element(by.css('option:checked')).getText();
  }

  async termSelectLastOption() {
    await this.termSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async termSelectOption(option) {
    await this.termSelect.sendKeys(option);
  }

  getTermSelect() {
    return this.termSelect;
  }

  async getTermSelectedOption() {
    return this.termSelect.element(by.css('option:checked')).getText();
  }

  async academicyearSelectLastOption() {
    await this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicyearSelectOption(option) {
    await this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  async getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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

  async batchSelectLastOption() {
    await this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async batchSelectOption(option) {
    await this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  async getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
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
