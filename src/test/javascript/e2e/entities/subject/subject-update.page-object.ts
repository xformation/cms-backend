import { element, by, ElementFinder } from 'protractor';

export default class SubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.subject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  subjectCodeInput: ElementFinder = element(by.css('input#subject-subjectCode'));
  subjectTypeSelect: ElementFinder = element(by.css('select#subject-subjectType'));
  subjectDescInput: ElementFinder = element(by.css('input#subject-subjectDesc'));
  departmentSelect: ElementFinder = element(by.css('select#subject-department'));
  teacherSelect: ElementFinder = element(by.css('select#subject-teacher'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSubjectCodeInput(subjectCode) {
    this.subjectCodeInput.sendKeys(subjectCode);
  }

  getSubjectCodeInput() {
    return this.subjectCodeInput.getAttribute('value');
  }

  setSubjectTypeSelect(subjectType) {
    this.subjectTypeSelect.sendKeys(subjectType);
  }

  getSubjectTypeSelect() {
    return this.subjectTypeSelect.element(by.css('option:checked')).getText();
  }

  subjectTypeSelectLastOption() {
    this.subjectTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSubjectDescInput(subjectDesc) {
    this.subjectDescInput.sendKeys(subjectDesc);
  }

  getSubjectDescInput() {
    return this.subjectDescInput.getAttribute('value');
  }

  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  teacherSelectLastOption() {
    this.teacherSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  teacherSelectOption(option) {
    this.teacherSelect.sendKeys(option);
  }

  getTeacherSelect() {
    return this.teacherSelect;
  }

  getTeacherSelectedOption() {
    return this.teacherSelect.element(by.css('option:checked')).getText();
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
