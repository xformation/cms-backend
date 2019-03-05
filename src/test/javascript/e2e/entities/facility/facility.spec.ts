/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import FacilityComponentsPage from './facility.page-object';
import FacilityUpdatePage from './facility-update.page-object';

const expect = chai.expect;

describe('Facility e2e test', () => {
  let navBarPage: NavBarPage;
  let facilityUpdatePage: FacilityUpdatePage;
  let facilityComponentsPage: FacilityComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Facilities', async () => {
    navBarPage.getEntityPage('facility');
    facilityComponentsPage = new FacilityComponentsPage();
    expect(await facilityComponentsPage.getTitle().getText()).to.match(/Facilities/);
  });

  it('should load create Facility page', async () => {
    facilityComponentsPage.clickOnCreateButton();
    facilityUpdatePage = new FacilityUpdatePage();
    expect(await facilityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Facility/);
  });

  it('should create and save Facilities', async () => {
    facilityUpdatePage.transportSelectLastOption();
    facilityUpdatePage.messSelectLastOption();
    facilityUpdatePage.gymSelectLastOption();
    facilityUpdatePage.culturalClassSelectLastOption();
    facilityUpdatePage.librarySelectLastOption();
    facilityUpdatePage.sportsSelectLastOption();
    facilityUpdatePage.swimmingSelectLastOption();
    facilityUpdatePage.extraClassSelectLastOption();
    facilityUpdatePage.handicraftsSelectLastOption();
    facilityUpdatePage.academicYearSelectLastOption();
    facilityUpdatePage.branchSelectLastOption();
    facilityUpdatePage.studentSelectLastOption();
    await facilityUpdatePage.save();
    expect(await facilityUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
