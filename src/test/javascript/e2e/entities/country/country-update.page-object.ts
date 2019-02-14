import { element, by, ElementFinder } from 'protractor';

export default class CountryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.country.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  countryNameInput: ElementFinder = element(by.css('input#country-countryName'));
  countryCodeInput: ElementFinder = element(by.css('input#country-countryCode'));
  isdCodeInput: ElementFinder = element(by.css('input#country-isdCode'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCountryNameInput(countryName) {
    await this.countryNameInput.sendKeys(countryName);
  }

  async getCountryNameInput() {
    return this.countryNameInput.getAttribute('value');
  }

  async setCountryCodeInput(countryCode) {
    await this.countryCodeInput.sendKeys(countryCode);
  }

  async getCountryCodeInput() {
    return this.countryCodeInput.getAttribute('value');
  }

  async setIsdCodeInput(isdCode) {
    await this.isdCodeInput.sendKeys(isdCode);
  }

  async getIsdCodeInput() {
    return this.isdCodeInput.getAttribute('value');
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
