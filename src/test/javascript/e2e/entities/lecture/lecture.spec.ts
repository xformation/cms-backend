/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LectureComponentsPage from './lecture.page-object';
import { LectureDeleteDialog } from './lecture.page-object';
import LectureUpdatePage from './lecture-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Lecture e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lectureUpdatePage: LectureUpdatePage;
  let lectureComponentsPage: LectureComponentsPage;
  let lectureDeleteDialog: LectureDeleteDialog;

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

  it('should load Lectures', async () => {
    await navBarPage.getEntityPage('lecture');
    lectureComponentsPage = new LectureComponentsPage();
    expect(await lectureComponentsPage.getTitle().getText()).to.match(/Lectures/);
  });

  it('should load create Lecture page', async () => {
    await lectureComponentsPage.clickOnCreateButton();
    lectureUpdatePage = new LectureUpdatePage();
    expect(await lectureUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Lecture/);
  });

  it('should create and save Lectures', async () => {
    const nbButtonsBeforeCreate = await lectureComponentsPage.countDeleteButtons();

    await lectureUpdatePage.setLecDateInput('01-01-2001');
    expect(await lectureUpdatePage.getLecDateInput()).to.eq('2001-01-01');
    await lectureUpdatePage.setLastUpdatedOnInput('01-01-2001');
    expect(await lectureUpdatePage.getLastUpdatedOnInput()).to.eq('2001-01-01');
    await lectureUpdatePage.setLastUpdatedByInput('lastUpdatedBy');
    expect(await lectureUpdatePage.getLastUpdatedByInput()).to.match(/lastUpdatedBy/);
    await lectureUpdatePage.setStartTimeInput('startTime');
    expect(await lectureUpdatePage.getStartTimeInput()).to.match(/startTime/);
    await lectureUpdatePage.setEndTimeInput('endTime');
    expect(await lectureUpdatePage.getEndTimeInput()).to.match(/endTime/);
    await lectureUpdatePage.attendancemasterSelectLastOption();
    await waitUntilDisplayed(lectureUpdatePage.getSaveButton());
    await lectureUpdatePage.save();
    await waitUntilHidden(lectureUpdatePage.getSaveButton());
    expect(await lectureUpdatePage.getSaveButton().isPresent()).to.be.false;

    await lectureComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await lectureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Lecture', async () => {
    await lectureComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await lectureComponentsPage.countDeleteButtons();
    await lectureComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    lectureDeleteDialog = new LectureDeleteDialog();
    expect(await lectureDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.lecture.delete.question/);
    await lectureDeleteDialog.clickOnConfirmButton();

    await lectureComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await lectureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
