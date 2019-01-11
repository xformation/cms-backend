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

  setBranchNameInput(branchName) {
    this.branchNameInput.sendKeys(branchName);
  }

  getBranchNameInput() {
    return this.branchNameInput.getAttribute('value');
  }

  setDescriptionInput(description) {
    this.descriptionInput.sendKeys(description);
  }

  getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  setCollegeHeadInput(collegeHead) {
    this.collegeHeadInput.sendKeys(collegeHead);
  }

  getCollegeHeadInput() {
    return this.collegeHeadInput.getAttribute('value');
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
