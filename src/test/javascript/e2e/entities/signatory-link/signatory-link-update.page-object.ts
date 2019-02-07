import { element, by, ElementFinder } from 'protractor';

export default class SignatoryLinkUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.signatoryLink.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#signatory-link-desc'));
  authorizedSignatorySelect: ElementFinder = element(by.css('select#signatory-link-authorizedSignatory'));
  legalEntitySelect: ElementFinder = element(by.css('select#signatory-link-legalEntity'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescInput(desc) {
    await this.descInput.sendKeys(desc);
  }

  async getDescInput() {
    return this.descInput.getAttribute('value');
  }

  async authorizedSignatorySelectLastOption() {
    await this.authorizedSignatorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async authorizedSignatorySelectOption(option) {
    await this.authorizedSignatorySelect.sendKeys(option);
  }

  getAuthorizedSignatorySelect() {
    return this.authorizedSignatorySelect;
  }

  async getAuthorizedSignatorySelectedOption() {
    return this.authorizedSignatorySelect.element(by.css('option:checked')).getText();
  }

  async legalEntitySelectLastOption() {
    await this.legalEntitySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async legalEntitySelectOption(option) {
    await this.legalEntitySelect.sendKeys(option);
  }

  getLegalEntitySelect() {
    return this.legalEntitySelect;
  }

  async getLegalEntitySelectedOption() {
    return this.legalEntitySelect.element(by.css('option:checked')).getText();
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
