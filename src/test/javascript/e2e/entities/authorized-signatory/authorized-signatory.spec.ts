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
    expect(await authorizedSignatoryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AuthorizedSignatory/);
  });

  it('should create and save AuthorizedSignatories', async () => {
    authorizedSignatoryUpdatePage.setSignatoryNameInput('signatoryName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryNameInput()).to.match(/signatoryName/);
    authorizedSignatoryUpdatePage.setSignatoryFatherNameInput('signatoryFatherName');
    expect(await authorizedSignatoryUpdatePage.getSignatoryFatherNameInput()).to.match(/signatoryFatherName/);
    authorizedSignatoryUpdatePage.setSignatoryDesignationInput('signatoryDesignation');
    expect(await authorizedSignatoryUpdatePage.getSignatoryDesignationInput()).to.match(/signatoryDesignation/);
    authorizedSignatoryUpdatePage.setAddress1Input('address1');
    expect(await authorizedSignatoryUpdatePage.getAddress1Input()).to.match(/address1/);
    authorizedSignatoryUpdatePage.setAddress2Input('address2');
    expect(await authorizedSignatoryUpdatePage.getAddress2Input()).to.match(/address2/);
    authorizedSignatoryUpdatePage.setAddress3Input('address3');
    expect(await authorizedSignatoryUpdatePage.getAddress3Input()).to.match(/address3/);
    authorizedSignatoryUpdatePage.setAddress4Input('address4');
    expect(await authorizedSignatoryUpdatePage.getAddress4Input()).to.match(/address4/);
    authorizedSignatoryUpdatePage.setAddress5Input('address5');
    expect(await authorizedSignatoryUpdatePage.getAddress5Input()).to.match(/address5/);
    authorizedSignatoryUpdatePage.setEmailInput('email');
    expect(await authorizedSignatoryUpdatePage.getEmailInput()).to.match(/email/);
    authorizedSignatoryUpdatePage.setPanCardNumberInput('panCardNumber');
    expect(await authorizedSignatoryUpdatePage.getPanCardNumberInput()).to.match(/panCardNumber/);
    authorizedSignatoryUpdatePage.branchSelectLastOption();
    authorizedSignatoryUpdatePage.collegeSelectLastOption();
    await authorizedSignatoryUpdatePage.save();
    expect(await authorizedSignatoryUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
