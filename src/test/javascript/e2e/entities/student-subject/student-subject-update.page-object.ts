import { element, by, ElementFinder } from 'protractor';

export default class StudentSubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentSubject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  commentsInput: ElementFinder = element(by.css('input#student-subject-comments'));
  lastupdatedDateInput: ElementFinder = element(by.css('input#student-subject-lastupdatedDate'));
  studentSelect: ElementFinder = element(by.css('select#student-subject-student'));
  subjectSelect: ElementFinder = element(by.css('select#student-subject-subject'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCommentsInput(comments) {
    this.commentsInput.sendKeys(comments);
  }

  getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  setLastupdatedDateInput(lastupdatedDate) {
    this.lastupdatedDateInput.sendKeys(lastupdatedDate);
  }

  getLastupdatedDateInput() {
    return this.lastupdatedDateInput.getAttribute('value');
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
