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
    legalEntityUpdatePage.setRegisteredOfficeAddressInput('registeredOfficeAddress');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddressInput()).to.match(/registeredOfficeAddress/);
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
    legalEntityUpdatePage.setFormSignatoryInput('formSignatory');
    expect(await legalEntityUpdatePage.getFormSignatoryInput()).to.match(/formSignatory/);
    legalEntityUpdatePage.setPfNumberInput('pfNumber');
    expect(await legalEntityUpdatePage.getPfNumberInput()).to.match(/pfNumber/);
    legalEntityUpdatePage.setPfRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPfRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setPfSignatoryInput('pfSignatory');
    expect(await legalEntityUpdatePage.getPfSignatoryInput()).to.match(/pfSignatory/);
    legalEntityUpdatePage.setPfSignatoryDesignationInput('pfSignatoryDesignation');
    expect(await legalEntityUpdatePage.getPfSignatoryDesignationInput()).to.match(/pfSignatoryDesignation/);
    legalEntityUpdatePage.setPfSignatoryFatherNameInput('pfSignatoryFatherName');
    expect(await legalEntityUpdatePage.getPfSignatoryFatherNameInput()).to.match(/pfSignatoryFatherName/);
    legalEntityUpdatePage.setEsiNumberInput('5');
    expect(await legalEntityUpdatePage.getEsiNumberInput()).to.eq('5');
    legalEntityUpdatePage.setEsiRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getEsiRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setEsiSignatoryInput('esiSignatory');
    expect(await legalEntityUpdatePage.getEsiSignatoryInput()).to.match(/esiSignatory/);
    legalEntityUpdatePage.setEsiSignatoryDesignationInput('esiSignatoryDesignation');
    expect(await legalEntityUpdatePage.getEsiSignatoryDesignationInput()).to.match(/esiSignatoryDesignation/);
    legalEntityUpdatePage.setEsiSignatoryFatherNameInput('esiSignatoryFatherName');
    expect(await legalEntityUpdatePage.getEsiSignatoryFatherNameInput()).to.match(/esiSignatoryFatherName/);
    legalEntityUpdatePage.setPtNumberInput('5');
    expect(await legalEntityUpdatePage.getPtNumberInput()).to.eq('5');
    legalEntityUpdatePage.setPtRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPtRegistrationDateInput()).to.eq('2001-01-01');
    legalEntityUpdatePage.setPtSignatoryInput('ptSignatory');
    expect(await legalEntityUpdatePage.getPtSignatoryInput()).to.match(/ptSignatory/);
    await legalEntityUpdatePage.save();
    expect(await legalEntityUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
