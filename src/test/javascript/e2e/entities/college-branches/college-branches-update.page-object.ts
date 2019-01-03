import { element, by, ElementFinder } from 'protractor';

export default class CollegeBranchesUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.collegeBranches.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  branchNameInput: ElementFinder = element(by.css('input#college-branches-branchName'));
  descriptionInput: ElementFinder = element(by.css('input#college-branches-description'));
  collegeHeadInput: ElementFinder = element(by.css('input#college-branches-collegeHead'));

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
