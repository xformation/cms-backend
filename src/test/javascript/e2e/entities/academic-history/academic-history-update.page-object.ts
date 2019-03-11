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

  setQualificationInput(qualification) {
    this.qualificationInput.sendKeys(qualification);
  }

  getQualificationInput() {
    return this.qualificationInput.getAttribute('value');
  }

  setYearOfPassingInput(yearOfPassing) {
    this.yearOfPassingInput.sendKeys(yearOfPassing);
  }

  getYearOfPassingInput() {
    return this.yearOfPassingInput.getAttribute('value');
  }

  setInstitutionInput(institution) {
    this.institutionInput.sendKeys(institution);
  }

  getInstitutionInput() {
    return this.institutionInput.getAttribute('value');
  }

  setUniversityInput(university) {
    this.universityInput.sendKeys(university);
  }

  getUniversityInput() {
    return this.universityInput.getAttribute('value');
  }

  setEnrollmentNoInput(enrollmentNo) {
    this.enrollmentNoInput.sendKeys(enrollmentNo);
  }

  getEnrollmentNoInput() {
    return this.enrollmentNoInput.getAttribute('value');
  }

  setScoreInput(score) {
    this.scoreInput.sendKeys(score);
  }

  getScoreInput() {
    return this.scoreInput.getAttribute('value');
  }

  setPercentageInput(percentage) {
    this.percentageInput.sendKeys(percentage);
  }

  getPercentageInput() {
    return this.percentageInput.getAttribute('value');
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
