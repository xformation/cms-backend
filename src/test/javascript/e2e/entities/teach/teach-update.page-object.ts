import { element, by, ElementFinder } from 'protractor';

export default class TeachUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.teach.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#teach-desc'));
  subjectSelect: ElementFinder = element(by.css('select#teach-subject'));
  teacherSelect: ElementFinder = element(by.css('select#teach-teacher'));

  getPageTitle() {
    return this.pageTitle;
  }

  setDescInput(desc) {
    this.descInput.sendKeys(desc);
  }

  getDescInput() {
    return this.descInput.getAttribute('value');
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

  teacherSelectLastOption() {
    this.teacherSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  teacherSelectOption(option) {
    this.teacherSelect.sendKeys(option);
  }

  getTeacherSelect() {
    return this.teacherSelect;
  }

  getTeacherSelectedOption() {
    return this.teacherSelect.element(by.css('option:checked')).getText();
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
