import { element, by, ElementFinder } from 'protractor';

export default class AuthorizedSignatoryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.authorizedSignatory.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  signatoryNameInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryName'));
  signatoryFatherNameInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryFatherName'));
  signatoryDesignationInput: ElementFinder = element(by.css('input#authorized-signatory-signatoryDesignation'));
  address1Input: ElementFinder = element(by.css('input#authorized-signatory-address1'));
  address2Input: ElementFinder = element(by.css('input#authorized-signatory-address2'));
  address3Input: ElementFinder = element(by.css('input#authorized-signatory-address3'));
  address4Input: ElementFinder = element(by.css('input#authorized-signatory-address4'));
  address5Input: ElementFinder = element(by.css('input#authorized-signatory-address5'));
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

  async setAddress1Input(address1) {
    await this.address1Input.sendKeys(address1);
  }

  async getAddress1Input() {
    return this.address1Input.getAttribute('value');
  }

  async setAddress2Input(address2) {
    await this.address2Input.sendKeys(address2);
  }

  async getAddress2Input() {
    return this.address2Input.getAttribute('value');
  }

  async setAddress3Input(address3) {
    await this.address3Input.sendKeys(address3);
  }

  async getAddress3Input() {
    return this.address3Input.getAttribute('value');
  }

  async setAddress4Input(address4) {
    await this.address4Input.sendKeys(address4);
  }

  async getAddress4Input() {
    return this.address4Input.getAttribute('value');
  }

  async setAddress5Input(address5) {
    await this.address5Input.sendKeys(address5);
  }

  async getAddress5Input() {
    return this.address5Input.getAttribute('value');
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
