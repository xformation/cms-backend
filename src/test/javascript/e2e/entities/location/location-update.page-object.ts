import { element, by, ElementFinder } from 'protractor';

export default class LocationUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.location.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameInput: ElementFinder = element(by.css('input#location-name'));
  addressInput: ElementFinder = element(by.css('input#location-address'));
  appliesToInput: ElementFinder = element(by.css('input#location-appliesTo'));

  getPageTitle() {
    return this.pageTitle;
  }

  setNameInput(name) {
    this.nameInput.sendKeys(name);
  }

  getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  setAddressInput(address) {
    this.addressInput.sendKeys(address);
  }

  getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  setAppliesToInput(appliesTo) {
    this.appliesToInput.sendKeys(appliesTo);
  }

  getAppliesToInput() {
    return this.appliesToInput.getAttribute('value');
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
