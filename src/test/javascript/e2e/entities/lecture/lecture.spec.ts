/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import LectureComponentsPage from './lecture.page-object';
import LectureUpdatePage from './lecture-update.page-object';

const expect = chai.expect;

describe('Lecture e2e test', () => {
  let navBarPage: NavBarPage;
  let lectureUpdatePage: LectureUpdatePage;
  let lectureComponentsPage: LectureComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Lectures', async () => {
    navBarPage.getEntityPage('lecture');
    lectureComponentsPage = new LectureComponentsPage();
    expect(await lectureComponentsPage.getTitle().getText()).to.match(/Lectures/);
  });

  it('should load create Lecture page', async () => {
    lectureComponentsPage.clickOnCreateButton();
    lectureUpdatePage = new LectureUpdatePage();
    expect(await lectureUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.lecture.home.createOrEditLabel/);
  });

  it('should create and save Lectures', async () => {
    lectureUpdatePage.setLecDateInput('01-01-2001');
    expect(await lectureUpdatePage.getLecDateInput()).to.eq('2001-01-01');
    lectureUpdatePage.setLastUpdatedOnInput('01-01-2001');
    expect(await lectureUpdatePage.getLastUpdatedOnInput()).to.eq('2001-01-01');
    lectureUpdatePage.setLastUpdatedByInput('lastUpdatedBy');
    expect(await lectureUpdatePage.getLastUpdatedByInput()).to.match(/lastUpdatedBy/);
    lectureUpdatePage.lecStatusSelectLastOption();
    lectureUpdatePage.setDescInput('desc');
    expect(await lectureUpdatePage.getDescInput()).to.match(/desc/);
    lectureUpdatePage.attendancemasterSelectLastOption();
    await lectureUpdatePage.save();
    expect(await lectureUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
