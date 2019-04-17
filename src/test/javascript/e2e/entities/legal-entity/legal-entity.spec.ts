/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LegalEntityComponentsPage from './legal-entity.page-object';
import { LegalEntityDeleteDialog } from './legal-entity.page-object';
import LegalEntityUpdatePage from './legal-entity-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('LegalEntity e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let legalEntityUpdatePage: LegalEntityUpdatePage;
  let legalEntityComponentsPage: LegalEntityComponentsPage;
  let legalEntityDeleteDialog: LegalEntityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load LegalEntities', async () => {
    await navBarPage.getEntityPage('legal-entity');
    legalEntityComponentsPage = new LegalEntityComponentsPage();
    expect(await legalEntityComponentsPage.getTitle().getText()).to.match(/Legal Entities/);
  });

  it('should load create LegalEntity page', async () => {
    await legalEntityComponentsPage.clickOnCreateButton();
    legalEntityUpdatePage = new LegalEntityUpdatePage();
    expect(await legalEntityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a LegalEntity/);
  });

  it('should create and save LegalEntities', async () => {
    const nbButtonsBeforeCreate = await legalEntityComponentsPage.countDeleteButtons();

    await legalEntityUpdatePage.setLogoPathInput('logoPath');
    expect(await legalEntityUpdatePage.getLogoPathInput()).to.match(/logoPath/);
    await legalEntityUpdatePage.setLogoFileNameInput('logoFileName');
    expect(await legalEntityUpdatePage.getLogoFileNameInput()).to.match(/logoFileName/);
    await legalEntityUpdatePage.setLogoFileInput('logoFile');
    expect(await legalEntityUpdatePage.getLogoFileInput()).to.match(/logoFile/);
    await legalEntityUpdatePage.setLegalNameOfTheCollegeInput('legalNameOfTheCollege');
    expect(await legalEntityUpdatePage.getLegalNameOfTheCollegeInput()).to.match(/legalNameOfTheCollege/);
    await legalEntityUpdatePage.typeOfCollegeSelectLastOption();
    await legalEntityUpdatePage.setDateOfIncorporationInput('01-01-2001');
    expect(await legalEntityUpdatePage.getDateOfIncorporationInput()).to.eq('2001-01-01');
    await legalEntityUpdatePage.setRegisteredOfficeAddress1Input('registeredOfficeAddress1');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress1Input()).to.match(/registeredOfficeAddress1/);
    await legalEntityUpdatePage.setRegisteredOfficeAddress2Input('registeredOfficeAddress2');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress2Input()).to.match(/registeredOfficeAddress2/);
    await legalEntityUpdatePage.setRegisteredOfficeAddress3Input('registeredOfficeAddress3');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress3Input()).to.match(/registeredOfficeAddress3/);
    await legalEntityUpdatePage.setRegisteredOfficeAddress4Input('registeredOfficeAddress4');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress4Input()).to.match(/registeredOfficeAddress4/);
    await legalEntityUpdatePage.setRegisteredOfficeAddress5Input('registeredOfficeAddress5');
    expect(await legalEntityUpdatePage.getRegisteredOfficeAddress5Input()).to.match(/registeredOfficeAddress5/);
    await legalEntityUpdatePage.setCollegeIdentificationNumberInput('collegeIdentificationNumber');
    expect(await legalEntityUpdatePage.getCollegeIdentificationNumberInput()).to.match(/collegeIdentificationNumber/);
    await legalEntityUpdatePage.setPanInput('pan');
    expect(await legalEntityUpdatePage.getPanInput()).to.match(/pan/);
    await legalEntityUpdatePage.setTanInput('tan');
    expect(await legalEntityUpdatePage.getTanInput()).to.match(/tan/);
    await legalEntityUpdatePage.setTanCircleNumberInput('tanCircleNumber');
    expect(await legalEntityUpdatePage.getTanCircleNumberInput()).to.match(/tanCircleNumber/);
    await legalEntityUpdatePage.setCitTdsLocationInput('citTdsLocation');
    expect(await legalEntityUpdatePage.getCitTdsLocationInput()).to.match(/citTdsLocation/);
    await legalEntityUpdatePage.setFormSignatoryInput('5');
    expect(await legalEntityUpdatePage.getFormSignatoryInput()).to.eq('5');
    await legalEntityUpdatePage.setPfNumberInput('pfNumber');
    expect(await legalEntityUpdatePage.getPfNumberInput()).to.match(/pfNumber/);
    await legalEntityUpdatePage.setPfRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPfRegistrationDateInput()).to.eq('2001-01-01');
    await legalEntityUpdatePage.setPfSignatoryInput('5');
    expect(await legalEntityUpdatePage.getPfSignatoryInput()).to.eq('5');
    await legalEntityUpdatePage.setEsiNumberInput('esiNumber');
    expect(await legalEntityUpdatePage.getEsiNumberInput()).to.match(/esiNumber/);
    await legalEntityUpdatePage.setEsiRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getEsiRegistrationDateInput()).to.eq('2001-01-01');
    await legalEntityUpdatePage.setEsiSignatoryInput('5');
    expect(await legalEntityUpdatePage.getEsiSignatoryInput()).to.eq('5');
    await legalEntityUpdatePage.setPtNumberInput('ptNumber');
    expect(await legalEntityUpdatePage.getPtNumberInput()).to.match(/ptNumber/);
    await legalEntityUpdatePage.setPtRegistrationDateInput('01-01-2001');
    expect(await legalEntityUpdatePage.getPtRegistrationDateInput()).to.eq('2001-01-01');
    await legalEntityUpdatePage.setPtSignatoryInput('5');
    expect(await legalEntityUpdatePage.getPtSignatoryInput()).to.eq('5');
    await legalEntityUpdatePage.branchSelectLastOption();
    await legalEntityUpdatePage.collegeSelectLastOption();
    await legalEntityUpdatePage.stateSelectLastOption();
    await legalEntityUpdatePage.citySelectLastOption();
    await waitUntilDisplayed(legalEntityUpdatePage.getSaveButton());
    await legalEntityUpdatePage.save();
    await waitUntilHidden(legalEntityUpdatePage.getSaveButton());
    expect(await legalEntityUpdatePage.getSaveButton().isPresent()).to.be.false;

    await legalEntityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await legalEntityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last LegalEntity', async () => {
    await legalEntityComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await legalEntityComponentsPage.countDeleteButtons();
    await legalEntityComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    legalEntityDeleteDialog = new LegalEntityDeleteDialog();
    expect(await legalEntityDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.legalEntity.delete.question/);
    await legalEntityDeleteDialog.clickOnConfirmButton();

    await legalEntityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await legalEntityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
