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

  setFeeRemainderSelect(feeRemainder) {
    this.feeRemainderSelect.sendKeys(feeRemainder);
  }

  getFeeRemainderSelect() {
    return this.feeRemainderSelect.element(by.css('option:checked')).getText();
  }

  feeRemainderSelectLastOption() {
    this.feeRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setNoticeDayInput(noticeDay) {
    this.noticeDayInput.sendKeys(noticeDay);
  }

  getNoticeDayInput() {
    return this.noticeDayInput.getAttribute('value');
  }

  setOverDueRemainderSelect(overDueRemainder) {
    this.overDueRemainderSelect.sendKeys(overDueRemainder);
  }

  getOverDueRemainderSelect() {
    return this.overDueRemainderSelect.element(by.css('option:checked')).getText();
  }

  overDueRemainderSelectLastOption() {
    this.overDueRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setRemainderRecipientsInput(remainderRecipients) {
    this.remainderRecipientsInput.sendKeys(remainderRecipients);
  }

  getRemainderRecipientsInput() {
    return this.remainderRecipientsInput.getAttribute('value');
  }

  dueDateSelectLastOption() {
    this.dueDateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  dueDateSelectOption(option) {
    this.dueDateSelect.sendKeys(option);
  }

  getDueDateSelect() {
    return this.dueDateSelect;
  }

  getDueDateSelectedOption() {
    return this.dueDateSelect.element(by.css('option:checked')).getText();
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
