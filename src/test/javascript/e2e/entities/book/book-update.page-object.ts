import { element, by, ElementFinder } from 'protractor';

export default class BookUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.book.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  issueDateInput: ElementFinder = element(by.css('input#book-issueDate'));
  dueDateInput: ElementFinder = element(by.css('input#book-dueDate'));
  noOfCopiesAvailableInput: ElementFinder = element(by.css('input#book-noOfCopiesAvailable'));
  statusSelect: ElementFinder = element(by.css('select#book-status'));
  receivedDateInput: ElementFinder = element(by.css('input#book-receivedDate'));
  studentSelect: ElementFinder = element(by.css('select#book-student'));
  librarySelect: ElementFinder = element(by.css('select#book-library'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIssueDateInput(issueDate) {
    await this.issueDateInput.sendKeys(issueDate);
  }

  async getIssueDateInput() {
    return this.issueDateInput.getAttribute('value');
  }

  async setDueDateInput(dueDate) {
    await this.dueDateInput.sendKeys(dueDate);
  }

  async getDueDateInput() {
    return this.dueDateInput.getAttribute('value');
  }

  async setNoOfCopiesAvailableInput(noOfCopiesAvailable) {
    await this.noOfCopiesAvailableInput.sendKeys(noOfCopiesAvailable);
  }

  async getNoOfCopiesAvailableInput() {
    return this.noOfCopiesAvailableInput.getAttribute('value');
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
  async setReceivedDateInput(receivedDate) {
    await this.receivedDateInput.sendKeys(receivedDate);
  }

  async getReceivedDateInput() {
    return this.receivedDateInput.getAttribute('value');
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

  async librarySelectLastOption() {
    await this.librarySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async librarySelectOption(option) {
    await this.librarySelect.sendKeys(option);
  }

  getLibrarySelect() {
    return this.librarySelect;
  }

  async getLibrarySelectedOption() {
    return this.librarySelect.element(by.css('option:checked')).getText();
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
