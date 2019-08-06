import { element, by, ElementFinder } from 'protractor';

export default class ContractUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.contract.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  vendorNameInput: ElementFinder = element(by.css('input#contract-vendorName'));
  typeOfOwnerShipSelect: ElementFinder = element(by.css('select#contract-typeOfOwnerShip'));
  durationOfContractInput: ElementFinder = element(by.css('input#contract-durationOfContract'));
  startDateInput: ElementFinder = element(by.css('input#contract-startDate'));
  endDateInput: ElementFinder = element(by.css('input#contract-endDate'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setVendorNameInput(vendorName) {
    await this.vendorNameInput.sendKeys(vendorName);
  }

  async getVendorNameInput() {
    return this.vendorNameInput.getAttribute('value');
  }

  async setTypeOfOwnerShipSelect(typeOfOwnerShip) {
    await this.typeOfOwnerShipSelect.sendKeys(typeOfOwnerShip);
  }

  async getTypeOfOwnerShipSelect() {
    return this.typeOfOwnerShipSelect.element(by.css('option:checked')).getText();
  }

  async typeOfOwnerShipSelectLastOption() {
    await this.typeOfOwnerShipSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setDurationOfContractInput(durationOfContract) {
    await this.durationOfContractInput.sendKeys(durationOfContract);
  }

  async getDurationOfContractInput() {
    return this.durationOfContractInput.getAttribute('value');
  }

  async setStartDateInput(startDate) {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput() {
    return this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate) {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput() {
    return this.endDateInput.getAttribute('value');
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
