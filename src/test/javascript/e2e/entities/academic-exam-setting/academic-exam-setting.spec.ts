/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AcademicExamSettingComponentsPage from './academic-exam-setting.page-object';
import { AcademicExamSettingDeleteDialog } from './academic-exam-setting.page-object';
import AcademicExamSettingUpdatePage from './academic-exam-setting-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AcademicExamSetting e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let academicExamSettingUpdatePage: AcademicExamSettingUpdatePage;
  let academicExamSettingComponentsPage: AcademicExamSettingComponentsPage;
  let academicExamSettingDeleteDialog: AcademicExamSettingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load AcademicExamSettings', async () => {
    await navBarPage.getEntityPage('academic-exam-setting');
    academicExamSettingComponentsPage = new AcademicExamSettingComponentsPage();
    expect(await academicExamSettingComponentsPage.getTitle().getText()).to.match(/Academic Exam Settings/);
  });

  it('should load create AcademicExamSetting page', async () => {
    await academicExamSettingComponentsPage.clickOnCreateButton();
    academicExamSettingUpdatePage = new AcademicExamSettingUpdatePage();
    expect(await academicExamSettingUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AcademicExamSetting/);
  });

  it('should create and save AcademicExamSettings', async () => {
    const nbButtonsBeforeCreate = await academicExamSettingComponentsPage.countDeleteButtons();

    await academicExamSettingUpdatePage.setExamTypeInput('examType');
    expect(await academicExamSettingUpdatePage.getExamTypeInput()).to.match(/examType/);
    await academicExamSettingUpdatePage.semesterSelectLastOption();
    await academicExamSettingUpdatePage.setSubjectInput('subject');
    expect(await academicExamSettingUpdatePage.getSubjectInput()).to.match(/subject/);
    await academicExamSettingUpdatePage.setExamDateInput('01-01-2001');
    expect(await academicExamSettingUpdatePage.getExamDateInput()).to.eq('2001-01-01');
    await academicExamSettingUpdatePage.setDayInput('day');
    expect(await academicExamSettingUpdatePage.getDayInput()).to.match(/day/);
    await academicExamSettingUpdatePage.setDurationInput('duration');
    expect(await academicExamSettingUpdatePage.getDurationInput()).to.match(/duration/);
    await academicExamSettingUpdatePage.setStartTimeInput('startTime');
    expect(await academicExamSettingUpdatePage.getStartTimeInput()).to.match(/startTime/);
    await academicExamSettingUpdatePage.setEndTimeInput('endTime');
    expect(await academicExamSettingUpdatePage.getEndTimeInput()).to.match(/endTime/);
    await academicExamSettingUpdatePage.gradeTypeSelectLastOption();
    await academicExamSettingUpdatePage.setTotalInput('5');
    expect(await academicExamSettingUpdatePage.getTotalInput()).to.eq('5');
    await academicExamSettingUpdatePage.setPassingInput('5');
    expect(await academicExamSettingUpdatePage.getPassingInput()).to.eq('5');
    await academicExamSettingUpdatePage.setActionsInput('actions');
    expect(await academicExamSettingUpdatePage.getActionsInput()).to.match(/actions/);
    await academicExamSettingUpdatePage.departmentSelectLastOption();
    await academicExamSettingUpdatePage.academicyearSelectLastOption();
    await academicExamSettingUpdatePage.sectionSelectLastOption();
    await academicExamSettingUpdatePage.batchSelectLastOption();
    await waitUntilDisplayed(academicExamSettingUpdatePage.getSaveButton());
    await academicExamSettingUpdatePage.save();
    await waitUntilHidden(academicExamSettingUpdatePage.getSaveButton());
    expect(await academicExamSettingUpdatePage.getSaveButton().isPresent()).to.be.false;

    await academicExamSettingComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await academicExamSettingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AcademicExamSetting', async () => {
    await academicExamSettingComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await academicExamSettingComponentsPage.countDeleteButtons();
    await academicExamSettingComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    academicExamSettingDeleteDialog = new AcademicExamSettingDeleteDialog();
    expect(await academicExamSettingDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /cmsApp.academicExamSetting.delete.question/
    );
    await academicExamSettingDeleteDialog.clickOnConfirmButton();

    await academicExamSettingComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await academicExamSettingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
