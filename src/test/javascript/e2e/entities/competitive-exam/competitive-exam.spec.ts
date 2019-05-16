/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CompetitiveExamComponentsPage from './competitive-exam.page-object';
import { CompetitiveExamDeleteDialog } from './competitive-exam.page-object';
import CompetitiveExamUpdatePage from './competitive-exam-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('CompetitiveExam e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let competitiveExamUpdatePage: CompetitiveExamUpdatePage;
  let competitiveExamComponentsPage: CompetitiveExamComponentsPage;
  let competitiveExamDeleteDialog: CompetitiveExamDeleteDialog;

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

  it('should load CompetitiveExams', async () => {
    await navBarPage.getEntityPage('competitive-exam');
    competitiveExamComponentsPage = new CompetitiveExamComponentsPage();
    expect(await competitiveExamComponentsPage.getTitle().getText()).to.match(/Competitive Exams/);
  });

  it('should load create CompetitiveExam page', async () => {
    await competitiveExamComponentsPage.clickOnCreateButton();
    competitiveExamUpdatePage = new CompetitiveExamUpdatePage();
    expect(await competitiveExamUpdatePage.getPageTitle().getText()).to.match(/Create or edit a CompetitiveExam/);
  });

  it('should create and save CompetitiveExams', async () => {
    const nbButtonsBeforeCreate = await competitiveExamComponentsPage.countDeleteButtons();

    await competitiveExamUpdatePage.setTestNameInput('testName');
    expect(await competitiveExamUpdatePage.getTestNameInput()).to.match(/testName/);
    await competitiveExamUpdatePage.setTestScoreInput('5');
    expect(await competitiveExamUpdatePage.getTestScoreInput()).to.eq('5');
    await competitiveExamUpdatePage.setEnrollmentNoInput('5');
    expect(await competitiveExamUpdatePage.getEnrollmentNoInput()).to.eq('5');
    await competitiveExamUpdatePage.setRankInput('5');
    expect(await competitiveExamUpdatePage.getRankInput()).to.eq('5');
    await competitiveExamUpdatePage.studentSelectLastOption();
    await waitUntilDisplayed(competitiveExamUpdatePage.getSaveButton());
    await competitiveExamUpdatePage.save();
    await waitUntilHidden(competitiveExamUpdatePage.getSaveButton());
    expect(await competitiveExamUpdatePage.getSaveButton().isPresent()).to.be.false;

    await competitiveExamComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await competitiveExamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last CompetitiveExam', async () => {
    await competitiveExamComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await competitiveExamComponentsPage.countDeleteButtons();
    await competitiveExamComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    competitiveExamDeleteDialog = new CompetitiveExamDeleteDialog();
    expect(await competitiveExamDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.competitiveExam.delete.question/);
    await competitiveExamDeleteDialog.clickOnConfirmButton();

    await competitiveExamComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await competitiveExamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
