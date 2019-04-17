import { element, by, ElementFinder } from 'protractor';

export default class CompetitiveExamUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.competitiveExam.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  testNameInput: ElementFinder = element(by.css('input#competitive-exam-testName'));
  testScoreInput: ElementFinder = element(by.css('input#competitive-exam-testScore'));
  enrollmentNoInput: ElementFinder = element(by.css('input#competitive-exam-enrollmentNo'));
  rankInput: ElementFinder = element(by.css('input#competitive-exam-rank'));
  studentSelect: ElementFinder = element(by.css('select#competitive-exam-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTestNameInput(testName) {
    await this.testNameInput.sendKeys(testName);
  }

  async getTestNameInput() {
    return this.testNameInput.getAttribute('value');
  }

  async setTestScoreInput(testScore) {
    await this.testScoreInput.sendKeys(testScore);
  }

  async getTestScoreInput() {
    return this.testScoreInput.getAttribute('value');
  }

  async setEnrollmentNoInput(enrollmentNo) {
    await this.enrollmentNoInput.sendKeys(enrollmentNo);
  }

  async getEnrollmentNoInput() {
    return this.enrollmentNoInput.getAttribute('value');
  }

  async setRankInput(rank) {
    await this.rankInput.sendKeys(rank);
  }

  async getRankInput() {
    return this.rankInput.getAttribute('value');
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
