import { element, by, ElementFinder } from 'protractor';

export default class AcademicSubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicSubject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  subjectNameInput: ElementFinder = element(by.css('input#academic-subject-subjectName'));
  electiveSubInput: ElementFinder = element(by.css('input#academic-subject-electiveSub'));
  departmentSelect: ElementFinder = element(by.css('select#academic-subject-department'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSubjectNameInput(subjectName) {
    this.subjectNameInput.sendKeys(subjectName);
  }

  getSubjectNameInput() {
    return this.subjectNameInput.getAttribute('value');
  }

  getElectiveSubInput() {
    return this.electiveSubInput;
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
