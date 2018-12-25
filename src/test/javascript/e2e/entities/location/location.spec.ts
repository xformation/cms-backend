/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import LocationComponentsPage from './location.page-object';
import LocationUpdatePage from './location-update.page-object';

const expect = chai.expect;

describe('Location e2e test', () => {
  let navBarPage: NavBarPage;
  let locationUpdatePage: LocationUpdatePage;
  let locationComponentsPage: LocationComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Locations', async () => {
    navBarPage.getEntityPage('location');
    locationComponentsPage = new LocationComponentsPage();
    expect(await locationComponentsPage.getTitle().getText()).to.match(/Locations/);
  });

  it('should load create Location page', async () => {
    locationComponentsPage.clickOnCreateButton();
    locationUpdatePage = new LocationUpdatePage();
    expect(await locationUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.location.home.createOrEditLabel/);
  });

  it('should create and save Locations', async () => {
    locationUpdatePage.setNameInput('name');
    expect(await locationUpdatePage.getNameInput()).to.match(/name/);
    locationUpdatePage.setAddressInput('address');
    expect(await locationUpdatePage.getAddressInput()).to.match(/address/);
    locationUpdatePage.setAppliesToInput('appliesTo');
    expect(await locationUpdatePage.getAppliesToInput()).to.match(/appliesTo/);
    await locationUpdatePage.save();
    expect(await locationUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
