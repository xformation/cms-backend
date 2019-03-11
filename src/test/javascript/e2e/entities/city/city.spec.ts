/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CityComponentsPage from './city.page-object';
import CityUpdatePage from './city-update.page-object';

const expect = chai.expect;

describe('City e2e test', () => {
  let navBarPage: NavBarPage;
  let cityUpdatePage: CityUpdatePage;
  let cityComponentsPage: CityComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Cities', async () => {
    navBarPage.getEntityPage('city');
    cityComponentsPage = new CityComponentsPage();
    expect(await cityComponentsPage.getTitle().getText()).to.match(/Cities/);
  });

  it('should load create City page', async () => {
    cityComponentsPage.clickOnCreateButton();
    cityUpdatePage = new CityUpdatePage();
    expect(await cityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a City/);
  });

  it('should create and save Cities', async () => {
    cityUpdatePage.setCityNameInput('cityName');
    expect(await cityUpdatePage.getCityNameInput()).to.match(/cityName/);
    cityUpdatePage.setCityCodeInput('cityCode');
    expect(await cityUpdatePage.getCityCodeInput()).to.match(/cityCode/);
    cityUpdatePage.setStdCodeInput('stdCode');
    expect(await cityUpdatePage.getStdCodeInput()).to.match(/stdCode/);
    cityUpdatePage.stateSelectLastOption();
    await cityUpdatePage.save();
    expect(await cityUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
