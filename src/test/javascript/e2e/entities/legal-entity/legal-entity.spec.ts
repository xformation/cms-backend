/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import LegalEntityComponentsPage from './legal-entity.page-object';
import LegalEntityUpdatePage from './legal-entity-update.page-object';

const expect = chai.expect;

describe('LegalEntity e2e test', () => {
  let navBarPage: NavBarPage;
  let legalEntityUpdatePage: LegalEntityUpdatePage;
  let legalEntityComponentsPage: LegalEntityComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load LegalEntities', async () => {
    navBarPage.getEntityPage('legal-entity');
    legalEntityComponentsPage = new LegalEntityComponentsPage();
    expect(await legalEntityComponentsPage.getTitle().getText()).to.match(/Legal Entities/);
  });

  it('should load create LegalEntity page', async () => {
    legalEntityComponentsPage.clickOnCreateButton();
    legalEntityUpdatePage = new LegalEntityUpdatePage();
    expect(await legalEntityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a LegalEntity/);
  });

  it('should create and save LegalEntities', async () => {
    legalEntityUpdatePage.setLogoInput('5');
    expect(await legalEntityUpdatePage.getLogoInput()).to.eq('5');
    legalEntityUpdatePage.setLegalNameOfTheCollegeInput('legalNameOfTheCollege');
    expect(await legalEntityUpdatePage.getLegalNameOfTheCollegeInput()).to.match(/legalNameOfTheCollege/);
    legalEntityUpdatePage.typeOfCollegeSelectLastOption();
    legalEntityUpdatePage.setDateOfIncorporationInput('01-01-2001');
    expect(await legalEntityUpdatePage.getDateOfIncorporationInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setRegisteredOfficeAddress1Input('registeredOfficeAddress1');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress1Input()).to.match(/registeredOfficeAddress1/);
    legalEntityUpdatePage.setRegisteredOfficeAddress2Input('registeredOfficeAddress2');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress2Input()).to.match(/registeredOfficeAddress2/);
    legalEntityUpdatePage.setRegisteredOfficeAddress3Input('registeredOfficeAddress3');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress3Input()).to.match(/registeredOfficeAddress3/);
    legalEntityUpdatePage.setRegisteredOfficeAddress4Input('registeredOfficeAddress4');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress4Input()).to.match(/registeredOfficeAddress4/);
    legalEntityUpdatePage.setRegisteredOfficeAddress5Input('registeredOfficeAddress5');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress5Input()).to.match(/registeredOfficeAddress5/);
    legalEntityUpdatePage.setCollegeIdentificationNumberInput('collegeIdentificationNumber');
    expect(await legalEntityUpdatePage.getCollegeIdentificationNumberInput()).to.match(/collegeIdentificationNumber/);
    legalEntityUpdatePage.setPanInput('pan');
    expect(await legalEntityUpdatePage.getPanInput()).to.match(/pan/);
    legalEntityUpdatePage.setTanInput('tan');
    expect(await legalEntityUpdatePage.getTanInput()).to.match(/tan/);
    legalEntityUpdatePage.setTanCircleNumberInput('tanCircleNumber');
    expect(await legalEntityUpdatePage.getTanCircleNumberInput()).to.match(/tanCircleNumber/);
    legalEntityUpdatePage.setCitTdsLocationInput('citTdsLocation');
    expect(await legalEntityUpdatePage.getCitTdsLocationInput()).to.match(/citTdsLocation/);
    legalEntityUpdatePage.setFormSignatoryInput('5');
    expect(await legalEntityUpdatePage.getFormSignatoryInput()).to.eq('5');
    legalEntityUpdatePage.setPfNumberInput('pfNumber');
    expect(await legalEntityUpdatePage.getPfNumberInput()).to.match(/pfNumber/);
    legalEntityUpdatePage.setPfRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPfRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setPfSignatoryInput('5');
    expect(await legalEntityUpdatePage.getPfSignatoryInput()).to.eq('5');
    legalEntityUpdatePage.setEsiNumberInput('5');
    expect(await legalEntityUpdatePage.getEsiNumberInput()).to.eq('5');
    legalEntityUpdatePage.setEsiRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getEsiRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setEsiSignatoryInput('5');
    expect(await legalEntityUpdatePage.getEsiSignatoryInput()).to.eq('5');
    legalEntityUpdatePage.setPtNumberInput('5');
    expect(await legalEntityUpdatePage.getPtNumberInput()).to.eq('5');
    legalEntityUpdatePage.setPtRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPtRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setPtSignatoryInput('5');
    expect(await legalEntityUpdatePage.getPtSignatoryInput()).to.eq('5');
    legalEntityUpdatePage.branchSelectLastOption();
    legalEntityUpdatePage.collegeSelectLastOption();
    legalEntityUpdatePage.stateSelectLastOption();
    legalEntityUpdatePage.citySelectLastOption();
    await legalEntityUpdatePage.save();
    expect(await legalEntityUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
