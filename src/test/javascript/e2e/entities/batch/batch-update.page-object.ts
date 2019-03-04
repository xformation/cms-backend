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

  setBatchSelect(batch) {
    this.batchSelect.sendKeys(batch);
  }

  getBatchSelect() {
    return this.batchSelect.element(by.css('option:checked')).getText();
  }

  batchSelectLastOption() {
    this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
