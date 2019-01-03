import { element, by, ElementFinder } from 'protractor';

export default class SubjectUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.subject.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  commonSubSelect: ElementFinder = element(by.css('select#subject-commonSub'));
  electiveSubSelect: ElementFinder = element(by.css('select#subject-electiveSub'));
  periodsSelect: ElementFinder = element(by.css('select#subject-periods'));
  studentSelect: ElementFinder = element(by.css('select#subject-student'));
  teacherSelect: ElementFinder = element(by.css('select#subject-teacher'));

  getPageTitle() {
    return this.pageTitle;
  }

  setCommonSubSelect(commonSub) {
    this.commonSubSelect.sendKeys(commonSub);
  }

  getCommonSubSelect() {
    return this.commonSubSelect.element(by.css('option:checked')).getText();
  }

  commonSubSelectLastOption() {
    this.commonSubSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setElectiveSubSelect(electiveSub) {
    this.electiveSubSelect.sendKeys(electiveSub);
  }

  getElectiveSubSelect() {
    return this.electiveSubSelect.element(by.css('option:checked')).getText();
  }

  electiveSubSelectLastOption() {
    this.electiveSubSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  periodsSelectLastOption() {
    this.periodsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  periodsSelectOption(option) {
    this.periodsSelect.sendKeys(option);
  }

  getPeriodsSelect() {
    return this.periodsSelect;
  }

  getPeriodsSelectedOption() {
    return this.periodsSelect.element(by.css('option:checked')).getText();
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

  teacherSelectLastOption() {
    this.teacherSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  teacherSelectOption(option) {
    this.teacherSelect.sendKeys(option);
  }

  getTeacherSelect() {
    return this.teacherSelect;
  }

  getTeacherSelectedOption() {
    return this.teacherSelect.element(by.css('option:checked')).getText();
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
