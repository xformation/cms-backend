import { element, by, ElementFinder } from 'protractor';

export default class StateUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.state.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  stateNameInput: ElementFinder = element(by.css('input#state-stateName'));
  divisionTypeInput: ElementFinder = element(by.css('input#state-divisionType'));
  stateCodeInput: ElementFinder = element(by.css('input#state-stateCode'));
  countrySelect: ElementFinder = element(by.css('select#state-country'));

  getPageTitle() {
    return this.pageTitle;
  }

  setStateNameInput(stateName) {
    this.stateNameInput.sendKeys(stateName);
  }

  getStateNameInput() {
    return this.stateNameInput.getAttribute('value');
  }

  setDivisionTypeInput(divisionType) {
    this.divisionTypeInput.sendKeys(divisionType);
  }

  getDivisionTypeInput() {
    return this.divisionTypeInput.getAttribute('value');
  }

  setStateCodeInput(stateCode) {
    this.stateCodeInput.sendKeys(stateCode);
  }

  getStateCodeInput() {
    return this.stateCodeInput.getAttribute('value');
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
