import { element, by, ElementFinder } from 'protractor';

export default class VehicleUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.vehicle.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  vehicleNumberInput: ElementFinder = element(by.css('input#vehicle-vehicleNumber'));
  vehicleTypeInput: ElementFinder = element(by.css('input#vehicle-vehicleType'));
  capacityInput: ElementFinder = element(by.css('input#vehicle-capacity'));
  ownerShipInput: ElementFinder = element(by.css('input#vehicle-ownerShip'));
  dateOfRegistrationInput: ElementFinder = element(by.css('input#vehicle-dateOfRegistration'));
  yearOfManufacturingInput: ElementFinder = element(by.css('input#vehicle-yearOfManufacturing'));
  manufacturingCompanyInput: ElementFinder = element(by.css('input#vehicle-manufacturingCompany'));
  modelInput: ElementFinder = element(by.css('input#vehicle-model'));
  chasisNoInput: ElementFinder = element(by.css('input#vehicle-chasisNo'));
  rcNoInput: ElementFinder = element(by.css('input#vehicle-rcNo'));
  contactNumberInput: ElementFinder = element(by.css('input#vehicle-contactNumber'));
  statusSelect: ElementFinder = element(by.css('select#vehicle-status'));
  insuranceSelect: ElementFinder = element(by.css('select#vehicle-insurance'));
  employeeSelect: ElementFinder = element(by.css('select#vehicle-employee'));
  transportRouteSelect: ElementFinder = element(by.css('select#vehicle-transportRoute'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setVehicleNumberInput(vehicleNumber) {
    await this.vehicleNumberInput.sendKeys(vehicleNumber);
  }

  async getVehicleNumberInput() {
    return this.vehicleNumberInput.getAttribute('value');
  }

  async setVehicleTypeInput(vehicleType) {
    await this.vehicleTypeInput.sendKeys(vehicleType);
  }

  async getVehicleTypeInput() {
    return this.vehicleTypeInput.getAttribute('value');
  }

  async setCapacityInput(capacity) {
    await this.capacityInput.sendKeys(capacity);
  }

  async getCapacityInput() {
    return this.capacityInput.getAttribute('value');
  }

  async setOwnerShipInput(ownerShip) {
    await this.ownerShipInput.sendKeys(ownerShip);
  }

  async getOwnerShipInput() {
    return this.ownerShipInput.getAttribute('value');
  }

  async setDateOfRegistrationInput(dateOfRegistration) {
    await this.dateOfRegistrationInput.sendKeys(dateOfRegistration);
  }

  async getDateOfRegistrationInput() {
    return this.dateOfRegistrationInput.getAttribute('value');
  }

  async setYearOfManufacturingInput(yearOfManufacturing) {
    await this.yearOfManufacturingInput.sendKeys(yearOfManufacturing);
  }

  async getYearOfManufacturingInput() {
    return this.yearOfManufacturingInput.getAttribute('value');
  }

  async setManufacturingCompanyInput(manufacturingCompany) {
    await this.manufacturingCompanyInput.sendKeys(manufacturingCompany);
  }

  async getManufacturingCompanyInput() {
    return this.manufacturingCompanyInput.getAttribute('value');
  }

  async setModelInput(model) {
    await this.modelInput.sendKeys(model);
  }

  async getModelInput() {
    return this.modelInput.getAttribute('value');
  }

  async setChasisNoInput(chasisNo) {
    await this.chasisNoInput.sendKeys(chasisNo);
  }

  async getChasisNoInput() {
    return this.chasisNoInput.getAttribute('value');
  }

  async setRcNoInput(rcNo) {
    await this.rcNoInput.sendKeys(rcNo);
  }

  async getRcNoInput() {
    return this.rcNoInput.getAttribute('value');
  }

  async setContactNumberInput(contactNumber) {
    await this.contactNumberInput.sendKeys(contactNumber);
  }

  async getContactNumberInput() {
    return this.contactNumberInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async insuranceSelectLastOption() {
    await this.insuranceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async insuranceSelectOption(option) {
    await this.insuranceSelect.sendKeys(option);
  }

  getInsuranceSelect() {
    return this.insuranceSelect;
  }

  async getInsuranceSelectedOption() {
    return this.insuranceSelect.element(by.css('option:checked')).getText();
  }

  async employeeSelectLastOption() {
    await this.employeeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async employeeSelectOption(option) {
    await this.employeeSelect.sendKeys(option);
  }

  getEmployeeSelect() {
    return this.employeeSelect;
  }

  async getEmployeeSelectedOption() {
    return this.employeeSelect.element(by.css('option:checked')).getText();
  }

  async transportRouteSelectLastOption() {
    await this.transportRouteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async transportRouteSelectOption(option) {
    await this.transportRouteSelect.sendKeys(option);
  }

  getTransportRouteSelect() {
    return this.transportRouteSelect;
  }

  async getTransportRouteSelectedOption() {
    return this.transportRouteSelect.element(by.css('option:checked')).getText();
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
