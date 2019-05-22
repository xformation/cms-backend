/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AcademicHistoryComponentsPage from './academic-history.page-object';
import { AcademicHistoryDeleteDialog } from './academic-history.page-object';
import AcademicHistoryUpdatePage from './academic-history-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AcademicHistory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let academicHistoryUpdatePage: AcademicHistoryUpdatePage;
  let academicHistoryComponentsPage: AcademicHistoryComponentsPage;
  let academicHistoryDeleteDialog: AcademicHistoryDeleteDialog;

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

  it('should load AcademicHistories', async () => {
    await navBarPage.getEntityPage('academic-history');
    academicHistoryComponentsPage = new AcademicHistoryComponentsPage();
    expect(await academicHistoryComponentsPage.getTitle().getText()).to.match(/Academic Histories/);
  });

  it('should load create AcademicHistory page', async () => {
    await academicHistoryComponentsPage.clickOnCreateButton();
    academicHistoryUpdatePage = new AcademicHistoryUpdatePage();
    expect(await academicHistoryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AcademicHistory/);
  });

  it('should create and save AcademicHistories', async () => {
    const nbButtonsBeforeCreate = await academicHistoryComponentsPage.countDeleteButtons();

    await academicHistoryUpdatePage.setQualificationInput('qualification');
    expect(await academicHistoryUpdatePage.getQualificationInput()).to.match(/qualification/);
    await academicHistoryUpdatePage.setYearOfPassingInput('yearOfPassing');
    expect(await academicHistoryUpdatePage.getYearOfPassingInput()).to.match(/yearOfPassing/);
    await academicHistoryUpdatePage.setInstitutionInput('institution');
    expect(await academicHistoryUpdatePage.getInstitutionInput()).to.match(/institution/);
    await academicHistoryUpdatePage.setUniversityInput('university');
    expect(await academicHistoryUpdatePage.getUniversityInput()).to.match(/university/);
    await academicHistoryUpdatePage.setEnrollmentNoInput('5');
    expect(await academicHistoryUpdatePage.getEnrollmentNoInput()).to.eq('5');
    await academicHistoryUpdatePage.setScoreInput('5');
    expect(await academicHistoryUpdatePage.getScoreInput()).to.eq('5');
    await academicHistoryUpdatePage.setPercentageInput('5');
    expect(await academicHistoryUpdatePage.getPercentageInput()).to.eq('5');
    await academicHistoryUpdatePage.studentSelectLastOption();
    await waitUntilDisplayed(academicHistoryUpdatePage.getSaveButton());
    await academicHistoryUpdatePage.save();
    await waitUntilHidden(academicHistoryUpdatePage.getSaveButton());
    expect(await academicHistoryUpdatePage.getSaveButton().isPresent()).to.be.false;

    await academicHistoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await academicHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AcademicHistory', async () => {
    await academicHistoryComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await academicHistoryComponentsPage.countDeleteButtons();
    await academicHistoryComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    academicHistoryDeleteDialog = new AcademicHistoryDeleteDialog();
    expect(await academicHistoryDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.academicHistory.delete.question/);
    await academicHistoryDeleteDialog.clickOnConfirmButton();

    await academicHistoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await academicHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
