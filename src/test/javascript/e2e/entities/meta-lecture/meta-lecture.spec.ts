/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import MetaLectureComponentsPage from './meta-lecture.page-object';
import MetaLectureUpdatePage from './meta-lecture-update.page-object';

const expect = chai.expect;

describe('MetaLecture e2e test', () => {
  let navBarPage: NavBarPage;
  let metaLectureUpdatePage: MetaLectureUpdatePage;
  let metaLectureComponentsPage: MetaLectureComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load MetaLectures', async () => {
    navBarPage.getEntityPage('meta-lecture');
    metaLectureComponentsPage = new MetaLectureComponentsPage();
    expect(await metaLectureComponentsPage.getTitle().getText()).to.match(/Meta Lectures/);
  });

  it('should load create MetaLecture page', async () => {
    metaLectureComponentsPage.clickOnCreateButton();
    metaLectureUpdatePage = new MetaLectureUpdatePage();
    expect(await metaLectureUpdatePage.getPageTitle().getText()).to.match(/Create or edit a MetaLecture/);
  });

  it('should create and save MetaLectures', async () => {
    metaLectureUpdatePage.setWeekDayInput('weekDay');
    expect(await metaLectureUpdatePage.getWeekDayInput()).to.match(/weekDay/);
    metaLectureUpdatePage.setStartTimeInput('startTime');
    expect(await metaLectureUpdatePage.getStartTimeInput()).to.match(/startTime/);
    metaLectureUpdatePage.setEndTimeInput('endTime');
    expect(await metaLectureUpdatePage.getEndTimeInput()).to.match(/endTime/);
    metaLectureUpdatePage.branchSelectLastOption();
    metaLectureUpdatePage.departmentSelectLastOption();
    metaLectureUpdatePage.subjectSelectLastOption();
    metaLectureUpdatePage.teacherSelectLastOption();
    metaLectureUpdatePage.termSelectLastOption();
    metaLectureUpdatePage.academicyearSelectLastOption();
    metaLectureUpdatePage.sectionSelectLastOption();
    metaLectureUpdatePage.batchSelectLastOption();
    await metaLectureUpdatePage.save();
    expect(await metaLectureUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
