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

  async setNameOfBankSelect(nameOfBank) {
    await this.nameOfBankSelect.sendKeys(nameOfBank);
  }

  async getNameOfBankSelect() {
    return this.nameOfBankSelect.element(by.css('option:checked')).getText();
  }

  async nameOfBankSelectLastOption() {
    await this.nameOfBankSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setAccountNumberInput(accountNumber) {
    await this.accountNumberInput.sendKeys(accountNumber);
  }

  async getAccountNumberInput() {
    return this.accountNumberInput.getAttribute('value');
  }

  async setTypeOfAccountInput(typeOfAccount) {
    await this.typeOfAccountInput.sendKeys(typeOfAccount);
  }

  async getTypeOfAccountInput() {
    return this.typeOfAccountInput.getAttribute('value');
  }

  async setIfscCodeInput(ifscCode) {
    await this.ifscCodeInput.sendKeys(ifscCode);
  }

  async getIfscCodeInput() {
    return this.ifscCodeInput.getAttribute('value');
  }

  async setBranchAddressInput(branchAddress) {
    await this.branchAddressInput.sendKeys(branchAddress);
  }

  async getBranchAddressInput() {
    return this.branchAddressInput.getAttribute('value');
  }

  async setCorporateIdInput(corporateId) {
    await this.corporateIdInput.sendKeys(corporateId);
  }

  async getCorporateIdInput() {
    return this.corporateIdInput.getAttribute('value');
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
