/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import MetaLectureComponentsPage from './meta-lecture.page-object';
import { MetaLectureDeleteDialog } from './meta-lecture.page-object';
import MetaLectureUpdatePage from './meta-lecture-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('MetaLecture e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let metaLectureUpdatePage: MetaLectureUpdatePage;
  let metaLectureComponentsPage: MetaLectureComponentsPage;
  let metaLectureDeleteDialog: MetaLectureDeleteDialog;

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

  it('should load MetaLectures', async () => {
    await navBarPage.getEntityPage('meta-lecture');
    metaLectureComponentsPage = new MetaLectureComponentsPage();
    expect(await metaLectureComponentsPage.getTitle().getText()).to.match(/Meta Lectures/);
  });

  it('should load create MetaLecture page', async () => {
    await metaLectureComponentsPage.clickOnCreateButton();
    metaLectureUpdatePage = new MetaLectureUpdatePage();
    expect(await metaLectureUpdatePage.getPageTitle().getText()).to.match(/Create or edit a MetaLecture/);
  });

  it('should create and save MetaLectures', async () => {
    const nbButtonsBeforeCreate = await metaLectureComponentsPage.countDeleteButtons();

    await metaLectureUpdatePage.setWeekDayInput('weekDay');
    expect(await metaLectureUpdatePage.getWeekDayInput()).to.match(/weekDay/);
    await metaLectureUpdatePage.setStartTimeInput('startTime');
    expect(await metaLectureUpdatePage.getStartTimeInput()).to.match(/startTime/);
    await metaLectureUpdatePage.setEndTimeInput('endTime');
    expect(await metaLectureUpdatePage.getEndTimeInput()).to.match(/endTime/);
    await metaLectureUpdatePage.branchSelectLastOption();
    await metaLectureUpdatePage.departmentSelectLastOption();
    await metaLectureUpdatePage.subjectSelectLastOption();
    await metaLectureUpdatePage.teacherSelectLastOption();
    await metaLectureUpdatePage.termSelectLastOption();
    await metaLectureUpdatePage.academicyearSelectLastOption();
    await metaLectureUpdatePage.sectionSelectLastOption();
    await metaLectureUpdatePage.batchSelectLastOption();
    await waitUntilDisplayed(metaLectureUpdatePage.getSaveButton());
    await metaLectureUpdatePage.save();
    await waitUntilHidden(metaLectureUpdatePage.getSaveButton());
    expect(await metaLectureUpdatePage.getSaveButton().isPresent()).to.be.false;

    await metaLectureComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await metaLectureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last MetaLecture', async () => {
    await metaLectureComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await metaLectureComponentsPage.countDeleteButtons();
    await metaLectureComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    metaLectureDeleteDialog = new MetaLectureDeleteDialog();
    expect(await metaLectureDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.metaLecture.delete.question/);
    await metaLectureDeleteDialog.clickOnConfirmButton();

    await metaLectureComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await metaLectureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
