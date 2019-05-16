/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AcademicYearComponentsPage from './academic-year.page-object';
import { AcademicYearDeleteDialog } from './academic-year.page-object';
import AcademicYearUpdatePage from './academic-year-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AcademicYear e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let academicYearUpdatePage: AcademicYearUpdatePage;
  let academicYearComponentsPage: AcademicYearComponentsPage;
  let academicYearDeleteDialog: AcademicYearDeleteDialog;

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

  it('should load AcademicYears', async () => {
    await navBarPage.getEntityPage('academic-year');
    academicYearComponentsPage = new AcademicYearComponentsPage();
    expect(await academicYearComponentsPage.getTitle().getText()).to.match(/Academic Years/);
  });

  it('should load create AcademicYear page', async () => {
    await academicYearComponentsPage.clickOnCreateButton();
    academicYearUpdatePage = new AcademicYearUpdatePage();
    expect(await academicYearUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AcademicYear/);
  });

  it('should create and save AcademicYears', async () => {
    const nbButtonsBeforeCreate = await academicYearComponentsPage.countDeleteButtons();

    await academicYearUpdatePage.setYearInput('year');
    expect(await academicYearUpdatePage.getYearInput()).to.match(/year/);
    await academicYearUpdatePage.setStartDateInput('01-01-2001');
    expect(await academicYearUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    await academicYearUpdatePage.setEndDateInput('01-01-2001');
    expect(await academicYearUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    await academicYearUpdatePage.statusSelectLastOption();
    await waitUntilDisplayed(academicYearUpdatePage.getSaveButton());
    await academicYearUpdatePage.save();
    await waitUntilHidden(academicYearUpdatePage.getSaveButton());
    expect(await academicYearUpdatePage.getSaveButton().isPresent()).to.be.false;

    await academicYearComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await academicYearComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AcademicYear', async () => {
    await academicYearComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await academicYearComponentsPage.countDeleteButtons();
    await academicYearComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    academicYearDeleteDialog = new AcademicYearDeleteDialog();
    expect(await academicYearDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.academicYear.delete.question/);
    await academicYearDeleteDialog.clickOnConfirmButton();

    await academicYearComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await academicYearComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
