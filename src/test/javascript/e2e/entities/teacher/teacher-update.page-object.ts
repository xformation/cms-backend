import { element, by, ElementFinder } from 'protractor';

export default class TeacherUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.teacher.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  teacherNameInput: ElementFinder = element(by.css('input#teacher-teacherName'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTeacherNameInput(teacherName) {
    this.teacherNameInput.sendKeys(teacherName);
  }

  getTeacherNameInput() {
    return this.teacherNameInput.getAttribute('value');
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
