import { element, by, ElementFinder } from 'protractor';

export default class LegalEntityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.legalEntity.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  logoInput: ElementFinder = element(by.css('input#legal-entity-logo'));
  legalNameOfTheCollegeInput: ElementFinder = element(by.css('input#legal-entity-legalNameOfTheCollege'));
  typeOfCollegeSelect: ElementFinder = element(by.css('select#legal-entity-typeOfCollege'));
  dateOfIncorporationInput: ElementFinder = element(by.css('input#legal-entity-dateOfIncorporation'));
  registeredOfficeAddress1Input: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress1'));
  registeredOfficeAddress2Input: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress2'));
  registeredOfficeAddress3Input: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress3'));
  registeredOfficeAddress4Input: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress4'));
  registeredOfficeAddress5Input: ElementFinder = element(by.css('input#legal-entity-registeredOfficeAddress5'));
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

  setRegisteredOfficeAddress1Input(registeredOfficeAddress1) {
    this.registeredOfficeAddress1Input.sendKeys(registeredOfficeAddress1);
  }

  getRegisteredOfficeAddress1Input() {
    return this.registeredOfficeAddress1Input.getAttribute('value');
  }

  setRegisteredOfficeAddress2Input(registeredOfficeAddress2) {
    this.registeredOfficeAddress2Input.sendKeys(registeredOfficeAddress2);
  }

  getRegisteredOfficeAddress2Input() {
    return this.registeredOfficeAddress2Input.getAttribute('value');
  }

  setRegisteredOfficeAddress3Input(registeredOfficeAddress3) {
    this.registeredOfficeAddress3Input.sendKeys(registeredOfficeAddress3);
  }

  getRegisteredOfficeAddress3Input() {
    return this.registeredOfficeAddress3Input.getAttribute('value');
  }

  setRegisteredOfficeAddress4Input(registeredOfficeAddress4) {
    this.registeredOfficeAddress4Input.sendKeys(registeredOfficeAddress4);
  }

  getRegisteredOfficeAddress4Input() {
    return this.registeredOfficeAddress4Input.getAttribute('value');
  }

  setRegisteredOfficeAddress5Input(registeredOfficeAddress5) {
    this.registeredOfficeAddress5Input.sendKeys(registeredOfficeAddress5);
  }

  getRegisteredOfficeAddress5Input() {
    return this.registeredOfficeAddress5Input.getAttribute('value');
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

  setPfRegistrationDateInput(pfRegistrationDate) {
    this.pfRegistrationDateInput.sendKeys(pfRegistrationDate);
  }

  getPfRegistrationDateInput() {
    return this.pfRegistrationDateInput.getAttribute('value');
  }

  setPfSignatoryInput(pfSignatory) {
    this.pfSignatoryInput.sendKeys(pfSignatory);
  }

  getPfSignatoryInput() {
    return this.pfSignatoryInput.getAttribute('value');
  }

  setEsiNumberInput(esiNumber) {
    this.esiNumberInput.sendKeys(esiNumber);
  }

  getEsiNumberInput() {
    return this.esiNumberInput.getAttribute('value');
  }

  setEsiRegistrationDateInput(esiRegistrationDate) {
    this.esiRegistrationDateInput.sendKeys(esiRegistrationDate);
  }

  getEsiRegistrationDateInput() {
    return this.esiRegistrationDateInput.getAttribute('value');
  }

  setEsiSignatoryInput(esiSignatory) {
    this.esiSignatoryInput.sendKeys(esiSignatory);
  }

  getEsiSignatoryInput() {
    return this.esiSignatoryInput.getAttribute('value');
  }

  setPtNumberInput(ptNumber) {
    this.ptNumberInput.sendKeys(ptNumber);
  }

  getPtNumberInput() {
    return this.ptNumberInput.getAttribute('value');
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

  stateSelectLastOption() {
    this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  stateSelectOption(option) {
    this.stateSelect.sendKeys(option);
  }

  getStateSelect() {
    return this.stateSelect;
  }

  getStateSelectedOption() {
    return this.stateSelect.element(by.css('option:checked')).getText();
  }

  citySelectLastOption() {
    this.citySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  citySelectOption(option) {
    this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
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
