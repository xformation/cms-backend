import { element, by, ElementFinder } from 'protractor';

export default class AcademicHistoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicHistory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  qualificationInput: ElementFinder = element(by.css('input#academic-history-qualification'));
  yearOfPassingInput: ElementFinder = element(by.css('input#academic-history-yearOfPassing'));
  institutionInput: ElementFinder = element(by.css('input#academic-history-institution'));
  universityInput: ElementFinder = element(by.css('input#academic-history-university'));
  enrollmentNoInput: ElementFinder = element(by.css('input#academic-history-enrollmentNo'));
  scoreInput: ElementFinder = element(by.css('input#academic-history-score'));
  percentageInput: ElementFinder = element(by.css('input#academic-history-percentage'));
  studentSelect: ElementFinder = element(by.css('select#academic-history-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setQualificationInput(qualification) {
    await this.qualificationInput.sendKeys(qualification);
  }

  async getQualificationInput() {
    return this.qualificationInput.getAttribute('value');
  }

  async setYearOfPassingInput(yearOfPassing) {
    await this.yearOfPassingInput.sendKeys(yearOfPassing);
  }

  async getYearOfPassingInput() {
    return this.yearOfPassingInput.getAttribute('value');
  }

  async setInstitutionInput(institution) {
    await this.institutionInput.sendKeys(institution);
  }

  async getInstitutionInput() {
    return this.institutionInput.getAttribute('value');
  }

  async setUniversityInput(university) {
    await this.universityInput.sendKeys(university);
  }

  async getUniversityInput() {
    return this.universityInput.getAttribute('value');
  }

  async setEnrollmentNoInput(enrollmentNo) {
    await this.enrollmentNoInput.sendKeys(enrollmentNo);
  }

  async getEnrollmentNoInput() {
    return this.enrollmentNoInput.getAttribute('value');
  }

  async setScoreInput(score) {
    await this.scoreInput.sendKeys(score);
  }

  async getScoreInput() {
    return this.scoreInput.getAttribute('value');
  }

  async setPercentageInput(percentage) {
    await this.percentageInput.sendKeys(percentage);
  }

  async getPercentageInput() {
    return this.percentageInput.getAttribute('value');
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
