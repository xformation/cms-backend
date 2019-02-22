import { element, by, ElementFinder } from 'protractor';

export default class FeeCategoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.feeCategory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  categoryNameInput: ElementFinder = element(by.css('input#fee-category-categoryName'));
  descriptionInput: ElementFinder = element(by.css('input#fee-category-description'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCategoryNameInput(categoryName) {
    await this.categoryNameInput.sendKeys(categoryName);
  }

  async getCategoryNameInput() {
    return this.categoryNameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
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
