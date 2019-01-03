import { element, by, ElementFinder } from 'protractor';

export default class StudentYearUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentYear.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  sYearSelect: ElementFinder = element(by.css('select#student-year-sYear'));

  getPageTitle() {
    return this.pageTitle;
  }

  setSYearSelect(sYear) {
    this.sYearSelect.sendKeys(sYear);
  }

  getSYearSelect() {
    return this.sYearSelect.element(by.css('option:checked')).getText();
  }

  sYearSelectLastOption() {
    this.sYearSelect
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
