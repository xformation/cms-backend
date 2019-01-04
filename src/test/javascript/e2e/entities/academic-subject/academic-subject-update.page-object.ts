import { element, by, ElementFinder } from 'protractor';

export default class AcademicSubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.academicSubject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  subjectNameInput: ElementFinder = element(by.css('input#academic-subject-subjectName'));
  electiveSubInput: ElementFinder = element(by.css('input#academic-subject-electiveSub'));
  academicDepartmentSelect: ElementFinder = element(by.css('select#academic-subject-academicDepartment'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSubjectNameInput(subjectName) {
    await this.subjectNameInput.sendKeys(subjectName);
  }

  async getSubjectNameInput() {
    return this.subjectNameInput.getAttribute('value');
  }

  getElectiveSubInput() {
    return this.electiveSubInput;
  }
  async academicDepartmentSelectLastOption() {
    await this.academicDepartmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicDepartmentSelectOption(option) {
    await this.academicDepartmentSelect.sendKeys(option);
  }

  getAcademicDepartmentSelect() {
    return this.academicDepartmentSelect;
  }

  async getAcademicDepartmentSelectedOption() {
    return this.academicDepartmentSelect.element(by.css('option:checked')).getText();
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
