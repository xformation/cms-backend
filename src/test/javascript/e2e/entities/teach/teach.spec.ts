/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TeachComponentsPage from './teach.page-object';
import { TeachDeleteDialog } from './teach.page-object';
import TeachUpdatePage from './teach-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Teach e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let teachUpdatePage: TeachUpdatePage;
  let teachComponentsPage: TeachComponentsPage;
  let teachDeleteDialog: TeachDeleteDialog;

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

  it('should load Teaches', async () => {
    await navBarPage.getEntityPage('teach');
    teachComponentsPage = new TeachComponentsPage();
    expect(await teachComponentsPage.getTitle().getText()).to.match(/Teaches/);
  });

  it('should load create Teach page', async () => {
    await teachComponentsPage.clickOnCreateButton();
    teachUpdatePage = new TeachUpdatePage();
    expect(await teachUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Teach/);
  });

  it('should create and save Teaches', async () => {
    const nbButtonsBeforeCreate = await teachComponentsPage.countDeleteButtons();

    await teachUpdatePage.setDescInput('desc');
    expect(await teachUpdatePage.getDescInput()).to.match(/desc/);
    await teachUpdatePage.subjectSelectLastOption();
    await teachUpdatePage.teacherSelectLastOption();
    await waitUntilDisplayed(teachUpdatePage.getSaveButton());
    await teachUpdatePage.save();
    await waitUntilHidden(teachUpdatePage.getSaveButton());
    expect(await teachUpdatePage.getSaveButton().isPresent()).to.be.false;

    await teachComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await teachComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Teach', async () => {
    await teachComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await teachComponentsPage.countDeleteButtons();
    await teachComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    teachDeleteDialog = new TeachDeleteDialog();
    expect(await teachDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.teach.delete.question/);
    await teachDeleteDialog.clickOnConfirmButton();

    await teachComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await teachComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
