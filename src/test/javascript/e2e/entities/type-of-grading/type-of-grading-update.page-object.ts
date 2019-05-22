import { element, by, ElementFinder } from 'protractor';

export default class TypeOfGradingUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.typeOfGrading.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  minMarksInput: ElementFinder = element(by.css('input#type-of-grading-minMarks'));
  maxMarksInput: ElementFinder = element(by.css('input#type-of-grading-maxMarks'));
  gradesSelect: ElementFinder = element(by.css('select#type-of-grading-grades'));
  academicExamSettingSelect: ElementFinder = element(by.css('select#type-of-grading-academicExamSetting'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setMinMarksInput(minMarks) {
    await this.minMarksInput.sendKeys(minMarks);
  }

  async getMinMarksInput() {
    return this.minMarksInput.getAttribute('value');
  }

  async setMaxMarksInput(maxMarks) {
    await this.maxMarksInput.sendKeys(maxMarks);
  }

  async getMaxMarksInput() {
    return this.maxMarksInput.getAttribute('value');
  }

  async setGradesSelect(grades) {
    await this.gradesSelect.sendKeys(grades);
  }

  async getGradesSelect() {
    return this.gradesSelect.element(by.css('option:checked')).getText();
  }

  async gradesSelectLastOption() {
    await this.gradesSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async academicExamSettingSelectLastOption() {
    await this.academicExamSettingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicExamSettingSelectOption(option) {
    await this.academicExamSettingSelect.sendKeys(option);
  }

  getAcademicExamSettingSelect() {
    return this.academicExamSettingSelect;
  }

  async getAcademicExamSettingSelectedOption() {
    return this.academicExamSettingSelect.element(by.css('option:checked')).getText();
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
