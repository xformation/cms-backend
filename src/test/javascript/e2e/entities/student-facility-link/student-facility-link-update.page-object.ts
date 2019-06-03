import { element, by, ElementFinder } from 'protractor';

export default class StudentFacilityLinkUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.studentFacilityLink.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  linkDescInput: ElementFinder = element(by.css('input#student-facility-link-linkDesc'));
  studentSelect: ElementFinder = element(by.css('select#student-facility-link-student'));
  facilitySelect: ElementFinder = element(by.css('select#student-facility-link-facility'));

  getPageTitle() {
    return this.pageTitle;
  }

  setLinkDescInput(linkDesc) {
    this.linkDescInput.sendKeys(linkDesc);
  }

  getLinkDescInput() {
    return this.linkDescInput.getAttribute('value');
  }

  studentSelectLastOption() {
    this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  studentSelectOption(option) {
    this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
  }

  facilitySelectLastOption() {
    this.facilitySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  facilitySelectOption(option) {
    this.facilitySelect.sendKeys(option);
  }

  getFacilitySelect() {
    return this.facilitySelect;
  }

  getFacilitySelectedOption() {
    return this.facilitySelect.element(by.css('option:checked')).getText();
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
