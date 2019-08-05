/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import VehicleComponentsPage from './vehicle.page-object';
import { VehicleDeleteDialog } from './vehicle.page-object';
import VehicleUpdatePage from './vehicle-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Vehicle e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let vehicleUpdatePage: VehicleUpdatePage;
  let vehicleComponentsPage: VehicleComponentsPage;
  let vehicleDeleteDialog: VehicleDeleteDialog;

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

  it('should load Vehicles', async () => {
    await navBarPage.getEntityPage('vehicle');
    vehicleComponentsPage = new VehicleComponentsPage();
    expect(await vehicleComponentsPage.getTitle().getText()).to.match(/Vehicles/);
  });

  it('should load create Vehicle page', async () => {
    await vehicleComponentsPage.clickOnCreateButton();
    vehicleUpdatePage = new VehicleUpdatePage();
    expect(await vehicleUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Vehicle/);
  });

  it('should create and save Vehicles', async () => {
    const nbButtonsBeforeCreate = await vehicleComponentsPage.countDeleteButtons();

    await vehicleUpdatePage.setVehicleNumberInput('5');
    expect(await vehicleUpdatePage.getVehicleNumberInput()).to.eq('5');
    await vehicleUpdatePage.setVehicleTypeInput('vehicleType');
    expect(await vehicleUpdatePage.getVehicleTypeInput()).to.match(/vehicleType/);
    await vehicleUpdatePage.setCapacityInput('5');
    expect(await vehicleUpdatePage.getCapacityInput()).to.eq('5');
    await vehicleUpdatePage.setOwnerShipInput('ownerShip');
    expect(await vehicleUpdatePage.getOwnerShipInput()).to.match(/ownerShip/);
    await vehicleUpdatePage.setDateOfRegistrationInput('01-01-2001');
    expect(await vehicleUpdatePage.getDateOfRegistrationInput()).to.eq('2001-01-01');
    await vehicleUpdatePage.setYearOfManufacturingInput('yearOfManufacturing');
    expect(await vehicleUpdatePage.getYearOfManufacturingInput()).to.match(/yearOfManufacturing/);
    await vehicleUpdatePage.setManufacturingCompanyInput('manufacturingCompany');
    expect(await vehicleUpdatePage.getManufacturingCompanyInput()).to.match(/manufacturingCompany/);
    await vehicleUpdatePage.setModelInput('model');
    expect(await vehicleUpdatePage.getModelInput()).to.match(/model/);
    await vehicleUpdatePage.setChasisNoInput('chasisNo');
    expect(await vehicleUpdatePage.getChasisNoInput()).to.match(/chasisNo/);
    await vehicleUpdatePage.setRcNoInput('rcNo');
    expect(await vehicleUpdatePage.getRcNoInput()).to.match(/rcNo/);
    await vehicleUpdatePage.setContactNumberInput('contactNumber');
    expect(await vehicleUpdatePage.getContactNumberInput()).to.match(/contactNumber/);
    await vehicleUpdatePage.statusSelectLastOption();
    await vehicleUpdatePage.employeeSelectLastOption();
    await vehicleUpdatePage.transportRouteSelectLastOption();
    await waitUntilDisplayed(vehicleUpdatePage.getSaveButton());
    await vehicleUpdatePage.save();
    await waitUntilHidden(vehicleUpdatePage.getSaveButton());
    expect(await vehicleUpdatePage.getSaveButton().isPresent()).to.be.false;

    await vehicleComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await vehicleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Vehicle', async () => {
    await vehicleComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await vehicleComponentsPage.countDeleteButtons();
    await vehicleComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    vehicleDeleteDialog = new VehicleDeleteDialog();
    expect(await vehicleDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.vehicle.delete.question/);
    await vehicleDeleteDialog.clickOnConfirmButton();

    await vehicleComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await vehicleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
