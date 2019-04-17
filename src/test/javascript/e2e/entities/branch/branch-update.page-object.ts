import { element, by, ElementFinder } from 'protractor';

export default class BranchUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.branch.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  branchNameInput: ElementFinder = element(by.css('input#branch-branchName'));
  address1Input: ElementFinder = element(by.css('input#branch-address1'));
  address2Input: ElementFinder = element(by.css('input#branch-address2'));
  branchHeadInput: ElementFinder = element(by.css('input#branch-branchHead'));
  collegeSelect: ElementFinder = element(by.css('select#branch-college'));
  citySelect: ElementFinder = element(by.css('select#branch-city'));
  stateSelect: ElementFinder = element(by.css('select#branch-state'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBranchNameInput(branchName) {
    await this.branchNameInput.sendKeys(branchName);
  }

  async getBranchNameInput() {
    return this.branchNameInput.getAttribute('value');
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

  async setBranchHeadInput(branchHead) {
    await this.branchHeadInput.sendKeys(branchHead);
  }

  async getBranchHeadInput() {
    return this.branchHeadInput.getAttribute('value');
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

  async citySelectLastOption() {
    await this.citySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async citySelectOption(option) {
    await this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  async getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
  }

  async stateSelectLastOption() {
    await this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async stateSelectOption(option) {
    await this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  async getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
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
