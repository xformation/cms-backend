/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import FeeDetailsComponentsPage from './fee-details.page-object';
import FeeDetailsUpdatePage from './fee-details-update.page-object';

const expect = chai.expect;

describe('FeeDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let feeDetailsUpdatePage: FeeDetailsUpdatePage;
  let feeDetailsComponentsPage: FeeDetailsComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load FeeDetails', async () => {
    navBarPage.getEntityPage('fee-details');
    feeDetailsComponentsPage = new FeeDetailsComponentsPage();
    expect(await feeDetailsComponentsPage.getTitle().getText()).to.match(/Fee Details/);
  });

  it('should load create FeeDetails page', async () => {
    feeDetailsComponentsPage.clickOnCreateButton();
    feeDetailsUpdatePage = new FeeDetailsUpdatePage();
    expect(await feeDetailsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a FeeDetails/);
  });

  it('should create and save FeeDetails', async () => {
    feeDetailsUpdatePage.setFeeParticularsNameInput('feeParticularsName');
    expect(await feeDetailsUpdatePage.getFeeParticularsNameInput()).to.match(/feeParticularsName/);
    feeDetailsUpdatePage.setFeeParticularDescInput('feeParticularDesc');
    expect(await feeDetailsUpdatePage.getFeeParticularDescInput()).to.match(/feeParticularDesc/);
    feeDetailsUpdatePage.studentTypeSelectLastOption();
    feeDetailsUpdatePage.genderSelectLastOption();
    feeDetailsUpdatePage.setAmountInput('5');
    expect(await feeDetailsUpdatePage.getAmountInput()).to.eq('5');
    feeDetailsUpdatePage.feeCategorySelectLastOption();
    feeDetailsUpdatePage.batchSelectLastOption();
    feeDetailsUpdatePage.facilitySelectLastOption();
    feeDetailsUpdatePage.transportRouteSelectLastOption();
    feeDetailsUpdatePage.collegeSelectLastOption();
    feeDetailsUpdatePage.departmentSelectLastOption();
    feeDetailsUpdatePage.branchSelectLastOption();
    feeDetailsUpdatePage.academicYearSelectLastOption();
    await feeDetailsUpdatePage.save();
    expect(await feeDetailsUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
