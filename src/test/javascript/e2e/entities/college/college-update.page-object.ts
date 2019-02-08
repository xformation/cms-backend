import { element, by, ElementFinder } from 'protractor';

export default class CollegeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.college.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  shortNameInput: ElementFinder = element(by.css('input#college-shortName'));
  logoInput: ElementFinder = element(by.css('input#college-logo'));
  backgroundImageInput: ElementFinder = element(by.css('input#college-backgroundImage'));
  instructionInformationInput: ElementFinder = element(by.css('input#college-instructionInformation'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setShortNameInput(shortName) {
    await this.shortNameInput.sendKeys(shortName);
  }

  async getShortNameInput() {
    return this.shortNameInput.getAttribute('value');
  }

  async setLogoInput(logo) {
    await this.logoInput.sendKeys(logo);
  }

  async getLogoInput() {
    return this.logoInput.getAttribute('value');
  }

  async setBackgroundImageInput(backgroundImage) {
    await this.backgroundImageInput.sendKeys(backgroundImage);
  }

  async getBackgroundImageInput() {
    return this.backgroundImageInput.getAttribute('value');
  }

  async setInstructionInformationInput(instructionInformation) {
    await this.instructionInformationInput.sendKeys(instructionInformation);
  }

  async getInstructionInformationInput() {
    return this.instructionInformationInput.getAttribute('value');
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
