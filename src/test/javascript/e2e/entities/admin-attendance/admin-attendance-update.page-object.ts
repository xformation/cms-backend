import { element, by, ElementFinder } from 'protractor';

export default class AdminAttendanceUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.adminAttendance.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  updatedOnInput: ElementFinder = element(by.css('input#admin-attendance-updatedOn'));
  updatedByInput: ElementFinder = element(by.css('input#admin-attendance-updatedBy'));
  lectureSelect: ElementFinder = element(by.css('select#admin-attendance-lecture'));
  branchSelect: ElementFinder = element(by.css('select#admin-attendance-branch'));
  collegeSelect: ElementFinder = element(by.css('select#admin-attendance-college'));
  departmentSelect: ElementFinder = element(by.css('select#admin-attendance-department'));
  academicyearSelect: ElementFinder = element(by.css('select#admin-attendance-academicyear'));
  sectionSelect: ElementFinder = element(by.css('select#admin-attendance-section'));
  studentSelect: ElementFinder = element(by.css('select#admin-attendance-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUpdatedOnInput(updatedOn) {
    await this.updatedOnInput.sendKeys(updatedOn);
  }

  async getUpdatedOnInput() {
    return this.updatedOnInput.getAttribute('value');
  }

  async setUpdatedByInput(updatedBy) {
    await this.updatedByInput.sendKeys(updatedBy);
  }

  async getUpdatedByInput() {
    return this.updatedByInput.getAttribute('value');
  }

  async lectureSelectLastOption() {
    await this.lectureSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async lectureSelectOption(option) {
    await this.lectureSelect.sendKeys(option);
  }

  getLectureSelect() {
    return this.lectureSelect;
  }

  async getLectureSelectedOption() {
    return this.lectureSelect.element(by.css('option:checked')).getText();
  }

  async branchSelectLastOption() {
    await this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async branchSelectOption(option) {
    await this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  async getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
  }

  async collegeSelectLastOption() {
    await this.collegeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async collegeSelectOption(option) {
    await this.collegeSelect.sendKeys(option);
  }

  getCollegeSelect() {
    return this.collegeSelect;
  }

  async getCollegeSelectedOption() {
    return this.collegeSelect.element(by.css('option:checked')).getText();
  }

  async departmentSelectLastOption() {
    await this.departmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async departmentSelectOption(option) {
    await this.departmentSelect.sendKeys(option);
  }

  getDepartmentSelect() {
    return this.departmentSelect;
  }

  async getDepartmentSelectedOption() {
    return this.departmentSelect.element(by.css('option:checked')).getText();
  }

  async academicyearSelectLastOption() {
    await this.academicyearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicyearSelectOption(option) {
    await this.academicyearSelect.sendKeys(option);
  }

  getAcademicyearSelect() {
    return this.academicyearSelect;
  }

  async getAcademicyearSelectedOption() {
    return this.academicyearSelect.element(by.css('option:checked')).getText();
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

  async studentSelectLastOption() {
    await this.studentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async studentSelectOption(option) {
    await this.studentSelect.sendKeys(option);
  }

  getStudentSelect() {
    return this.studentSelect;
  }

  async getStudentSelectedOption() {
    return this.studentSelect.element(by.css('option:checked')).getText();
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
