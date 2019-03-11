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

  setCurrencyNameInput(currencyName) {
    this.currencyNameInput.sendKeys(currencyName);
  }

  getCurrencyNameInput() {
    return this.currencyNameInput.getAttribute('value');
  }

  setCurrencyCodeInput(currencyCode) {
    this.currencyCodeInput.sendKeys(currencyCode);
  }

  getCurrencyCodeInput() {
    return this.currencyCodeInput.getAttribute('value');
  }

  setCurrencySymbolInput(currencySymbol) {
    this.currencySymbolInput.sendKeys(currencySymbol);
  }

  getCurrencySymbolInput() {
    return this.currencySymbolInput.getAttribute('value');
  }

  countrySelectLastOption() {
    this.countrySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  countrySelectOption(option) {
    this.countrySelect.sendKeys(option);
  }

  getCountrySelect() {
    return this.countrySelect;
  }

  getCountrySelectedOption() {
    return this.countrySelect.element(by.css('option:checked')).getText();
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
