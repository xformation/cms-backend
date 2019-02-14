import { element, by, ElementFinder } from 'protractor';

export default class LegalEntityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.legalEntity.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  logoInput: ElementFinder = element(by.css('input#legal-entity-logo'));
  legalNameOfTheCollegeInput: ElementFinder = element(by.css('input#legal-entity-legalNameOfTheCollege'));
  typeOfCollegeSelect: ElementFinder = element(by.css('select#legal-entity-typeOfCollege'));
  dateOfIncorporationInput: ElementFinder = element(by.css('input#legal-entity-dateOfIncorporation'));
  registeredOfficeAddressInput: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress'));
  collegeIdentificationNumberInput: ElementFinder = element(by.css('input#legal-entity-collegeIdentificationNumber'));
  panInput: ElementFinder = element(by.css('input#legal-entity-pan'));
  tanInput: ElementFinder = element(by.css('input#legal-entity-tan'));
  tanCircleNumberInput: ElementFinder = element(by.css('input#legal-entity-tanCircleNumber'));
  citTdsLocationInput: ElementFinder = element(by.css('input#legal-entity-citTdsLocation'));
  formSignatoryInput: ElementFinder = element(by.css('input#legal-entity-formSignatory'));
  pfNumberInput: ElementFinder = element(by.css('input#legal-entity-pfNumber'));
  pfRegistrationDateInput: ElementFinder = element(by.css('input#legal-entity-pfRegistrationDate'));
  pfSignatoryInput: ElementFinder = element(by.css('input#legal-entity-pfSignatory'));
  esiNumberInput: ElementFinder = element(by.css('input#legal-entity-esiNumber'));
  esiRegistrationDateInput: ElementFinder = element(by.css('input#legal-entity-esiRegistrationDate'));
  esiSignatoryInput: ElementFinder = element(by.css('input#legal-entity-esiSignatory'));
  ptNumberInput: ElementFinder = element(by.css('input#legal-entity-ptNumber'));
  ptRegistrationDateInput: ElementFinder = element(by.css('input#legal-entity-ptRegistrationDate'));
  ptSignatoryInput: ElementFinder = element(by.css('input#legal-entity-ptSignatory'));
  branchSelect: ElementFinder = element(by.css('select#legal-entity-branch'));
  collegeSelect: ElementFinder = element(by.css('select#legal-entity-college'));
  stateSelect: ElementFinder = element(by.css('select#legal-entity-state'));
  citySelect: ElementFinder = element(by.css('select#legal-entity-city'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setLogoInput(logo) {
    await this.logoInput.sendKeys(logo);
  }

  async getLogoInput() {
    return this.logoInput.getAttribute('value');
  }

  async setLegalNameOfTheCollegeInput(legalNameOfTheCollege) {
    await this.legalNameOfTheCollegeInput.sendKeys(legalNameOfTheCollege);
  }

  async getLegalNameOfTheCollegeInput() {
    return this.legalNameOfTheCollegeInput.getAttribute('value');
  }

  async setTypeOfCollegeSelect(typeOfCollege) {
    await this.typeOfCollegeSelect.sendKeys(typeOfCollege);
  }

  async getTypeOfCollegeSelect() {
    return this.typeOfCollegeSelect.element(by.css('option:checked')).getText();
  }

  async typeOfCollegeSelectLastOption() {
    await this.typeOfCollegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setDateOfIncorporationInput(dateOfIncorporation) {
    await this.dateOfIncorporationInput.sendKeys(dateOfIncorporation);
  }

  async getDateOfIncorporationInput() {
    return this.dateOfIncorporationInput.getAttribute('value');
  }

  async setRegisteredOfficeAddressInput(registeredOfficeAddress) {
    await this.registeredOfficeAddressInput.sendKeys(registeredOfficeAddress);
  }

  async getRegisteredOfficeAddressInput() {
    return this.registeredOfficeAddressInput.getAttribute('value');
  }

  async setCollegeIdentificationNumberInput(collegeIdentificationNumber) {
    await this.collegeIdentificationNumberInput.sendKeys(collegeIdentificationNumber);
  }

  async getCollegeIdentificationNumberInput() {
    return this.collegeIdentificationNumberInput.getAttribute('value');
  }

  async setPanInput(pan) {
    await this.panInput.sendKeys(pan);
  }

  async getPanInput() {
    return this.panInput.getAttribute('value');
  }

  async setTanInput(tan) {
    await this.tanInput.sendKeys(tan);
  }

  async getTanInput() {
    return this.tanInput.getAttribute('value');
  }

  async setTanCircleNumberInput(tanCircleNumber) {
    await this.tanCircleNumberInput.sendKeys(tanCircleNumber);
  }

  async getTanCircleNumberInput() {
    return this.tanCircleNumberInput.getAttribute('value');
  }

  async setCitTdsLocationInput(citTdsLocation) {
    await this.citTdsLocationInput.sendKeys(citTdsLocation);
  }

  async getCitTdsLocationInput() {
    return this.citTdsLocationInput.getAttribute('value');
  }

  async setFormSignatoryInput(formSignatory) {
    await this.formSignatoryInput.sendKeys(formSignatory);
  }

  async getFormSignatoryInput() {
    return this.formSignatoryInput.getAttribute('value');
  }

  async setPfNumberInput(pfNumber) {
    await this.pfNumberInput.sendKeys(pfNumber);
  }

  async getPfNumberInput() {
    return this.pfNumberInput.getAttribute('value');
  }

  async setPfRegistrationDateInput(pfRegistrationDate) {
    await this.pfRegistrationDateInput.sendKeys(pfRegistrationDate);
  }

  async getPfRegistrationDateInput() {
    return this.pfRegistrationDateInput.getAttribute('value');
  }

  async setPfSignatoryInput(pfSignatory) {
    await this.pfSignatoryInput.sendKeys(pfSignatory);
  }

  async getPfSignatoryInput() {
    return this.pfSignatoryInput.getAttribute('value');
  }

  async setEsiNumberInput(esiNumber) {
    await this.esiNumberInput.sendKeys(esiNumber);
  }

  async getEsiNumberInput() {
    return this.esiNumberInput.getAttribute('value');
  }

  async setEsiRegistrationDateInput(esiRegistrationDate) {
    await this.esiRegistrationDateInput.sendKeys(esiRegistrationDate);
  }

  async getEsiRegistrationDateInput() {
    return this.esiRegistrationDateInput.getAttribute('value');
  }

  async setEsiSignatoryInput(esiSignatory) {
    await this.esiSignatoryInput.sendKeys(esiSignatory);
  }

  async getEsiSignatoryInput() {
    return this.esiSignatoryInput.getAttribute('value');
  }

  async setPtNumberInput(ptNumber) {
    await this.ptNumberInput.sendKeys(ptNumber);
  }

  async getPtNumberInput() {
    return this.ptNumberInput.getAttribute('value');
  }

  async setPtRegistrationDateInput(ptRegistrationDate) {
    await this.ptRegistrationDateInput.sendKeys(ptRegistrationDate);
  }

  async getPtRegistrationDateInput() {
    return this.ptRegistrationDateInput.getAttribute('value');
  }

  async setPtSignatoryInput(ptSignatory) {
    await this.ptSignatoryInput.sendKeys(ptSignatory);
  }

  async getPtSignatoryInput() {
    return this.ptSignatoryInput.getAttribute('value');
  }

  async branchSelectLastOption() {
    await this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async branchSelectOption(option) {
    await this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  async getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
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

  async stateSelectLastOption() {
    await this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async stateSelectOption(option) {
    await this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  async getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
  }

  async citySelectLastOption() {
    await this.citySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async citySelectOption(option) {
    await this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  async getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
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
