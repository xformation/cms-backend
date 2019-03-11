/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CurrencyComponentsPage from './currency.page-object';
import CurrencyUpdatePage from './currency-update.page-object';

const expect = chai.expect;

describe('Currency e2e test', () => {
  let navBarPage: NavBarPage;
  let currencyUpdatePage: CurrencyUpdatePage;
  let currencyComponentsPage: CurrencyComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Currencies', async () => {
    navBarPage.getEntityPage('currency');
    currencyComponentsPage = new CurrencyComponentsPage();
    expect(await currencyComponentsPage.getTitle().getText()).to.match(/Currencies/);
  });

  it('should load create Currency page', async () => {
    currencyComponentsPage.clickOnCreateButton();
    currencyUpdatePage = new CurrencyUpdatePage();
    expect(await currencyUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Currency/);
  });

  it('should create and save Currencies', async () => {
    currencyUpdatePage.setCurrencyNameInput('currencyName');
    expect(await currencyUpdatePage.getCurrencyNameInput()).to.match(/currencyName/);
    currencyUpdatePage.setCurrencyCodeInput('currencyCode');
    expect(await currencyUpdatePage.getCurrencyCodeInput()).to.match(/currencyCode/);
    currencyUpdatePage.setCurrencySymbolInput('currencySymbol');
    expect(await currencyUpdatePage.getCurrencySymbolInput()).to.match(/currencySymbol/);
    currencyUpdatePage.countrySelectLastOption();
    await currencyUpdatePage.save();
    expect(await currencyUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
