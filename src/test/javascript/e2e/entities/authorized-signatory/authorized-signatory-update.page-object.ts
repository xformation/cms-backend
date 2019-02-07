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
  branchSelect: ElementFinder = element(by.css('select#authorized-signatory-branch'));
  collegeSelect: ElementFinder = element(by.css('select#authorized-signatory-college'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSignatoryNameInput(signatoryName) {
    await this.signatoryNameInput.sendKeys(signatoryName);
  }

  async getSignatoryNameInput() {
    return this.signatoryNameInput.getAttribute('value');
  }

  async setSignatoryFatherNameInput(signatoryFatherName) {
    await this.signatoryFatherNameInput.sendKeys(signatoryFatherName);
  }

  async getSignatoryFatherNameInput() {
    return this.signatoryFatherNameInput.getAttribute('value');
  }

  async setSignatoryDesignationInput(signatoryDesignation) {
    await this.signatoryDesignationInput.sendKeys(signatoryDesignation);
  }

  async getSignatoryDesignationInput() {
    return this.signatoryDesignationInput.getAttribute('value');
  }

  async setAddressInput(address) {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput() {
    return this.addressInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setPanCardNumberInput(panCardNumber) {
    await this.panCardNumberInput.sendKeys(panCardNumber);
  }

  async getPanCardNumberInput() {
    return this.panCardNumberInput.getAttribute('value');
  }

  async branchSelectLastOption() {
    await this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async branchSelectOption(option) {
    await this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  async getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  async collegeSelectLastOption() {
    await this.collegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async collegeSelectOption(option) {
    await this.collegeSelect.sendKeys(option);
  }

  getCollegeSelect() {
    return this.collegeSelect;
  }

  async getCollegeSelectedOption() {
    return this.collegeSelect.element(by.css('option:checked')).getText();
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
