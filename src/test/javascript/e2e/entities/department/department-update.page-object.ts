import { element, by, ElementFinder } from 'protractor';

export default class DepartmentUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.department.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameInput: ElementFinder = element(by.css('input#department-name'));
  descriptionInput: ElementFinder = element(by.css('input#department-description'));
  deptHeadInput: ElementFinder = element(by.css('input#department-deptHead'));
  collegeSelect: ElementFinder = element(by.css('select#department-college'));
  academicyearSelect: ElementFinder = element(by.css('select#department-academicyear'));

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

  academicyearSelectLastOption() {
    this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicyearSelectOption(option) {
    this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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
