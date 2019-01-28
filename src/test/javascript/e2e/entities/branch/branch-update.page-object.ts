import { element, by, ElementFinder } from 'protractor';

export default class BranchUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.branch.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  branchNameInput: ElementFinder = element(by.css('input#branch-branchName'));
  descriptionInput: ElementFinder = element(by.css('input#branch-description'));
  collegeHeadInput: ElementFinder = element(by.css('input#branch-collegeHead'));
  collegeSelect: ElementFinder = element(by.css('select#branch-college'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBranchNameInput(branchName) {
    await this.branchNameInput.sendKeys(branchName);
  }

  async getBranchNameInput() {
    return this.branchNameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setCollegeHeadInput(collegeHead) {
    await this.collegeHeadInput.sendKeys(collegeHead);
  }

  async getCollegeHeadInput() {
    return this.collegeHeadInput.getAttribute('value');
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
