/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TeacherComponentsPage from './teacher.page-object';
import { TeacherDeleteDialog } from './teacher.page-object';
import TeacherUpdatePage from './teacher-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Teacher e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let teacherUpdatePage: TeacherUpdatePage;
  let teacherComponentsPage: TeacherComponentsPage;
  let teacherDeleteDialog: TeacherDeleteDialog;

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

  it('should load Teachers', async () => {
    await navBarPage.getEntityPage('teacher');
    teacherComponentsPage = new TeacherComponentsPage();
    expect(await teacherComponentsPage.getTitle().getText()).to.match(/Teachers/);
  });

  it('should load create Teacher page', async () => {
    await teacherComponentsPage.clickOnCreateButton();
    teacherUpdatePage = new TeacherUpdatePage();
    expect(await teacherUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.teacher.home.createOrEditLabel/);
  });

  it('should create and save Teachers', async () => {
    const nbButtonsBeforeCreate = await teacherComponentsPage.countDeleteButtons();

    await teacherUpdatePage.setTNameInput('tName');
    expect(await teacherUpdatePage.getTNameInput()).to.match(/tName/);
    await teacherUpdatePage.periodsSelectLastOption();
    await waitUntilDisplayed(teacherUpdatePage.getSaveButton());
    await teacherUpdatePage.save();
    await waitUntilHidden(teacherUpdatePage.getSaveButton());
    expect(await teacherUpdatePage.getSaveButton().isPresent()).to.be.false;

    await teacherComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await teacherComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Teacher', async () => {
    await teacherComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await teacherComponentsPage.countDeleteButtons();
    await teacherComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    teacherDeleteDialog = new TeacherDeleteDialog();
    expect(await teacherDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.teacher.delete.question/);
    await teacherDeleteDialog.clickOnConfirmButton();

    await teacherComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await teacherComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
