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

  async setSectionSelect(section) {
    await this.sectionSelect.sendKeys(section);
  }

  async getSectionSelect() {
    return this.sectionSelect.element(by.css('option:checked')).getText();
  }

  async sectionSelectLastOption() {
    await this.sectionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async batchSelectLastOption() {
    await this.batchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async batchSelectOption(option) {
    await this.batchSelect.sendKeys(option);
  }

  getBatchSelect() {
    return this.batchSelect;
  }

  async getBatchSelectedOption() {
    return this.batchSelect.element(by.css('option:checked')).getText();
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
