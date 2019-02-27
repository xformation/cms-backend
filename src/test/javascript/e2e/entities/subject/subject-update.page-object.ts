import { element, by, ElementFinder } from 'protractor';

export default class SubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.subject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  subjectCodeInput: ElementFinder = element(by.css('input#subject-subjectCode'));
  subjectTypeSelect: ElementFinder = element(by.css('select#subject-subjectType'));
  subjectDescInput: ElementFinder = element(by.css('input#subject-subjectDesc'));
  statusSelect: ElementFinder = element(by.css('select#subject-status'));
  departmentSelect: ElementFinder = element(by.css('select#subject-department'));
  batchSelect: ElementFinder = element(by.css('select#subject-batch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSubjectCodeInput(subjectCode) {
    await this.subjectCodeInput.sendKeys(subjectCode);
  }

  async getSubjectCodeInput() {
    return this.subjectCodeInput.getAttribute('value');
  }

  async setSubjectTypeSelect(subjectType) {
    await this.subjectTypeSelect.sendKeys(subjectType);
  }

  async getSubjectTypeSelect() {
    return this.subjectTypeSelect.element(by.css('option:checked')).getText();
  }

  async subjectTypeSelectLastOption() {
    await this.subjectTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setSubjectDescInput(subjectDesc) {
    await this.subjectDescInput.sendKeys(subjectDesc);
  }

  async getSubjectDescInput() {
    return this.subjectDescInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async departmentSelectLastOption() {
    await this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentSelectOption(option) {
    await this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  async getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  async batchSelectLastOption() {
    await this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async batchSelectOption(option) {
    await this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  async getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
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
