import { element, by, ElementFinder } from 'protractor';

export default class TermUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.term.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  srNoInput: ElementFinder = element(by.css('input#term-srNo'));
  aTermsInput: ElementFinder = element(by.css('input#term-aTerms'));
  startDateInput: ElementFinder = element(by.css('input#term-startDate'));
  endDateInput: ElementFinder = element(by.css('input#term-endDate'));
  statusSelect: ElementFinder = element(by.css('select#term-status'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setSrNoInput(srNo) {
    await this.srNoInput.sendKeys(srNo);
  }

  async getSrNoInput() {
    return this.srNoInput.getAttribute('value');
  }

  async setATermsInput(aTerms) {
    await this.aTermsInput.sendKeys(aTerms);
  }

  async getATermsInput() {
    return this.aTermsInput.getAttribute('value');
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
