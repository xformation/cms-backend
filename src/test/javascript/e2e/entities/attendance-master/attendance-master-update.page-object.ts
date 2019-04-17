import { element, by, ElementFinder } from 'protractor';

export default class AttendanceMasterUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.attendanceMaster.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#attendance-master-desc'));
  batchSelect: ElementFinder = element(by.css('select#attendance-master-batch'));
  sectionSelect: ElementFinder = element(by.css('select#attendance-master-section'));
  teachSelect: ElementFinder = element(by.css('select#attendance-master-teach'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescInput(desc) {
    await this.descInput.sendKeys(desc);
  }

  async getDescInput() {
    return this.descInput.getAttribute('value');
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

  async teachSelectLastOption() {
    await this.teachSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async teachSelectOption(option) {
    await this.teachSelect.sendKeys(option);
  }

  getTeachSelect() {
    return this.teachSelect;
  }

  async getTeachSelectedOption() {
    return this.teachSelect.element(by.css('option:checked')).getText();
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
