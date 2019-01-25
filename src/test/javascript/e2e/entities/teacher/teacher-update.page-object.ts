import { element, by, ElementFinder } from 'protractor';

export default class TeacherUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.teacher.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  teacherNameInput: ElementFinder = element(by.css('input#teacher-teacherName'));
  branchSelect: ElementFinder = element(by.css('select#teacher-branch'));
  departmentSelect: ElementFinder = element(by.css('select#teacher-department'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTeacherNameInput(teacherName) {
    this.teacherNameInput.sendKeys(teacherName);
  }

  getTeacherNameInput() {
    return this.teacherNameInput.getAttribute('value');
  }

  branchSelectLastOption() {
    this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  branchSelectOption(option) {
    this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
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
