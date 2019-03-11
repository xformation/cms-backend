/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import LateFeeComponentsPage from './late-fee.page-object';
import LateFeeUpdatePage from './late-fee-update.page-object';

const expect = chai.expect;

describe('LateFee e2e test', () => {
  let navBarPage: NavBarPage;
  let lateFeeUpdatePage: LateFeeUpdatePage;
  let lateFeeComponentsPage: LateFeeComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load LateFees', async () => {
    navBarPage.getEntityPage('late-fee');
    lateFeeComponentsPage = new LateFeeComponentsPage();
    expect(await lateFeeComponentsPage.getTitle().getText()).to.match(/Late Fees/);
  });

  it('should load create LateFee page', async () => {
    lateFeeComponentsPage.clickOnCreateButton();
    lateFeeUpdatePage = new LateFeeUpdatePage();
    expect(await lateFeeUpdatePage.getPageTitle().getText()).to.match(/Create or edit a LateFee/);
  });

  it('should create and save LateFees', async () => {
    lateFeeUpdatePage.assignLateFeeSelectLastOption();
    lateFeeUpdatePage.setLateFeeDaysInput('5');
    expect(await lateFeeUpdatePage.getLateFeeDaysInput()).to.eq('5');
    lateFeeUpdatePage.fixedSelectLastOption();
    lateFeeUpdatePage.percentageSelectLastOption();
    lateFeeUpdatePage.setFixedChargesInput('5');
    expect(await lateFeeUpdatePage.getFixedChargesInput()).to.eq('5');
    lateFeeUpdatePage.setPercentChargesInput('5');
    expect(await lateFeeUpdatePage.getPercentChargesInput()).to.eq('5');
    lateFeeUpdatePage.lateFeeAssignmentFrequencySelectLastOption();
    lateFeeUpdatePage.collegeSelectLastOption();
    lateFeeUpdatePage.branchSelectLastOption();
    await lateFeeUpdatePage.save();
    expect(await lateFeeUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
