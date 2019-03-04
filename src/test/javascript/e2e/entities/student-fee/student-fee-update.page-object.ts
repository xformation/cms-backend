import { element, by, ElementFinder } from 'protractor';

export default class StudentFeeUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentFee.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  totalFeeInput: ElementFinder = element(by.css('input#student-fee-totalFee'));
  feesPaidInput: ElementFinder = element(by.css('input#student-fee-feesPaid'));
  feesDueInput: ElementFinder = element(by.css('input#student-fee-feesDue'));
  dueDateInput: ElementFinder = element(by.css('input#student-fee-dueDate'));
  totalRemainingInput: ElementFinder = element(by.css('input#student-fee-totalRemaining'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTotalFeeInput(totalFee) {
    await this.totalFeeInput.sendKeys(totalFee);
  }

  async getTotalFeeInput() {
    return this.totalFeeInput.getAttribute('value');
  }

  async setFeesPaidInput(feesPaid) {
    await this.feesPaidInput.sendKeys(feesPaid);
  }

  async getFeesPaidInput() {
    return this.feesPaidInput.getAttribute('value');
  }

  async setFeesDueInput(feesDue) {
    await this.feesDueInput.sendKeys(feesDue);
  }

  async getFeesDueInput() {
    return this.feesDueInput.getAttribute('value');
  }

  async setDueDateInput(dueDate) {
    await this.dueDateInput.sendKeys(dueDate);
  }

  async getDueDateInput() {
    return this.dueDateInput.getAttribute('value');
  }

  async setTotalRemainingInput(totalRemaining) {
    await this.totalRemainingInput.sendKeys(totalRemaining);
  }

  async getTotalRemainingInput() {
    return this.totalRemainingInput.getAttribute('value');
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
