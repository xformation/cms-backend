import { element, by, ElementFinder } from 'protractor';

export default class AdmissionApplicationUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.admissionApplication.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  admissionStatusSelect: ElementFinder = element(by.css('select#admission-application-admissionStatus'));
  courseSelect: ElementFinder = element(by.css('select#admission-application-course'));
  admissionDateInput: ElementFinder = element(by.css('input#admission-application-admissionDate'));
  commentsInput: ElementFinder = element(by.css('input#admission-application-comments'));
  studentSelect: ElementFinder = element(by.css('select#admission-application-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAdmissionStatusSelect(admissionStatus) {
    await this.admissionStatusSelect.sendKeys(admissionStatus);
  }

  async getAdmissionStatusSelect() {
    return this.admissionStatusSelect.element(by.css('option:checked')).getText();
  }

  async admissionStatusSelectLastOption() {
    await this.admissionStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCourseSelect(course) {
    await this.courseSelect.sendKeys(course);
  }

  async getCourseSelect() {
    return this.courseSelect.element(by.css('option:checked')).getText();
  }

  async courseSelectLastOption() {
    await this.courseSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setAdmissionDateInput(admissionDate) {
    await this.admissionDateInput.sendKeys(admissionDate);
  }

  async getAdmissionDateInput() {
    return this.admissionDateInput.getAttribute('value');
  }

  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
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
