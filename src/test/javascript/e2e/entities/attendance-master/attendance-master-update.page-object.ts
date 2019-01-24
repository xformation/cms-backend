import { element, by, ElementFinder } from 'protractor';

export default class AttendanceMasterUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.attendanceMaster.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descInput: ElementFinder = element(by.css('input#attendance-master-desc'));
  teachSelect: ElementFinder = element(by.css('select#attendance-master-teach'));
  batchSelect: ElementFinder = element(by.css('select#attendance-master-batch'));
  sectionSelect: ElementFinder = element(by.css('select#attendance-master-section'));

  getPageTitle() {
    return this.pageTitle;
  }

  setDescInput(desc) {
    this.descInput.sendKeys(desc);
  }

  getDescInput() {
    return this.descInput.getAttribute('value');
  }

  teachSelectLastOption() {
    this.teachSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  teachSelectOption(option) {
    this.teachSelect.sendKeys(option);
  }

  getTeachSelect() {
    return this.teachSelect;
  }

  getTeachSelectedOption() {
    return this.teachSelect.element(by.css('option:checked')).getText();
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
