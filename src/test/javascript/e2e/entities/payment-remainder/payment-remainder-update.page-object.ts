import { element, by, ElementFinder } from 'protractor';

export default class PaymentRemainderUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.paymentRemainder.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  feeRemainderSelect: ElementFinder = element(by.css('select#payment-remainder-feeRemainder'));
  noticeDayInput: ElementFinder = element(by.css('input#payment-remainder-noticeDay'));
  overDueRemainderSelect: ElementFinder = element(by.css('select#payment-remainder-overDueRemainder'));
  remainderRecipientsInput: ElementFinder = element(by.css('input#payment-remainder-remainderRecipients'));
  dueDateSelect: ElementFinder = element(by.css('select#payment-remainder-dueDate'));
  collegeSelect: ElementFinder = element(by.css('select#payment-remainder-college'));
  branchSelect: ElementFinder = element(by.css('select#payment-remainder-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setFeeRemainderSelect(feeRemainder) {
    await this.feeRemainderSelect.sendKeys(feeRemainder);
  }

  async getFeeRemainderSelect() {
    return this.feeRemainderSelect.element(by.css('option:checked')).getText();
  }

  async feeRemainderSelectLastOption() {
    await this.feeRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNoticeDayInput(noticeDay) {
    await this.noticeDayInput.sendKeys(noticeDay);
  }

  async getNoticeDayInput() {
    return this.noticeDayInput.getAttribute('value');
  }

  async setOverDueRemainderSelect(overDueRemainder) {
    await this.overDueRemainderSelect.sendKeys(overDueRemainder);
  }

  async getOverDueRemainderSelect() {
    return this.overDueRemainderSelect.element(by.css('option:checked')).getText();
  }

  async overDueRemainderSelectLastOption() {
    await this.overDueRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setRemainderRecipientsInput(remainderRecipients) {
    await this.remainderRecipientsInput.sendKeys(remainderRecipients);
  }

  async getRemainderRecipientsInput() {
    return this.remainderRecipientsInput.getAttribute('value');
  }

  async dueDateSelectLastOption() {
    await this.dueDateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async dueDateSelectOption(option) {
    await this.dueDateSelect.sendKeys(option);
  }

  getDueDateSelect() {
    return this.dueDateSelect;
  }

  async getDueDateSelectedOption() {
    return this.dueDateSelect.element(by.css('option:checked')).getText();
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
