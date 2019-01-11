import { element, by, ElementFinder } from 'protractor';

export default class CollegeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.college.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  shortNameInput: ElementFinder = element(by.css('input#college-shortName'));
  logoInput: ElementFinder = element(by.css('input#college-logo'));
  backgroundImageInput: ElementFinder = element(by.css('input#college-backgroundImage'));
  instructionInformationInput: ElementFinder = element(by.css('input#college-instructionInformation'));
  branchSelect: ElementFinder = element(by.css('select#college-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  setShortNameInput(shortName) {
    this.shortNameInput.sendKeys(shortName);
  }

  getShortNameInput() {
    return this.shortNameInput.getAttribute('value');
  }

  setLogoInput(logo) {
    this.logoInput.sendKeys(logo);
  }

  getLogoInput() {
    return this.logoInput.getAttribute('value');
  }

  setBackgroundImageInput(backgroundImage) {
    this.backgroundImageInput.sendKeys(backgroundImage);
  }

  getBackgroundImageInput() {
    return this.backgroundImageInput.getAttribute('value');
  }

  setInstructionInformationInput(instructionInformation) {
    this.instructionInformationInput.sendKeys(instructionInformation);
  }

  getInstructionInformationInput() {
    return this.instructionInformationInput.getAttribute('value');
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
