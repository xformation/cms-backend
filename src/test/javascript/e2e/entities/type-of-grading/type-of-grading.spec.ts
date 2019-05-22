/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TypeOfGradingComponentsPage from './type-of-grading.page-object';
import { TypeOfGradingDeleteDialog } from './type-of-grading.page-object';
import TypeOfGradingUpdatePage from './type-of-grading-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('TypeOfGrading e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeOfGradingUpdatePage: TypeOfGradingUpdatePage;
  let typeOfGradingComponentsPage: TypeOfGradingComponentsPage;
  let typeOfGradingDeleteDialog: TypeOfGradingDeleteDialog;

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

  it('should load TypeOfGradings', async () => {
    await navBarPage.getEntityPage('type-of-grading');
    typeOfGradingComponentsPage = new TypeOfGradingComponentsPage();
    expect(await typeOfGradingComponentsPage.getTitle().getText()).to.match(/Type Of Gradings/);
  });

  it('should load create TypeOfGrading page', async () => {
    await typeOfGradingComponentsPage.clickOnCreateButton();
    typeOfGradingUpdatePage = new TypeOfGradingUpdatePage();
    expect(await typeOfGradingUpdatePage.getPageTitle().getText()).to.match(/Create or edit a TypeOfGrading/);
  });

  it('should create and save TypeOfGradings', async () => {
    const nbButtonsBeforeCreate = await typeOfGradingComponentsPage.countDeleteButtons();

    await typeOfGradingUpdatePage.setMinMarksInput('5');
    expect(await typeOfGradingUpdatePage.getMinMarksInput()).to.eq('5');
    await typeOfGradingUpdatePage.setMaxMarksInput('5');
    expect(await typeOfGradingUpdatePage.getMaxMarksInput()).to.eq('5');
    await typeOfGradingUpdatePage.gradesSelectLastOption();
    await typeOfGradingUpdatePage.academicExamSettingSelectLastOption();
    await waitUntilDisplayed(typeOfGradingUpdatePage.getSaveButton());
    await typeOfGradingUpdatePage.save();
    await waitUntilHidden(typeOfGradingUpdatePage.getSaveButton());
    expect(await typeOfGradingUpdatePage.getSaveButton().isPresent()).to.be.false;

    await typeOfGradingComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await typeOfGradingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last TypeOfGrading', async () => {
    await typeOfGradingComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await typeOfGradingComponentsPage.countDeleteButtons();
    await typeOfGradingComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    typeOfGradingDeleteDialog = new TypeOfGradingDeleteDialog();
    expect(await typeOfGradingDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.typeOfGrading.delete.question/);
    await typeOfGradingDeleteDialog.clickOnConfirmButton();

    await typeOfGradingComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await typeOfGradingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
