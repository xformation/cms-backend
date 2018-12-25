import { element, by, ElementFinder } from 'protractor';

export default class TeacherUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.teacher.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  tNameInput: ElementFinder = element(by.css('input#teacher-tName'));
  periodsSelect: ElementFinder = element(by.css('select#teacher-periods'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTNameInput(tName) {
    this.tNameInput.sendKeys(tName);
  }

  getTNameInput() {
    return this.tNameInput.getAttribute('value');
  }

  periodsSelectLastOption() {
    this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  periodsSelectOption(option) {
    this.periodsSelect.sendKeys(option);
  }

  getPeriodsSelect() {
    return this.periodsSelect;
  }

  getPeriodsSelectedOption() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
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
