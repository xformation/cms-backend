import { element, by, ElementFinder } from 'protractor';

export default class TransportRouteUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.transportRoute.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  routeNameInput: ElementFinder = element(by.css('input#transport-route-routeName'));
  routeDetailsInput: ElementFinder = element(by.css('input#transport-route-routeDetails'));
  routeMapUrlInput: ElementFinder = element(by.css('input#transport-route-routeMapUrl'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setRouteNameInput(routeName) {
    await this.routeNameInput.sendKeys(routeName);
  }

  async getRouteNameInput() {
    return this.routeNameInput.getAttribute('value');
  }

  async setRouteDetailsInput(routeDetails) {
    await this.routeDetailsInput.sendKeys(routeDetails);
  }

  async getRouteDetailsInput() {
    return this.routeDetailsInput.getAttribute('value');
  }

  async setRouteMapUrlInput(routeMapUrl) {
    await this.routeMapUrlInput.sendKeys(routeMapUrl);
  }

  async getRouteMapUrlInput() {
    return this.routeMapUrlInput.getAttribute('value');
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
