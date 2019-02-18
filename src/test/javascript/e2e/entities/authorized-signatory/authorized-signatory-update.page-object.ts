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

  setAddress1Input(address1) {
    this.address1Input.sendKeys(address1);
  }

  getAddress1Input() {
    return this.address1Input.getAttribute('value');
  }

  setAddress2Input(address2) {
    this.address2Input.sendKeys(address2);
  }

  getAddress2Input() {
    return this.address2Input.getAttribute('value');
  }

  setAddress3Input(address3) {
    this.address3Input.sendKeys(address3);
  }

  getAddress3Input() {
    return this.address3Input.getAttribute('value');
  }

  setAddress4Input(address4) {
    this.address4Input.sendKeys(address4);
  }

  getAddress4Input() {
    return this.address4Input.getAttribute('value');
  }

  setAddress5Input(address5) {
    this.address5Input.sendKeys(address5);
  }

  getAddress5Input() {
    return this.address5Input.getAttribute('value');
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

  branchSelectLastOption() {
    this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  branchSelectOption(option) {
    this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  collegeSelectLastOption() {
    this.collegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  collegeSelectOption(option) {
    this.collegeSelect.sendKeys(option);
  }

  getCollegeSelect() {
    return this.collegeSelect;
  }

  getCollegeSelectedOption() {
    return this.collegeSelect.element(by.css('option:checked')).getText();
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
