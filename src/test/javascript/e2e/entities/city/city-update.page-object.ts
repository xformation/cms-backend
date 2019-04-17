import { element, by, ElementFinder } from 'protractor';

export default class CityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.city.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  cityNameInput: ElementFinder = element(by.css('input#city-cityName'));
  cityCodeInput: ElementFinder = element(by.css('input#city-cityCode'));
  stdCodeInput: ElementFinder = element(by.css('input#city-stdCode'));
  stateSelect: ElementFinder = element(by.css('select#city-state'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setCityNameInput(cityName) {
    await this.cityNameInput.sendKeys(cityName);
  }

  async getCityNameInput() {
    return this.cityNameInput.getAttribute('value');
  }

  async setCityCodeInput(cityCode) {
    await this.cityCodeInput.sendKeys(cityCode);
  }

  async getCityCodeInput() {
    return this.cityCodeInput.getAttribute('value');
  }

  async setStdCodeInput(stdCode) {
    await this.stdCodeInput.sendKeys(stdCode);
  }

  async getStdCodeInput() {
    return this.stdCodeInput.getAttribute('value');
  }

  async stateSelectLastOption() {
    await this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async stateSelectOption(option) {
    await this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  async getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
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
