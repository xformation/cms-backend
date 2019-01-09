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
  registrationDateInput: ElementFinder = element(by.css('input#legal-entity-registrationDate'));
  esiNumberInput: ElementFinder = element(by.css('input#legal-entity-esiNumber'));
  ptRegistrationDateInput: ElementFinder = element(by.css('input#legal-entity-ptRegistrationDate'));
  ptSignatoryInput: ElementFinder = element(by.css('input#legal-entity-ptSignatory'));
  ptNumberInput: ElementFinder = element(by.css('input#legal-entity-ptNumber'));

  getPageTitle() {
    return this.pageTitle;
  }

  setLogoInput(logo) {
    this.logoInput.sendKeys(logo);
  }

  getLogoInput() {
    return this.logoInput.getAttribute('value');
  }

  setLegalNameOfTheCollegeInput(legalNameOfTheCollege) {
    this.legalNameOfTheCollegeInput.sendKeys(legalNameOfTheCollege);
  }

  getLegalNameOfTheCollegeInput() {
    return this.legalNameOfTheCollegeInput.getAttribute('value');
  }

  setTypeOfCollegeSelect(typeOfCollege) {
    this.typeOfCollegeSelect.sendKeys(typeOfCollege);
  }

  getTypeOfCollegeSelect() {
    return this.typeOfCollegeSelect.element(by.css('option:checked')).getText();
  }

  typeOfCollegeSelectLastOption() {
    this.typeOfCollegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setDateOfIncorporationInput(dateOfIncorporation) {
    this.dateOfIncorporationInput.sendKeys(dateOfIncorporation);
  }

  getDateOfIncorporationInput() {
    return this.dateOfIncorporationInput.getAttribute('value');
  }

  setRegisteredOfficeAddressInput(registeredOfficeAddress) {
    this.registeredOfficeAddressInput.sendKeys(registeredOfficeAddress);
  }

  getRegisteredOfficeAddressInput() {
    return this.registeredOfficeAddressInput.getAttribute('value');
  }

  setCollegeIdentificationNumberInput(collegeIdentificationNumber) {
    this.collegeIdentificationNumberInput.sendKeys(collegeIdentificationNumber);
  }

  getCollegeIdentificationNumberInput() {
    return this.collegeIdentificationNumberInput.getAttribute('value');
  }

  setPanInput(pan) {
    this.panInput.sendKeys(pan);
  }

  getPanInput() {
    return this.panInput.getAttribute('value');
  }

  setTanInput(tan) {
    this.tanInput.sendKeys(tan);
  }

  getTanInput() {
    return this.tanInput.getAttribute('value');
  }

  setTanCircleNumberInput(tanCircleNumber) {
    this.tanCircleNumberInput.sendKeys(tanCircleNumber);
  }

  getTanCircleNumberInput() {
    return this.tanCircleNumberInput.getAttribute('value');
  }

  setCitTdsLocationInput(citTdsLocation) {
    this.citTdsLocationInput.sendKeys(citTdsLocation);
  }

  getCitTdsLocationInput() {
    return this.citTdsLocationInput.getAttribute('value');
  }

  setFormSignatoryInput(formSignatory) {
    this.formSignatoryInput.sendKeys(formSignatory);
  }

  getFormSignatoryInput() {
    return this.formSignatoryInput.getAttribute('value');
  }

  setPfNumberInput(pfNumber) {
    this.pfNumberInput.sendKeys(pfNumber);
  }

  getPfNumberInput() {
    return this.pfNumberInput.getAttribute('value');
  }

  setRegistrationDateInput(registrationDate) {
    this.registrationDateInput.sendKeys(registrationDate);
  }

  getRegistrationDateInput() {
    return this.registrationDateInput.getAttribute('value');
  }

  setEsiNumberInput(esiNumber) {
    this.esiNumberInput.sendKeys(esiNumber);
  }

  getEsiNumberInput() {
    return this.esiNumberInput.getAttribute('value');
  }

  setPtRegistrationDateInput(ptRegistrationDate) {
    this.ptRegistrationDateInput.sendKeys(ptRegistrationDate);
  }

  getPtRegistrationDateInput() {
    return this.ptRegistrationDateInput.getAttribute('value');
  }

  setPtSignatoryInput(ptSignatory) {
    this.ptSignatoryInput.sendKeys(ptSignatory);
  }

  getPtSignatoryInput() {
    return this.ptSignatoryInput.getAttribute('value');
  }

  setPtNumberInput(ptNumber) {
    this.ptNumberInput.sendKeys(ptNumber);
  }

  getPtNumberInput() {
    return this.ptNumberInput.getAttribute('value');
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
