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

  async setTNameInput(tName) {
    await this.tNameInput.sendKeys(tName);
  }

  async getTNameInput() {
    return this.tNameInput.getAttribute('value');
  }

  async periodsSelectLastOption() {
    await this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async periodsSelectOption(option) {
    await this.periodsSelect.sendKeys(option);
  }

  getPeriodsSelect() {
    return this.periodsSelect;
  }

  async getPeriodsSelectedOption() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
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
