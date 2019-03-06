import { element, by, ElementFinder } from 'protractor';

export default class FacilityUpdatePage {
  pageTitle: ElementFinder = element(by.id('cmsApp.facility.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  transportSelect: ElementFinder = element(by.css('select#facility-transport'));
  messSelect: ElementFinder = element(by.css('select#facility-mess'));
  gymSelect: ElementFinder = element(by.css('select#facility-gym'));
  culturalClassSelect: ElementFinder = element(by.css('select#facility-culturalClass'));
  librarySelect: ElementFinder = element(by.css('select#facility-library'));
  sportsSelect: ElementFinder = element(by.css('select#facility-sports'));
  swimmingSelect: ElementFinder = element(by.css('select#facility-swimming'));
  extraClassSelect: ElementFinder = element(by.css('select#facility-extraClass'));
  handicraftsSelect: ElementFinder = element(by.css('select#facility-handicrafts'));
  academicYearSelect: ElementFinder = element(by.css('select#facility-academicYear'));
  branchSelect: ElementFinder = element(by.css('select#facility-branch'));
  studentSelect: ElementFinder = element(by.css('select#facility-student'));

  getPageTitle() {
    return this.pageTitle;
  }

  setTransportSelect(transport) {
    this.transportSelect.sendKeys(transport);
  }

  getTransportSelect() {
    return this.transportSelect.element(by.css('option:checked')).getText();
  }

  transportSelectLastOption() {
    this.transportSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setMessSelect(mess) {
    this.messSelect.sendKeys(mess);
  }

  getMessSelect() {
    return this.messSelect.element(by.css('option:checked')).getText();
  }

  messSelectLastOption() {
    this.messSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setGymSelect(gym) {
    this.gymSelect.sendKeys(gym);
  }

  getGymSelect() {
    return this.gymSelect.element(by.css('option:checked')).getText();
  }

  gymSelectLastOption() {
    this.gymSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCulturalClassSelect(culturalClass) {
    this.culturalClassSelect.sendKeys(culturalClass);
  }

  getCulturalClassSelect() {
    return this.culturalClassSelect.element(by.css('option:checked')).getText();
  }

  culturalClassSelectLastOption() {
    this.culturalClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setLibrarySelect(library) {
    this.librarySelect.sendKeys(library);
  }

  getLibrarySelect() {
    return this.librarySelect.element(by.css('option:checked')).getText();
  }

  librarySelectLastOption() {
    this.librarySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSportsSelect(sports) {
    this.sportsSelect.sendKeys(sports);
  }

  getSportsSelect() {
    return this.sportsSelect.element(by.css('option:checked')).getText();
  }

  sportsSelectLastOption() {
    this.sportsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setSwimmingSelect(swimming) {
    this.swimmingSelect.sendKeys(swimming);
  }

  getSwimmingSelect() {
    return this.swimmingSelect.element(by.css('option:checked')).getText();
  }

  swimmingSelectLastOption() {
    this.swimmingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setExtraClassSelect(extraClass) {
    this.extraClassSelect.sendKeys(extraClass);
  }

  getExtraClassSelect() {
    return this.extraClassSelect.element(by.css('option:checked')).getText();
  }

  extraClassSelectLastOption() {
    this.extraClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setHandicraftsSelect(handicrafts) {
    this.handicraftsSelect.sendKeys(handicrafts);
  }

  getHandicraftsSelect() {
    return this.handicraftsSelect.element(by.css('option:checked')).getText();
  }

  handicraftsSelectLastOption() {
    this.handicraftsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  academicYearSelectLastOption() {
    this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  academicYearSelectOption(option) {
    this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
  }

  branchSelectLastOption() {
    this.branchSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  branchSelectOption(option) {
    this.branchSelect.sendKeys(option);
  }

  getBranchSelect() {
    return this.branchSelect;
  }

  getBranchSelectedOption() {
    return this.branchSelect.element(by.css('option:checked')).getText();
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
