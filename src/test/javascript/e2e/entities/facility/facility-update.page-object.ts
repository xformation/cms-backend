import { element, by, ElementFinder } from 'protractor';

export default class FacilityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.facility.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  facilityNameInput: ElementFinder = element(by.css('input#facility-facilityName'));
  academicYearSelect: ElementFinder = element(by.css('select#facility-academicYear'));
  branchSelect: ElementFinder = element(by.css('select#facility-branch'));
  studentSelect: ElementFinder = element(by.css('select#facility-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setFacilityNameInput(facilityName) {
    await this.facilityNameInput.sendKeys(facilityName);
  }

  async getFacilityNameInput() {
    return this.facilityNameInput.getAttribute('value');
  }

  async academicYearSelectLastOption() {
    await this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicYearSelectOption(option) {
    await this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  async getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
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

  async studentSelectLastOption() {
    await this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async studentSelectOption(option) {
    await this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  async getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
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
