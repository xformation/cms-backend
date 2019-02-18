/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AdminOverviewComponentsPage from './admin-overview.page-object';
import AdminOverviewUpdatePage from './admin-overview-update.page-object';

const expect = chai.expect;

describe('AdminOverview e2e test', () => {
  let navBarPage: NavBarPage;
  let adminOverviewUpdatePage: AdminOverviewUpdatePage;
  let adminOverviewComponentsPage: AdminOverviewComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AdminOverviews', async () => {
    navBarPage.getEntityPage('admin-overview');
    adminOverviewComponentsPage = new AdminOverviewComponentsPage();
    expect(await adminOverviewComponentsPage.getTitle().getText()).to.match(/Admin Overviews/);
  });

  it('should load create AdminOverview page', async () => {
    adminOverviewComponentsPage.clickOnCreateButton();
    adminOverviewUpdatePage = new AdminOverviewUpdatePage();
    expect(await adminOverviewUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdminOverview/);
  });

  it('should create and save AdminOverviews', async () => {
    adminOverviewUpdatePage.setChooseDateInput('01-01-2001');
    expect(await adminOverviewUpdatePage.getChooseDateInput()).to.eq('2001-01-01');
    adminOverviewUpdatePage.sectionSelectLastOption();
    adminOverviewUpdatePage.lectureOneSelectLastOption();
    adminOverviewUpdatePage.lectureTwoSelectLastOption();
    adminOverviewUpdatePage.lectureThreeSelectLastOption();
    adminOverviewUpdatePage.lectureFourSelectLastOption();
    adminOverviewUpdatePage.lectureFiveSelectLastOption();
    adminOverviewUpdatePage.lectureSixSelectLastOption();
    adminOverviewUpdatePage.lectureSevenSelectLastOption();
    adminOverviewUpdatePage.lectureEightSelectLastOption();
    adminOverviewUpdatePage.departmentSelectLastOption();
    adminOverviewUpdatePage.batchSelectLastOption();
    await adminOverviewUpdatePage.save();
    expect(await adminOverviewUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
