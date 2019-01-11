/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AuthorizedSignatoryComponentsPage from './authorized-signatory.page-object';
import AuthorizedSignatoryUpdatePage from './authorized-signatory-update.page-object';

const expect = chai.expect;

describe('AuthorizedSignatory e2e test', () => {
  let navBarPage: NavBarPage;
  let authorizedSignatoryUpdatePage: AuthorizedSignatoryUpdatePage;
  let authorizedSignatoryComponentsPage: AuthorizedSignatoryComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load AuthorizedSignatories', async () => {
    navBarPage.getEntityPage('authorized-signatory');
    authorizedSignatoryComponentsPage = new AuthorizedSignatoryComponentsPage();
    expect(await authorizedSignatoryComponentsPage.getTitle().getText()).to.match(/Authorized Signatories/);
  });

  it('should load create AuthorizedSignatory page', async () => {
    authorizedSignatoryComponentsPage.clickOnCreateButton();
    authorizedSignatoryUpdatePage = new AuthorizedSignatoryUpdatePage();
    expect(await authorizedSignatoryUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /cmsApp.authorizedSignatory.home.createOrEditLabel/
    );
  });

  it('should create and save AuthorizedSignatories', async () => {
    authorizedSignatoryUpdatePage.setSignatoryNameInput('signatoryName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryNameInput()).to.match(/signatoryName/);
    authorizedSignatoryUpdatePage.setSignatoryFatherNameInput('signatoryFatherName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryFatherNameInput()).to.match(/signatoryFatherName/);
    authorizedSignatoryUpdatePage.setSignatoryDesignationInput('signatoryDesignation');
    expect(await authorizedSignatoryUpdatePage.getSignatoryDesignationInput()).to.match(/signatoryDesignation/);
    authorizedSignatoryUpdatePage.setAddressInput('address');
    expect(await authorizedSignatoryUpdatePage.getAddressInput()).to.match(/address/);
    authorizedSignatoryUpdatePage.setEmailInput('email');
    expect(await authorizedSignatoryUpdatePage.getEmailInput()).to.match(/email/);
    authorizedSignatoryUpdatePage.setPanCardNumberInput('panCardNumber');
    expect(await authorizedSignatoryUpdatePage.getPanCardNumberInput()).to.match(/panCardNumber/);
    authorizedSignatoryUpdatePage.legalEntitySelectLastOption();
    await authorizedSignatoryUpdatePage.save();
    expect(await authorizedSignatoryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
