/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import StudentExamReportComponentsPage from './student-exam-report.page-object';
import { StudentExamReportDeleteDialog } from './student-exam-report.page-object';
import StudentExamReportUpdatePage from './student-exam-report-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('StudentExamReport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studentExamReportUpdatePage: StudentExamReportUpdatePage;
  let studentExamReportComponentsPage: StudentExamReportComponentsPage;
  let studentExamReportDeleteDialog: StudentExamReportDeleteDialog;

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

  it('should load StudentExamReports', async () => {
    await navBarPage.getEntityPage('student-exam-report');
    studentExamReportComponentsPage = new StudentExamReportComponentsPage();
    expect(await studentExamReportComponentsPage.getTitle().getText()).to.match(/Student Exam Reports/);
  });

  it('should load create StudentExamReport page', async () => {
    await studentExamReportComponentsPage.clickOnCreateButton();
    studentExamReportUpdatePage = new StudentExamReportUpdatePage();
    expect(await studentExamReportUpdatePage.getPageTitle().getText()).to.match(/Create or edit a StudentExamReport/);
  });

  it('should create and save StudentExamReports', async () => {
    const nbButtonsBeforeCreate = await studentExamReportComponentsPage.countDeleteButtons();

    await studentExamReportUpdatePage.setMarksObtainedInput('5');
    expect(await studentExamReportUpdatePage.getMarksObtainedInput()).to.eq('5');
    await studentExamReportUpdatePage.setCommentsInput('comments');
    expect(await studentExamReportUpdatePage.getCommentsInput()).to.match(/comments/);
    await studentExamReportUpdatePage.setCreatedOnInput('01-01-2001');
    expect(await studentExamReportUpdatePage.getCreatedOnInput()).to.eq('2001-01-01');
    await studentExamReportUpdatePage.setCreatedByInput('createdBy');
    expect(await studentExamReportUpdatePage.getCreatedByInput()).to.match(/createdBy/);
    await studentExamReportUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await studentExamReportUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    await studentExamReportUpdatePage.setUpdatedByInput('updatedBy');
    expect(await studentExamReportUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    await studentExamReportUpdatePage.academicExamSettingSelectLastOption();
    await studentExamReportUpdatePage.studentSelectLastOption();
    await studentExamReportUpdatePage.typeOfGradingSelectLastOption();
    await studentExamReportUpdatePage.batchSelectLastOption();
    await studentExamReportUpdatePage.academicyearSelectLastOption();
    await waitUntilDisplayed(studentExamReportUpdatePage.getSaveButton());
    await studentExamReportUpdatePage.save();
    await waitUntilHidden(studentExamReportUpdatePage.getSaveButton());
    expect(await studentExamReportUpdatePage.getSaveButton().isPresent()).to.be.false;

    await studentExamReportComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await studentExamReportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last StudentExamReport', async () => {
    await studentExamReportComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await studentExamReportComponentsPage.countDeleteButtons();
    await studentExamReportComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    studentExamReportDeleteDialog = new StudentExamReportDeleteDialog();
    expect(await studentExamReportDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.studentExamReport.delete.question/);
    await studentExamReportDeleteDialog.clickOnConfirmButton();

    await studentExamReportComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await studentExamReportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
