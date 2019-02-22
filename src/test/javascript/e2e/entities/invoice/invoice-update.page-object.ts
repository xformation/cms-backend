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

  async setInvoiceNumberInput(invoiceNumber) {
    await this.invoiceNumberInput.sendKeys(invoiceNumber);
  }

  async getInvoiceNumberInput() {
    return this.invoiceNumberInput.getAttribute('value');
  }

  async setAmountPaidInput(amountPaid) {
    await this.amountPaidInput.sendKeys(amountPaid);
  }

  async getAmountPaidInput() {
    return this.amountPaidInput.getAttribute('value');
  }

  async setPaymentDateInput(paymentDate) {
    await this.paymentDateInput.sendKeys(paymentDate);
  }

  async getPaymentDateInput() {
    return this.paymentDateInput.getAttribute('value');
  }

  async setNextPaymentDateInput(nextPaymentDate) {
    await this.nextPaymentDateInput.sendKeys(nextPaymentDate);
  }

  async getNextPaymentDateInput() {
    return this.nextPaymentDateInput.getAttribute('value');
  }

  async setOutStandingAmountInput(outStandingAmount) {
    await this.outStandingAmountInput.sendKeys(outStandingAmount);
  }

  async getOutStandingAmountInput() {
    return this.outStandingAmountInput.getAttribute('value');
  }

  async setModeOfPaymentSelect(modeOfPayment) {
    await this.modeOfPaymentSelect.sendKeys(modeOfPayment);
  }

  async getModeOfPaymentSelect() {
    return this.modeOfPaymentSelect.element(by.css('option:checked')).getText();
  }

  async modeOfPaymentSelectLastOption() {
    await this.modeOfPaymentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setChequeNumberInput(chequeNumber) {
    await this.chequeNumberInput.sendKeys(chequeNumber);
  }

  async getChequeNumberInput() {
    return this.chequeNumberInput.getAttribute('value');
  }

  async setDemandDraftNumberInput(demandDraftNumber) {
    await this.demandDraftNumberInput.sendKeys(demandDraftNumber);
  }

  async getDemandDraftNumberInput() {
    return this.demandDraftNumberInput.getAttribute('value');
  }

  async setOnlineTxnRefNumberInput(onlineTxnRefNumber) {
    await this.onlineTxnRefNumberInput.sendKeys(onlineTxnRefNumber);
  }

  async getOnlineTxnRefNumberInput() {
    return this.onlineTxnRefNumberInput.getAttribute('value');
  }

  async setPaymentStatusSelect(paymentStatus) {
    await this.paymentStatusSelect.sendKeys(paymentStatus);
  }

  async getPaymentStatusSelect() {
    return this.paymentStatusSelect.element(by.css('option:checked')).getText();
  }

  async paymentStatusSelectLastOption() {
    await this.paymentStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCommentsInput(comments) {
    await this.commentsInput.sendKeys(comments);
  }

  async getCommentsInput() {
    return this.commentsInput.getAttribute('value');
  }

  async setUpdatedByInput(updatedBy) {
    await this.updatedByInput.sendKeys(updatedBy);
  }

  async getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  async setUpdatedOnInput(updatedOn) {
    await this.updatedOnInput.sendKeys(updatedOn);
  }

  async getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  async feeCategorySelectLastOption() {
    await this.feeCategorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async feeCategorySelectOption(option) {
    await this.feeCategorySelect.sendKeys(option);
  }

  getFeeCategorySelect() {
    return this.feeCategorySelect;
  }

  async getFeeCategorySelectedOption() {
    return this.feeCategorySelect.element(by.css('option:checked')).getText();
  }

  async feeDetailsSelectLastOption() {
    await this.feeDetailsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async feeDetailsSelectOption(option) {
    await this.feeDetailsSelect.sendKeys(option);
  }

  getFeeDetailsSelect() {
    return this.feeDetailsSelect;
  }

  async getFeeDetailsSelectedOption() {
    return this.feeDetailsSelect.element(by.css('option:checked')).getText();
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

  async paymentRemainderSelectLastOption() {
    await this.paymentRemainderSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async paymentRemainderSelectOption(option) {
    await this.paymentRemainderSelect.sendKeys(option);
  }

  getPaymentRemainderSelect() {
    return this.paymentRemainderSelect;
  }

  async getPaymentRemainderSelectedOption() {
    return this.paymentRemainderSelect.element(by.css('option:checked')).getText();
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
