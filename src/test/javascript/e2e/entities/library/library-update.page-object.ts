import { element, by, ElementFinder } from 'protractor';

export default class LibraryUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.library.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  bookTitleInput: ElementFinder = element(by.css('input#library-bookTitle'));
  authorInput: ElementFinder = element(by.css('input#library-author'));
  noOfCopiesInput: ElementFinder = element(by.css('input#library-noOfCopies'));
  bookNoInput: ElementFinder = element(by.css('input#library-bookNo'));
  additionalInfoInput: ElementFinder = element(by.css('input#library-additionalInfo'));
  uniqueNoInput: ElementFinder = element(by.css('input#library-uniqueNo'));
  batchSelect: ElementFinder = element(by.css('select#library-batch'));
  subjectSelect: ElementFinder = element(by.css('select#library-subject'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setBookTitleInput(bookTitle) {
    await this.bookTitleInput.sendKeys(bookTitle);
  }

  async getBookTitleInput() {
    return this.bookTitleInput.getAttribute('value');
  }

  async setAuthorInput(author) {
    await this.authorInput.sendKeys(author);
  }

  async getAuthorInput() {
    return this.authorInput.getAttribute('value');
  }

  async setNoOfCopiesInput(noOfCopies) {
    await this.noOfCopiesInput.sendKeys(noOfCopies);
  }

  async getNoOfCopiesInput() {
    return this.noOfCopiesInput.getAttribute('value');
  }

  async setBookNoInput(bookNo) {
    await this.bookNoInput.sendKeys(bookNo);
  }

  async getBookNoInput() {
    return this.bookNoInput.getAttribute('value');
  }

  async setAdditionalInfoInput(additionalInfo) {
    await this.additionalInfoInput.sendKeys(additionalInfo);
  }

  async getAdditionalInfoInput() {
    return this.additionalInfoInput.getAttribute('value');
  }

  async setUniqueNoInput(uniqueNo) {
    await this.uniqueNoInput.sendKeys(uniqueNo);
  }

  async getUniqueNoInput() {
    return this.uniqueNoInput.getAttribute('value');
  }

  async batchSelectLastOption() {
    await this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async batchSelectOption(option) {
    await this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  async getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
  }

  async subjectSelectLastOption() {
    await this.subjectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async subjectSelectOption(option) {
    await this.subjectSelect.sendKeys(option);
  }

  getSubjectSelect() {
    return this.subjectSelect;
  }

  async getSubjectSelectedOption() {
    return this.subjectSelect.element(by.css('option:checked')).getText();
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
