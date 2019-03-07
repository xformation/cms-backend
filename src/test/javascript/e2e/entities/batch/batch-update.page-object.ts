import { element, by, ElementFinder } from 'protractor';

export default class BatchUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.batch.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  batchSelect: ElementFinder = element(by.css('select#batch-batch'));
  departmentSelect: ElementFinder = element(by.css('select#batch-department'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBatchSelect(batch) {
    await this.batchSelect.sendKeys(batch);
  }

  async getBatchSelect() {
    return this.batchSelect.element(by.css('option:checked')).getText();
  }

  async batchSelectLastOption() {
    await this.batchSelect
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
