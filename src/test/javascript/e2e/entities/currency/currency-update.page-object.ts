import { element, by, ElementFinder } from 'protractor';

export default class CurrencyUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.currency.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  currencyNameInput: ElementFinder = element(by.css('input#currency-currencyName'));
  currencyCodeInput: ElementFinder = element(by.css('input#currency-currencyCode'));
  currencySymbolInput: ElementFinder = element(by.css('input#currency-currencySymbol'));
  countrySelect: ElementFinder = element(by.css('select#currency-country'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCurrencyNameInput(currencyName) {
    await this.currencyNameInput.sendKeys(currencyName);
  }

  async getCurrencyNameInput() {
    return this.currencyNameInput.getAttribute('value');
  }

  async setCurrencyCodeInput(currencyCode) {
    await this.currencyCodeInput.sendKeys(currencyCode);
  }

  async getCurrencyCodeInput() {
    return this.currencyCodeInput.getAttribute('value');
  }

  async setCurrencySymbolInput(currencySymbol) {
    await this.currencySymbolInput.sendKeys(currencySymbol);
  }

  async getCurrencySymbolInput() {
    return this.currencySymbolInput.getAttribute('value');
  }

  async countrySelectLastOption() {
    await this.countrySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async countrySelectOption(option) {
    await this.countrySelect.sendKeys(option);
  }

  getCountrySelect() {
    return this.countrySelect;
  }

  async getCountrySelectedOption() {
    return this.countrySelect.element(by.css('option:checked')).getText();
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
