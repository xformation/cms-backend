import { element, by, ElementFinder } from 'protractor';

export default class InvoiceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.invoice.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  invoiceNumberInput: ElementFinder = element(by.css('input#invoice-invoiceNumber'));
  amountPaidInput: ElementFinder = element(by.css('input#invoice-amountPaid'));
  paymentDateInput: ElementFinder = element(by.css('input#invoice-paymentDate'));
  nextPaymentDateInput: ElementFinder = element(by.css('input#invoice-nextPaymentDate'));
  outStandingAmountInput: ElementFinder = element(by.css('input#invoice-outStandingAmount'));
  modeOfPaymentSelect: ElementFinder = element(by.css('select#invoice-modeOfPayment'));
  chequeNumberInput: ElementFinder = element(by.css('input#invoice-chequeNumber'));
  demandDraftNumberInput: ElementFinder = element(by.css('input#invoice-demandDraftNumber'));
  onlineTxnRefNumberInput: ElementFinder = element(by.css('input#invoice-onlineTxnRefNumber'));
  paymentStatusSelect: ElementFinder = element(by.css('select#invoice-paymentStatus'));
  commentsInput: ElementFinder = element(by.css('input#invoice-comments'));
  updatedByInput: ElementFinder = element(by.css('input#invoice-updatedBy'));
  updatedOnInput: ElementFinder = element(by.css('input#invoice-updatedOn'));
  feeCategorySelect: ElementFinder = element(by.css('select#invoice-feeCategory'));
  feeDetailsSelect: ElementFinder = element(by.css('select#invoice-feeDetails'));
  dueDateSelect: ElementFinder = element(by.css('select#invoice-dueDate'));
  paymentRemainderSelect: ElementFinder = element(by.css('select#invoice-paymentRemainder'));
  collegeSelect: ElementFinder = element(by.css('select#invoice-college'));
  branchSelect: ElementFinder = element(by.css('select#invoice-branch'));
  studentSelect: ElementFinder = element(by.css('select#invoice-student'));
  academicYearSelect: ElementFinder = element(by.css('select#invoice-academicYear'));

  getPageTitle() {
    return this.pageTitle;
  }

  setInvoiceNumberInput(invoiceNumber) {
    this.invoiceNumberInput.sendKeys(invoiceNumber);
  }

  getInvoiceNumberInput() {
    return this.invoiceNumberInput.getAttribute('value');
  }

  setAmountPaidInput(amountPaid) {
    this.amountPaidInput.sendKeys(amountPaid);
  }

  getAmountPaidInput() {
    return this.amountPaidInput.getAttribute('value');
  }

  setPaymentDateInput(paymentDate) {
    this.paymentDateInput.sendKeys(paymentDate);
  }

  getPaymentDateInput() {
    return this.paymentDateInput.getAttribute('value');
  }

  setNextPaymentDateInput(nextPaymentDate) {
    this.nextPaymentDateInput.sendKeys(nextPaymentDate);
  }

  getNextPaymentDateInput() {
    return this.nextPaymentDateInput.getAttribute('value');
  }

  setOutStandingAmountInput(outStandingAmount) {
    this.outStandingAmountInput.sendKeys(outStandingAmount);
  }

  getOutStandingAmountInput() {
    return this.outStandingAmountInput.getAttribute('value');
  }

  setModeOfPaymentSelect(modeOfPayment) {
    this.modeOfPaymentSelect.sendKeys(modeOfPayment);
  }

  getModeOfPaymentSelect() {
    return this.modeOfPaymentSelect.element(by.css('option:checked')).getText();
  }

  modeOfPaymentSelectLastOption() {
    this.modeOfPaymentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setChequeNumberInput(chequeNumber) {
    this.chequeNumberInput.sendKeys(chequeNumber);
  }

  getChequeNumberInput() {
    return this.chequeNumberInput.getAttribute('value');
  }

  setDemandDraftNumberInput(demandDraftNumber) {
    this.demandDraftNumberInput.sendKeys(demandDraftNumber);
  }

  getDemandDraftNumberInput() {
    return this.demandDraftNumberInput.getAttribute('value');
  }

  setOnlineTxnRefNumberInput(onlineTxnRefNumber) {
    this.onlineTxnRefNumberInput.sendKeys(onlineTxnRefNumber);
  }

  getOnlineTxnRefNumberInput() {
    return this.onlineTxnRefNumberInput.getAttribute('value');
  }

  setPaymentStatusSelect(paymentStatus) {
    this.paymentStatusSelect.sendKeys(paymentStatus);
  }

  getPaymentStatusSelect() {
    return this.paymentStatusSelect.element(by.css('option:checked')).getText();
  }

  paymentStatusSelectLastOption() {
    this.paymentStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCommentsInput(comments) {
    this.commentsInput.sendKeys(comments);
  }

  getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  setUpdatedByInput(updatedBy) {
    this.updatedByInput.sendKeys(updatedBy);
  }

  getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  setUpdatedOnInput(updatedOn) {
    this.updatedOnInput.sendKeys(updatedOn);
  }

  getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  feeCategorySelectLastOption() {
    this.feeCategorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  feeCategorySelectOption(option) {
    this.feeCategorySelect.sendKeys(option);
  }

  getFeeCategorySelect() {
    return this.feeCategorySelect;
  }

  getFeeCategorySelectedOption() {
    return this.feeCategorySelect.element(by.css('option:checked')).getText();
  }

  feeDetailsSelectLastOption() {
    this.feeDetailsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  feeDetailsSelectOption(option) {
    this.feeDetailsSelect.sendKeys(option);
  }

  getFeeDetailsSelect() {
    return this.feeDetailsSelect;
  }

  getFeeDetailsSelectedOption() {
    return this.feeDetailsSelect.element(by.css('option:checked')).getText();
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

  paymentRemainderSelectLastOption() {
    this.paymentRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  paymentRemainderSelectOption(option) {
    this.paymentRemainderSelect.sendKeys(option);
  }

  getPaymentRemainderSelect() {
    return this.paymentRemainderSelect;
  }

  getPaymentRemainderSelectedOption() {
    return this.paymentRemainderSelect.element(by.css('option:checked')).getText();
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

  studentSelectLastOption() {
    this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  studentSelectOption(option) {
    this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
  }

  academicYearSelectLastOption() {
    this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicYearSelectOption(option) {
    this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
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
