import { element, by, ElementFinder } from 'protractor';

export default class CourseOfferUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.courseOffer.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#course-offer-desc'));
  collegeSelect: ElementFinder = element(by.css('select#course-offer-college'));
  departmentSelect: ElementFinder = element(by.css('select#course-offer-department'));
  subjectSelect: ElementFinder = element(by.css('select#course-offer-subject'));

  getPageTitle() {
    return this.pageTitle;
  }

  setDescInput(desc) {
    this.descInput.sendKeys(desc);
  }

  getDescInput() {
    return this.descInput.getAttribute('value');
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

  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  subjectSelectLastOption() {
    this.subjectSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  subjectSelectOption(option) {
    this.subjectSelect.sendKeys(option);
  }

  getSubjectSelect() {
    return this.subjectSelect;
  }

  getSubjectSelectedOption() {
    return this.subjectSelect.element(by.css('option:checked')).getText();
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
