import { element, by, ElementFinder } from 'protractor';

export default class AcademicExamSettingUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicExamSetting.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  examTypeInput: ElementFinder = element(by.css('input#academic-exam-setting-examType'));
  semesterSelect: ElementFinder = element(by.css('select#academic-exam-setting-semester'));
  subjectInput: ElementFinder = element(by.css('input#academic-exam-setting-subject'));
  examDateInput: ElementFinder = element(by.css('input#academic-exam-setting-examDate'));
  dayInput: ElementFinder = element(by.css('input#academic-exam-setting-day'));
  durationInput: ElementFinder = element(by.css('input#academic-exam-setting-duration'));
  startTimeInput: ElementFinder = element(by.css('input#academic-exam-setting-startTime'));
  endTimeInput: ElementFinder = element(by.css('input#academic-exam-setting-endTime'));
  gradeTypeSelect: ElementFinder = element(by.css('select#academic-exam-setting-gradeType'));
  totalInput: ElementFinder = element(by.css('input#academic-exam-setting-total'));
  passingInput: ElementFinder = element(by.css('input#academic-exam-setting-passing'));
  actionsInput: ElementFinder = element(by.css('input#academic-exam-setting-actions'));
  startDateInput: ElementFinder = element(by.css('input#academic-exam-setting-startDate'));
  endDateInput: ElementFinder = element(by.css('input#academic-exam-setting-endDate'));
  departmentSelect: ElementFinder = element(by.css('select#academic-exam-setting-department'));
  academicyearSelect: ElementFinder = element(by.css('select#academic-exam-setting-academicyear'));
  sectionSelect: ElementFinder = element(by.css('select#academic-exam-setting-section'));
  batchSelect: ElementFinder = element(by.css('select#academic-exam-setting-batch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setExamTypeInput(examType) {
    await this.examTypeInput.sendKeys(examType);
  }

  async getExamTypeInput() {
    return this.examTypeInput.getAttribute('value');
  }

  async setSemesterSelect(semester) {
    await this.semesterSelect.sendKeys(semester);
  }

  async getSemesterSelect() {
    return this.semesterSelect.element(by.css('option:checked')).getText();
  }

  async semesterSelectLastOption() {
    await this.semesterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setSubjectInput(subject) {
    await this.subjectInput.sendKeys(subject);
  }

  async getSubjectInput() {
    return this.subjectInput.getAttribute('value');
  }

  async setExamDateInput(examDate) {
    await this.examDateInput.sendKeys(examDate);
  }

  async getExamDateInput() {
    return this.examDateInput.getAttribute('value');
  }

  async setDayInput(day) {
    await this.dayInput.sendKeys(day);
  }

  async getDayInput() {
    return this.dayInput.getAttribute('value');
  }

  async setDurationInput(duration) {
    await this.durationInput.sendKeys(duration);
  }

  async getDurationInput() {
    return this.durationInput.getAttribute('value');
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

  async setGradeTypeSelect(gradeType) {
    await this.gradeTypeSelect.sendKeys(gradeType);
  }

  async getGradeTypeSelect() {
    return this.gradeTypeSelect.element(by.css('option:checked')).getText();
  }

  async gradeTypeSelectLastOption() {
    await this.gradeTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setTotalInput(total) {
    await this.totalInput.sendKeys(total);
  }

  async getTotalInput() {
    return this.totalInput.getAttribute('value');
  }

  async setPassingInput(passing) {
    await this.passingInput.sendKeys(passing);
  }

  async getPassingInput() {
    return this.passingInput.getAttribute('value');
  }

  async setActionsInput(actions) {
    await this.actionsInput.sendKeys(actions);
  }

  async getActionsInput() {
    return this.actionsInput.getAttribute('value');
  }

  async setStartDateInput(startDate) {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput() {
    return this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate) {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput() {
    return this.endDateInput.getAttribute('value');
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
