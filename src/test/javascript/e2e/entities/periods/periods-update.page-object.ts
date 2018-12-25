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

  setPeriodsSelect(periods) {
    this.periodsSelect.sendKeys(periods);
  }

  getPeriodsSelect() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
  }

  periodsSelectLastOption() {
    this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  sectionSelectLastOption() {
    this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  sectionSelectOption(option) {
    this.sectionSelect.sendKeys(option);
  }

  getSectionSelect() {
    return this.sectionSelect;
  }

  getSectionSelectedOption() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
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
