/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FeeDetailsComponentsPage from './fee-details.page-object';
import { FeeDetailsDeleteDialog } from './fee-details.page-object';
import FeeDetailsUpdatePage from './fee-details-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('FeeDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let feeDetailsUpdatePage: FeeDetailsUpdatePage;
  let feeDetailsComponentsPage: FeeDetailsComponentsPage;
  let feeDetailsDeleteDialog: FeeDetailsDeleteDialog;

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

  it('should load FeeDetails', async () => {
    await navBarPage.getEntityPage('fee-details');
    feeDetailsComponentsPage = new FeeDetailsComponentsPage();
    expect(await feeDetailsComponentsPage.getTitle().getText()).to.match(/Fee Details/);
  });

  it('should load create FeeDetails page', async () => {
    await feeDetailsComponentsPage.clickOnCreateButton();
    feeDetailsUpdatePage = new FeeDetailsUpdatePage();
    expect(await feeDetailsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a FeeDetails/);
  });

  it('should create and save FeeDetails', async () => {
    const nbButtonsBeforeCreate = await feeDetailsComponentsPage.countDeleteButtons();

    await feeDetailsUpdatePage.setFeeParticularsNameInput('feeParticularsName');
    expect(await feeDetailsUpdatePage.getFeeParticularsNameInput()).to.match(/feeParticularsName/);
    await feeDetailsUpdatePage.setFeeParticularDescInput('feeParticularDesc');
    expect(await feeDetailsUpdatePage.getFeeParticularDescInput()).to.match(/feeParticularDesc/);
    await feeDetailsUpdatePage.studentTypeSelectLastOption();
    await feeDetailsUpdatePage.genderSelectLastOption();
    await feeDetailsUpdatePage.setAmountInput('5');
    expect(await feeDetailsUpdatePage.getAmountInput()).to.eq('5');
    await feeDetailsUpdatePage.feeCategorySelectLastOption();
    await feeDetailsUpdatePage.batchSelectLastOption();
    await feeDetailsUpdatePage.facilitySelectLastOption();
    await feeDetailsUpdatePage.transportRouteSelectLastOption();
    await feeDetailsUpdatePage.collegeSelectLastOption();
    await feeDetailsUpdatePage.departmentSelectLastOption();
    await feeDetailsUpdatePage.branchSelectLastOption();
    await feeDetailsUpdatePage.academicYearSelectLastOption();
    await waitUntilDisplayed(feeDetailsUpdatePage.getSaveButton());
    await feeDetailsUpdatePage.save();
    await waitUntilHidden(feeDetailsUpdatePage.getSaveButton());
    expect(await feeDetailsUpdatePage.getSaveButton().isPresent()).to.be.false;

    await feeDetailsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await feeDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last FeeDetails', async () => {
    await feeDetailsComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await feeDetailsComponentsPage.countDeleteButtons();
    await feeDetailsComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    feeDetailsDeleteDialog = new FeeDetailsDeleteDialog();
    expect(await feeDetailsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.feeDetails.delete.question/);
    await feeDetailsDeleteDialog.clickOnConfirmButton();

    await feeDetailsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await feeDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
