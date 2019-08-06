import { element, by, ElementFinder } from 'protractor';

export default class InsuranceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.insurance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  insuranceCompanyInput: ElementFinder = element(by.css('input#insurance-insuranceCompany'));
  typeOfInsuranceSelect: ElementFinder = element(by.css('select#insurance-typeOfInsurance'));
  dateOfInsuranceInput: ElementFinder = element(by.css('input#insurance-dateOfInsurance'));
  validTillInput: ElementFinder = element(by.css('input#insurance-validTill'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setInsuranceCompanyInput(insuranceCompany) {
    await this.insuranceCompanyInput.sendKeys(insuranceCompany);
  }

  async getInsuranceCompanyInput() {
    return this.insuranceCompanyInput.getAttribute('value');
  }

  async setTypeOfInsuranceSelect(typeOfInsurance) {
    await this.typeOfInsuranceSelect.sendKeys(typeOfInsurance);
  }

  async getTypeOfInsuranceSelect() {
    return this.typeOfInsuranceSelect.element(by.css('option:checked')).getText();
  }

  async typeOfInsuranceSelectLastOption() {
    await this.typeOfInsuranceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setDateOfInsuranceInput(dateOfInsurance) {
    await this.dateOfInsuranceInput.sendKeys(dateOfInsurance);
  }

  async getDateOfInsuranceInput() {
    return this.dateOfInsuranceInput.getAttribute('value');
  }

  async setValidTillInput(validTill) {
    await this.validTillInput.sendKeys(validTill);
  }

  async getValidTillInput() {
    return this.validTillInput.getAttribute('value');
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
