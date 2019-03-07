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

  async setTransportSelect(transport) {
    await this.transportSelect.sendKeys(transport);
  }

  async getTransportSelect() {
    return this.transportSelect.element(by.css('option:checked')).getText();
  }

  async transportSelectLastOption() {
    await this.transportSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setMessSelect(mess) {
    await this.messSelect.sendKeys(mess);
  }

  async getMessSelect() {
    return this.messSelect.element(by.css('option:checked')).getText();
  }

  async messSelectLastOption() {
    await this.messSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setGymSelect(gym) {
    await this.gymSelect.sendKeys(gym);
  }

  async getGymSelect() {
    return this.gymSelect.element(by.css('option:checked')).getText();
  }

  async gymSelectLastOption() {
    await this.gymSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCulturalClassSelect(culturalClass) {
    await this.culturalClassSelect.sendKeys(culturalClass);
  }

  async getCulturalClassSelect() {
    return this.culturalClassSelect.element(by.css('option:checked')).getText();
  }

  async culturalClassSelectLastOption() {
    await this.culturalClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setLibrarySelect(library) {
    await this.librarySelect.sendKeys(library);
  }

  async getLibrarySelect() {
    return this.librarySelect.element(by.css('option:checked')).getText();
  }

  async librarySelectLastOption() {
    await this.librarySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setSportsSelect(sports) {
    await this.sportsSelect.sendKeys(sports);
  }

  async getSportsSelect() {
    return this.sportsSelect.element(by.css('option:checked')).getText();
  }

  async sportsSelectLastOption() {
    await this.sportsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setSwimmingSelect(swimming) {
    await this.swimmingSelect.sendKeys(swimming);
  }

  async getSwimmingSelect() {
    return this.swimmingSelect.element(by.css('option:checked')).getText();
  }

  async swimmingSelectLastOption() {
    await this.swimmingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setExtraClassSelect(extraClass) {
    await this.extraClassSelect.sendKeys(extraClass);
  }

  async getExtraClassSelect() {
    return this.extraClassSelect.element(by.css('option:checked')).getText();
  }

  async extraClassSelectLastOption() {
    await this.extraClassSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setHandicraftsSelect(handicrafts) {
    await this.handicraftsSelect.sendKeys(handicrafts);
  }

  async getHandicraftsSelect() {
    return this.handicraftsSelect.element(by.css('option:checked')).getText();
  }

  async handicraftsSelectLastOption() {
    await this.handicraftsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async academicYearSelectLastOption() {
    await this.academicYearSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async academicYearSelectOption(option) {
    await this.academicYearSelect.sendKeys(option);
  }

  getAcademicYearSelect() {
    return this.academicYearSelect;
  }

  async getAcademicYearSelectedOption() {
    return this.academicYearSelect.element(by.css('option:checked')).getText();
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
