import { element, by, ElementFinder } from 'protractor';

export default class AdmissionApplicationUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.admissionApplication.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  admissionStatusSelect: ElementFinder = element(by.css('select#admission-application-admissionStatus'));
  courseSelect: ElementFinder = element(by.css('select#admission-application-course'));
  dateInput: ElementFinder = element(by.css('input#admission-application-date'));
  commentsInput: ElementFinder = element(by.css('input#admission-application-comments'));
  studentSelect: ElementFinder = element(by.css('select#admission-application-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  setAdmissionStatusSelect(admissionStatus) {
    this.admissionStatusSelect.sendKeys(admissionStatus);
  }

  getAdmissionStatusSelect() {
    return this.admissionStatusSelect.element(by.css('option:checked')).getText();
  }

  admissionStatusSelectLastOption() {
    this.admissionStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCourseSelect(course) {
    this.courseSelect.sendKeys(course);
  }

  getCourseSelect() {
    return this.courseSelect.element(by.css('option:checked')).getText();
  }

  courseSelectLastOption() {
    this.courseSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setDateInput(date) {
    this.dateInput.sendKeys(date);
  }

  getDateInput() {
    return this.dateInput.getAttribute('value');
  }

  setCommentsInput(comments) {
    this.commentsInput.sendKeys(comments);
  }

  getCommentsInput() {
    return this.commentsInput.getAttribute('value');
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
