import { element, by, ElementFinder } from 'protractor';

export default class PeriodsUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.periods.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  periodsSelect: ElementFinder = element(by.css('select#periods-periods'));
  sectionSelect: ElementFinder = element(by.css('select#periods-section'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setPeriodsSelect(periods) {
    await this.periodsSelect.sendKeys(periods);
  }

  async getPeriodsSelect() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
  }

  async periodsSelectLastOption() {
    await this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async sectionSelectLastOption() {
    await this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sectionSelectOption(option) {
    await this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  async getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
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
