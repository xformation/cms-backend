import { element, by, ElementFinder } from 'protractor';

export default class SectionUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.section.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  sectionSelect: ElementFinder = element(by.css('select#section-section'));
  batchSelect: ElementFinder = element(by.css('select#section-batch'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSectionSelect(section) {
    this.sectionSelect.sendKeys(section);
  }

  getSectionSelect() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
  }

  sectionSelectLastOption() {
    this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  batchSelectLastOption() {
    this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  batchSelectOption(option) {
    this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
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
