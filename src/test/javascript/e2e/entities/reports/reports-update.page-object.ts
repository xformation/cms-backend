import { element, by, ElementFinder } from 'protractor';

export default class ReportsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.reports.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#reports-desc'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescInput(desc) {
    await this.descInput.sendKeys(desc);
  }

  async getDescInput() {
    return this.descInput.getAttribute('value');
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
