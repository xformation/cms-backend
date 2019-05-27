import { element, by, ElementFinder } from 'protractor';

export default class StudentExamReportUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentExamReport.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  marksObtainedInput: ElementFinder = element(by.css('input#student-exam-report-marksObtained'));
  commentsInput: ElementFinder = element(by.css('input#student-exam-report-comments'));
  createdOnInput: ElementFinder = element(by.css('input#student-exam-report-createdOn'));
  createdByInput: ElementFinder = element(by.css('input#student-exam-report-createdBy'));
  updatedOnInput: ElementFinder = element(by.css('input#student-exam-report-updatedOn'));
  updatedByInput: ElementFinder = element(by.css('input#student-exam-report-updatedBy'));
  academicExamSettingSelect: ElementFinder = element(by.css('select#student-exam-report-academicExamSetting'));
  studentSelect: ElementFinder = element(by.css('select#student-exam-report-student'));
  typeOfGradingSelect: ElementFinder = element(by.css('select#student-exam-report-typeOfGrading'));
  batchSelect: ElementFinder = element(by.css('select#student-exam-report-batch'));
  academicyearSelect: ElementFinder = element(by.css('select#student-exam-report-academicyear'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setMarksObtainedInput(marksObtained) {
    await this.marksObtainedInput.sendKeys(marksObtained);
  }

  async getMarksObtainedInput() {
    return this.marksObtainedInput.getAttribute('value');
  }

  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  async setCreatedOnInput(createdOn) {
    await this.createdOnInput.sendKeys(createdOn);
  }

  async getCreatedOnInput() {
    return this.createdOnInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy) {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput() {
    return this.createdByInput.getAttribute('value');
  }

  async setUpdatedOnInput(updatedOn) {
    await this.updatedOnInput.sendKeys(updatedOn);
  }

  async getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  async setUpdatedByInput(updatedBy) {
    await this.updatedByInput.sendKeys(updatedBy);
  }

  async getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  async academicExamSettingSelectLastOption() {
    await this.academicExamSettingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicExamSettingSelectOption(option) {
    await this.academicExamSettingSelect.sendKeys(option);
  }

  getAcademicExamSettingSelect() {
    return this.academicExamSettingSelect;
  }

  async getAcademicExamSettingSelectedOption() {
    return this.academicExamSettingSelect.element(by.css('option:checked')).getText();
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

  async typeOfGradingSelectLastOption() {
    await this.typeOfGradingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async typeOfGradingSelectOption(option) {
    await this.typeOfGradingSelect.sendKeys(option);
  }

  getTypeOfGradingSelect() {
    return this.typeOfGradingSelect;
  }

  async getTypeOfGradingSelectedOption() {
    return this.typeOfGradingSelect.element(by.css('option:checked')).getText();
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
