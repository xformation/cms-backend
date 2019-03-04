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

  setBranchNameInput(branchName) {
    this.branchNameInput.sendKeys(branchName);
  }

  getBranchNameInput() {
    return this.branchNameInput.getAttribute('value');
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

  setBranchHeadInput(branchHead) {
    this.branchHeadInput.sendKeys(branchHead);
  }

  getBranchHeadInput() {
    return this.branchHeadInput.getAttribute('value');
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

  citySelectLastOption() {
    this.citySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  citySelectOption(option) {
    this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
  }

  stateSelectLastOption() {
    this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  stateSelectOption(option) {
    this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
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
