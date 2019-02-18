import { element, by, ElementFinder } from 'protractor';

export default class AdminOverviewUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.adminOverview.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  chooseDateInput: ElementFinder = element(by.css('input#admin-overview-chooseDate'));
  sectionSelect: ElementFinder = element(by.css('select#admin-overview-section'));
  lectureOneSelect: ElementFinder = element(by.css('select#admin-overview-lectureOne'));
  lectureTwoSelect: ElementFinder = element(by.css('select#admin-overview-lectureTwo'));
  lectureThreeSelect: ElementFinder = element(by.css('select#admin-overview-lectureThree'));
  lectureFourSelect: ElementFinder = element(by.css('select#admin-overview-lectureFour'));
  lectureFiveSelect: ElementFinder = element(by.css('select#admin-overview-lectureFive'));
  lectureSixSelect: ElementFinder = element(by.css('select#admin-overview-lectureSix'));
  lectureSevenSelect: ElementFinder = element(by.css('select#admin-overview-lectureSeven'));
  lectureEightSelect: ElementFinder = element(by.css('select#admin-overview-lectureEight'));
  departmentSelect: ElementFinder = element(by.css('select#admin-overview-department'));
  batchSelect: ElementFinder = element(by.css('select#admin-overview-batch'));

  getPageTitle() {
    return this.pageTitle;
  }

  setChooseDateInput(chooseDate) {
    this.chooseDateInput.sendKeys(chooseDate);
  }

  getChooseDateInput() {
    return this.chooseDateInput.getAttribute('value');
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
  setLectureOneSelect(lectureOne) {
    this.lectureOneSelect.sendKeys(lectureOne);
  }

  getLectureOneSelect() {
    return this.lectureOneSelect.element(by.css('option:checked')).getText();
  }

  lectureOneSelectLastOption() {
    this.lectureOneSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureTwoSelect(lectureTwo) {
    this.lectureTwoSelect.sendKeys(lectureTwo);
  }

  getLectureTwoSelect() {
    return this.lectureTwoSelect.element(by.css('option:checked')).getText();
  }

  lectureTwoSelectLastOption() {
    this.lectureTwoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureThreeSelect(lectureThree) {
    this.lectureThreeSelect.sendKeys(lectureThree);
  }

  getLectureThreeSelect() {
    return this.lectureThreeSelect.element(by.css('option:checked')).getText();
  }

  lectureThreeSelectLastOption() {
    this.lectureThreeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureFourSelect(lectureFour) {
    this.lectureFourSelect.sendKeys(lectureFour);
  }

  getLectureFourSelect() {
    return this.lectureFourSelect.element(by.css('option:checked')).getText();
  }

  lectureFourSelectLastOption() {
    this.lectureFourSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureFiveSelect(lectureFive) {
    this.lectureFiveSelect.sendKeys(lectureFive);
  }

  getLectureFiveSelect() {
    return this.lectureFiveSelect.element(by.css('option:checked')).getText();
  }

  lectureFiveSelectLastOption() {
    this.lectureFiveSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureSixSelect(lectureSix) {
    this.lectureSixSelect.sendKeys(lectureSix);
  }

  getLectureSixSelect() {
    return this.lectureSixSelect.element(by.css('option:checked')).getText();
  }

  lectureSixSelectLastOption() {
    this.lectureSixSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureSevenSelect(lectureSeven) {
    this.lectureSevenSelect.sendKeys(lectureSeven);
  }

  getLectureSevenSelect() {
    return this.lectureSevenSelect.element(by.css('option:checked')).getText();
  }

  lectureSevenSelectLastOption() {
    this.lectureSevenSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLectureEightSelect(lectureEight) {
    this.lectureEightSelect.sendKeys(lectureEight);
  }

  getLectureEightSelect() {
    return this.lectureEightSelect.element(by.css('option:checked')).getText();
  }

  lectureEightSelectLastOption() {
    this.lectureEightSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  departmentSelectLastOption() {
    this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  departmentSelectOption(option) {
    this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
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
