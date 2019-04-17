import { element, by, ElementFinder } from 'protractor';

export default class DocumentsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.documents.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  documentNameInput: ElementFinder = element(by.css('input#documents-documentName'));
  uploadInput: ElementFinder = element(by.css('input#documents-upload'));
  studentSelect: ElementFinder = element(by.css('select#documents-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDocumentNameInput(documentName) {
    await this.documentNameInput.sendKeys(documentName);
  }

  async getDocumentNameInput() {
    return this.documentNameInput.getAttribute('value');
  }

  async setUploadInput(upload) {
    await this.uploadInput.sendKeys(upload);
  }

  async getUploadInput() {
    return this.uploadInput.getAttribute('value');
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
