import { element, by, ElementFinder } from 'protractor';

export default class DepartmentsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.departments.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameInput: ElementFinder = element(by.css('input#departments-name'));
  descriptionInput: ElementFinder = element(by.css('input#departments-description'));
  deptHeadInput: ElementFinder = element(by.css('input#departments-deptHead'));

  getPageTitle() {
    return this.pageTitle;
  }

  setNameInput(name) {
    this.nameInput.sendKeys(name);
  }

  getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  setDescriptionInput(description) {
    this.descriptionInput.sendKeys(description);
  }

  getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  setDeptHeadInput(deptHead) {
    this.deptHeadInput.sendKeys(deptHead);
  }

  getDeptHeadInput() {
    return this.deptHeadInput.getAttribute('value');
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
