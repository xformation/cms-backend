import { element, by, ElementFinder } from 'protractor';

export default class BankAccountsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.bankAccounts.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameOfBankSelect: ElementFinder = element(by.css('select#bank-accounts-nameOfBank'));
  accountNumberInput: ElementFinder = element(by.css('input#bank-accounts-accountNumber'));
  typeOfAccountInput: ElementFinder = element(by.css('input#bank-accounts-typeOfAccount'));
  ifscCodeInput: ElementFinder = element(by.css('input#bank-accounts-ifscCode'));
  branchAddressInput: ElementFinder = element(by.css('input#bank-accounts-branchAddress'));
  corporateIdInput: ElementFinder = element(by.css('input#bank-accounts-corporateId'));
  branchSelect: ElementFinder = element(by.css('select#bank-accounts-branch'));
  collegeSelect: ElementFinder = element(by.css('select#bank-accounts-college'));

  getPageTitle() {
    return this.pageTitle;
  }

  setNameOfBankSelect(nameOfBank) {
    this.nameOfBankSelect.sendKeys(nameOfBank);
  }

  getNameOfBankSelect() {
    return this.nameOfBankSelect.element(by.css('option:checked')).getText();
  }

  nameOfBankSelectLastOption() {
    this.nameOfBankSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setAccountNumberInput(accountNumber) {
    this.accountNumberInput.sendKeys(accountNumber);
  }

  getAccountNumberInput() {
    return this.accountNumberInput.getAttribute('value');
  }

  setTypeOfAccountInput(typeOfAccount) {
    this.typeOfAccountInput.sendKeys(typeOfAccount);
  }

  getTypeOfAccountInput() {
    return this.typeOfAccountInput.getAttribute('value');
  }

  setIfscCodeInput(ifscCode) {
    this.ifscCodeInput.sendKeys(ifscCode);
  }

  getIfscCodeInput() {
    return this.ifscCodeInput.getAttribute('value');
  }

  setBranchAddressInput(branchAddress) {
    this.branchAddressInput.sendKeys(branchAddress);
  }

  getBranchAddressInput() {
    return this.branchAddressInput.getAttribute('value');
  }

  setCorporateIdInput(corporateId) {
    this.corporateIdInput.sendKeys(corporateId);
  }

  getCorporateIdInput() {
    return this.corporateIdInput.getAttribute('value');
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
