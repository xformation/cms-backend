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

  setTestNameInput(testName) {
    this.testNameInput.sendKeys(testName);
  }

  getTestNameInput() {
    return this.testNameInput.getAttribute('value');
  }

  setTestScoreInput(testScore) {
    this.testScoreInput.sendKeys(testScore);
  }

  getTestScoreInput() {
    return this.testScoreInput.getAttribute('value');
  }

  setEnrollmentNoInput(enrollmentNo) {
    this.enrollmentNoInput.sendKeys(enrollmentNo);
  }

  getEnrollmentNoInput() {
    return this.enrollmentNoInput.getAttribute('value');
  }

  setRankInput(rank) {
    this.rankInput.sendKeys(rank);
  }

  getRankInput() {
    return this.rankInput.getAttribute('value');
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
