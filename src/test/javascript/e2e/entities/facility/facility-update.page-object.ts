import { element, by, ElementFinder } from 'protractor';

export default class FacilityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.facility.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  facilityNameInput: ElementFinder = element(by.css('input#facility-facilityName'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setFacilityNameInput(facilityName) {
    await this.facilityNameInput.sendKeys(facilityName);
  }

  async getFacilityNameInput() {
    return this.facilityNameInput.getAttribute('value');
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
