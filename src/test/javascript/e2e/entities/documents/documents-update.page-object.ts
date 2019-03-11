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

  setDocumentNameInput(documentName) {
    this.documentNameInput.sendKeys(documentName);
  }

  getDocumentNameInput() {
    return this.documentNameInput.getAttribute('value');
  }

  setUploadInput(upload) {
    this.uploadInput.sendKeys(upload);
  }

  getUploadInput() {
    return this.uploadInput.getAttribute('value');
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
