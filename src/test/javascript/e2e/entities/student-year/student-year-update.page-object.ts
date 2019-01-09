import { element, by, ElementFinder } from 'protractor';

export default class StudentYearUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentYear.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  yearDescSelect: ElementFinder = element(by.css('select#student-year-yearDesc'));

  getPageTitle() {
    return this.pageTitle;
  }

  setYearDescSelect(yearDesc) {
    this.yearDescSelect.sendKeys(yearDesc);
  }

  getYearDescSelect() {
    return this.yearDescSelect.element(by.css('option:checked')).getText();
  }

  yearDescSelectLastOption() {
    this.yearDescSelect
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
