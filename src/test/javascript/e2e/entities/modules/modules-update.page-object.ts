import { element, by, ElementFinder } from 'protractor';

export default class ModulesUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.modules.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  moduleNameInput: ElementFinder = element(by.css('input#modules-moduleName'));
  subModuleNameInput: ElementFinder = element(by.css('input#modules-subModuleName'));
  urlInput: ElementFinder = element(by.css('input#modules-url'));
  statusSelect: ElementFinder = element(by.css('select#modules-status'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setModuleNameInput(moduleName) {
    await this.moduleNameInput.sendKeys(moduleName);
  }

  async getModuleNameInput() {
    return this.moduleNameInput.getAttribute('value');
  }

  async setSubModuleNameInput(subModuleName) {
    await this.subModuleNameInput.sendKeys(subModuleName);
  }

  async getSubModuleNameInput() {
    return this.subModuleNameInput.getAttribute('value');
  }

  async setUrlInput(url) {
    await this.urlInput.sendKeys(url);
  }

  async getUrlInput() {
    return this.urlInput.getAttribute('value');
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
