import { element, by, ElementFinder } from 'protractor';

export default class SemesterUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.semester.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  semSelect: ElementFinder = element(by.css('select#semester-sem'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSemSelect(sem) {
    this.semSelect.sendKeys(sem);
  }

  getSemSelect() {
    return this.semSelect.element(by.css('option:checked')).getText();
  }

  semSelectLastOption() {
    this.semSelect
      .all(by.tagName('option'))
      .last()
      .click();
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
