/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StateComponentsPage from './state.page-object';
import StateUpdatePage from './state-update.page-object';

const expect = chai.expect;

describe('State e2e test', () => {
  let navBarPage: NavBarPage;
  let stateUpdatePage: StateUpdatePage;
  let stateComponentsPage: StateComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load States', async () => {
    navBarPage.getEntityPage('state');
    stateComponentsPage = new StateComponentsPage();
    expect(await stateComponentsPage.getTitle().getText()).to.match(/States/);
  });

  it('should load create State page', async () => {
    stateComponentsPage.clickOnCreateButton();
    stateUpdatePage = new StateUpdatePage();
    expect(await stateUpdatePage.getPageTitle().getText()).to.match(/Create or edit a State/);
  });

  it('should create and save States', async () => {
    stateUpdatePage.setStateNameInput('stateName');
    expect(await stateUpdatePage.getStateNameInput()).to.match(/stateName/);
    stateUpdatePage.setDivisionTypeInput('divisionType');
    expect(await stateUpdatePage.getDivisionTypeInput()).to.match(/divisionType/);
    stateUpdatePage.setStateCodeInput('stateCode');
    expect(await stateUpdatePage.getStateCodeInput()).to.match(/stateCode/);
    stateUpdatePage.countrySelectLastOption();
    await stateUpdatePage.save();
    expect(await stateUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
