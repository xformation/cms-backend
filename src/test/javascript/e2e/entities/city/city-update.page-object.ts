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

  setCityNameInput(cityName) {
    this.cityNameInput.sendKeys(cityName);
  }

  getCityNameInput() {
    return this.cityNameInput.getAttribute('value');
  }

  setCityCodeInput(cityCode) {
    this.cityCodeInput.sendKeys(cityCode);
  }

  getCityCodeInput() {
    return this.cityCodeInput.getAttribute('value');
  }

  setStdCodeInput(stdCode) {
    this.stdCodeInput.sendKeys(stdCode);
  }

  getStdCodeInput() {
    return this.stdCodeInput.getAttribute('value');
  }

  stateSelectLastOption() {
    this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  stateSelectOption(option) {
    this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
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
