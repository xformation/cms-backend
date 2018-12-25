/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PeriodsComponentsPage from './periods.page-object';
import PeriodsUpdatePage from './periods-update.page-object';

const expect = chai.expect;

describe('Periods e2e test', () => {
  let navBarPage: NavBarPage;
  let periodsUpdatePage: PeriodsUpdatePage;
  let periodsComponentsPage: PeriodsComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Periods', async () => {
    navBarPage.getEntityPage('periods');
    periodsComponentsPage = new PeriodsComponentsPage();
    expect(await periodsComponentsPage.getTitle().getText()).to.match(/Periods/);
  });

  it('should load create Periods page', async () => {
    periodsComponentsPage.clickOnCreateButton();
    periodsUpdatePage = new PeriodsUpdatePage();
    expect(await periodsUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.periods.home.createOrEditLabel/);
  });

  it('should create and save Periods', async () => {
    periodsUpdatePage.periodsSelectLastOption();
    periodsUpdatePage.sectionSelectLastOption();
    await periodsUpdatePage.save();
    expect(await periodsUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
