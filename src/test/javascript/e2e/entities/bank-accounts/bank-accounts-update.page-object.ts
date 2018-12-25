import { element, by, ElementFinder } from 'protractor';

export default class BankAccountsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.bankAccounts.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameOfBankSelect: ElementFinder = element(by.css('select#bank-accounts-nameOfBank'));
  accountNumberInput: ElementFinder = element(by.css('input#bank-accounts-accountNumber'));
  typeOfAccountInput: ElementFinder = element(by.css('input#bank-accounts-typeOfAccount'));
  ifsCodeInput: ElementFinder = element(by.css('input#bank-accounts-ifsCode'));
  branchInput: ElementFinder = element(by.css('input#bank-accounts-branch'));
  corporateIdInput: ElementFinder = element(by.css('input#bank-accounts-corporateId'));

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

  setIfsCodeInput(ifsCode) {
    this.ifsCodeInput.sendKeys(ifsCode);
  }

  getIfsCodeInput() {
    return this.ifsCodeInput.getAttribute('value');
  }

  setBranchInput(branch) {
    this.branchInput.sendKeys(branch);
  }

  getBranchInput() {
    return this.branchInput.getAttribute('value');
  }

  setCorporateIdInput(corporateId) {
    this.corporateIdInput.sendKeys(corporateId);
  }

  getCorporateIdInput() {
    return this.corporateIdInput.getAttribute('value');
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
