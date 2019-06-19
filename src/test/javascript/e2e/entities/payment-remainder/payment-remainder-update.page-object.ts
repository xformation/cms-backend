import { element, by, ElementFinder } from 'protractor';

export default class PaymentRemainderUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.paymentRemainder.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  isAutoRemainderInput: ElementFinder = element(by.css('input#payment-remainder-isAutoRemainder'));
  isFirstPaymentRemainderInput: ElementFinder = element(by.css('input#payment-remainder-isFirstPaymentRemainder'));
  firstPaymentRemainderDaysInput: ElementFinder = element(by.css('input#payment-remainder-firstPaymentRemainderDays'));
  isSecondPaymentRemainderInput: ElementFinder = element(by.css('input#payment-remainder-isSecondPaymentRemainder'));
  secondPaymentRemainderDaysInput: ElementFinder = element(by.css('input#payment-remainder-secondPaymentRemainderDays'));
  isOverDuePaymentRemainderInput: ElementFinder = element(by.css('input#payment-remainder-isOverDuePaymentRemainder'));
  overDuePaymentRemainderAfterDueDateOrUntilPaidInput: ElementFinder = element(
    by.css('input#payment-remainder-overDuePaymentRemainderAfterDueDateOrUntilPaid')
  );
  overDuePaymentRemainderDaysInput: ElementFinder = element(by.css('input#payment-remainder-overDuePaymentRemainderDays'));
  isRemainderRecipientsInput: ElementFinder = element(by.css('input#payment-remainder-isRemainderRecipients'));
  remainderRecipientsInput: ElementFinder = element(by.css('input#payment-remainder-remainderRecipients'));
  collegeSelect: ElementFinder = element(by.css('select#payment-remainder-college'));
  branchSelect: ElementFinder = element(by.css('select#payment-remainder-branch'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIsAutoRemainderInput(isAutoRemainder) {
    await this.isAutoRemainderInput.sendKeys(isAutoRemainder);
  }

  async getIsAutoRemainderInput() {
    return this.isAutoRemainderInput.getAttribute('value');
  }

  async setIsFirstPaymentRemainderInput(isFirstPaymentRemainder) {
    await this.isFirstPaymentRemainderInput.sendKeys(isFirstPaymentRemainder);
  }

  async getIsFirstPaymentRemainderInput() {
    return this.isFirstPaymentRemainderInput.getAttribute('value');
  }

  async setFirstPaymentRemainderDaysInput(firstPaymentRemainderDays) {
    await this.firstPaymentRemainderDaysInput.sendKeys(firstPaymentRemainderDays);
  }

  async getFirstPaymentRemainderDaysInput() {
    return this.firstPaymentRemainderDaysInput.getAttribute('value');
  }

  async setIsSecondPaymentRemainderInput(isSecondPaymentRemainder) {
    await this.isSecondPaymentRemainderInput.sendKeys(isSecondPaymentRemainder);
  }

  async getIsSecondPaymentRemainderInput() {
    return this.isSecondPaymentRemainderInput.getAttribute('value');
  }

  async setSecondPaymentRemainderDaysInput(secondPaymentRemainderDays) {
    await this.secondPaymentRemainderDaysInput.sendKeys(secondPaymentRemainderDays);
  }

  async getSecondPaymentRemainderDaysInput() {
    return this.secondPaymentRemainderDaysInput.getAttribute('value');
  }

  async setIsOverDuePaymentRemainderInput(isOverDuePaymentRemainder) {
    await this.isOverDuePaymentRemainderInput.sendKeys(isOverDuePaymentRemainder);
  }

  async getIsOverDuePaymentRemainderInput() {
    return this.isOverDuePaymentRemainderInput.getAttribute('value');
  }

  async setOverDuePaymentRemainderAfterDueDateOrUntilPaidInput(overDuePaymentRemainderAfterDueDateOrUntilPaid) {
    await this.overDuePaymentRemainderAfterDueDateOrUntilPaidInput.sendKeys(overDuePaymentRemainderAfterDueDateOrUntilPaid);
  }

  async getOverDuePaymentRemainderAfterDueDateOrUntilPaidInput() {
    return this.overDuePaymentRemainderAfterDueDateOrUntilPaidInput.getAttribute('value');
  }

  async setOverDuePaymentRemainderDaysInput(overDuePaymentRemainderDays) {
    await this.overDuePaymentRemainderDaysInput.sendKeys(overDuePaymentRemainderDays);
  }

  async getOverDuePaymentRemainderDaysInput() {
    return this.overDuePaymentRemainderDaysInput.getAttribute('value');
  }

  async setIsRemainderRecipientsInput(isRemainderRecipients) {
    await this.isRemainderRecipientsInput.sendKeys(isRemainderRecipients);
  }

  async getIsRemainderRecipientsInput() {
    return this.isRemainderRecipientsInput.getAttribute('value');
  }

  async setRemainderRecipientsInput(remainderRecipients) {
    await this.remainderRecipientsInput.sendKeys(remainderRecipients);
  }

  async getRemainderRecipientsInput() {
    return this.remainderRecipientsInput.getAttribute('value');
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
