import { element, by, ElementFinder } from 'protractor';

export default class FacilityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.facility.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameInput: ElementFinder = element(by.css('input#facility-name'));
  statusSelect: ElementFinder = element(by.css('select#facility-status'));
  startDateInput: ElementFinder = element(by.css('input#facility-startDate'));
  endDateInput: ElementFinder = element(by.css('input#facility-endDate'));
  suspandStartDateInput: ElementFinder = element(by.css('input#facility-suspandStartDate'));
  suspandEndDateInput: ElementFinder = element(by.css('input#facility-suspandEndDate'));
  academicYearSelect: ElementFinder = element(by.css('select#facility-academicYear'));
  branchSelect: ElementFinder = element(by.css('select#facility-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
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

  async setSuspandStartDateInput(suspandStartDate) {
    await this.suspandStartDateInput.sendKeys(suspandStartDate);
  }

  async getSuspandStartDateInput() {
    return this.suspandStartDateInput.getAttribute('value');
  }

  async setSuspandEndDateInput(suspandEndDate) {
    await this.suspandEndDateInput.sendKeys(suspandEndDate);
  }

  async getSuspandEndDateInput() {
    return this.suspandEndDateInput.getAttribute('value');
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
