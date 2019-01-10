/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CourseOfferComponentsPage from './course-offer.page-object';
import CourseOfferUpdatePage from './course-offer-update.page-object';

const expect = chai.expect;

describe('CourseOffer e2e test', () => {
  let navBarPage: NavBarPage;
  let courseOfferUpdatePage: CourseOfferUpdatePage;
  let courseOfferComponentsPage: CourseOfferComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load CourseOffers', async () => {
    navBarPage.getEntityPage('course-offer');
    courseOfferComponentsPage = new CourseOfferComponentsPage();
    expect(await courseOfferComponentsPage.getTitle().getText()).to.match(/Course Offers/);
  });

  it('should load create CourseOffer page', async () => {
    courseOfferComponentsPage.clickOnCreateButton();
    courseOfferUpdatePage = new CourseOfferUpdatePage();
    expect(await courseOfferUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.courseOffer.home.createOrEditLabel/);
  });

  it('should create and save CourseOffers', async () => {
    courseOfferUpdatePage.setDescInput('desc');
    expect(await courseOfferUpdatePage.getDescInput()).to.match(/desc/);
    courseOfferUpdatePage.collegeSelectLastOption();
    courseOfferUpdatePage.departmentSelectLastOption();
    courseOfferUpdatePage.subjectSelectLastOption();
    await courseOfferUpdatePage.save();
    expect(await courseOfferUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
