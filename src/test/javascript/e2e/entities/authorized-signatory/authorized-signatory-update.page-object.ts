import { element, by, ElementFinder } from 'protractor';

export default class AuthorizedSignatoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.authorizedSignatory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  signatoryNameInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryName'));
  signatoryFatherNameInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryFatherName'));
  signatoryDesignationInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryDesignation'));
  addressInput: ElementFinder = element(by.css('input#authorized-signatory-address'));
  emailInput: ElementFinder = element(by.css('input#authorized-signatory-email'));
  panCardNumberInput: ElementFinder = element(by.css('input#authorized-signatory-panCardNumber'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSignatoryNameInput(signatoryName) {
    this.signatoryNameInput.sendKeys(signatoryName);
  }

  getSignatoryNameInput() {
    return this.signatoryNameInput.getAttribute('value');
  }

  setSignatoryFatherNameInput(signatoryFatherName) {
    this.signatoryFatherNameInput.sendKeys(signatoryFatherName);
  }

  getSignatoryFatherNameInput() {
    return this.signatoryFatherNameInput.getAttribute('value');
  }

  setSignatoryDesignationInput(signatoryDesignation) {
    this.signatoryDesignationInput.sendKeys(signatoryDesignation);
  }

  getSignatoryDesignationInput() {
    return this.signatoryDesignationInput.getAttribute('value');
  }

  setAddressInput(address) {
    this.addressInput.sendKeys(address);
  }

  getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  setEmailInput(email) {
    this.emailInput.sendKeys(email);
  }

  getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  setPanCardNumberInput(panCardNumber) {
    this.panCardNumberInput.sendKeys(panCardNumber);
  }

  getPanCardNumberInput() {
    return this.panCardNumberInput.getAttribute('value');
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
