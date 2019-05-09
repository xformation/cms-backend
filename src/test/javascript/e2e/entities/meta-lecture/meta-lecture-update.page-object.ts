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

  setWeekDayInput(weekDay) {
    this.weekDayInput.sendKeys(weekDay);
  }

  getWeekDayInput() {
    return this.weekDayInput.getAttribute('value');
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

  branchSelectLastOption() {
    this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  branchSelectOption(option) {
    this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
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

  termSelectLastOption() {
    this.termSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  termSelectOption(option) {
    this.termSelect.sendKeys(option);
  }

  getTermSelect() {
    return this.termSelect;
  }

  getTermSelectedOption() {
    return this.termSelect.element(by.css('option:checked')).getText();
  }

  academicyearSelectLastOption() {
    this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicyearSelectOption(option) {
    this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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

  batchSelectLastOption() {
    this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  batchSelectOption(option) {
    this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
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
