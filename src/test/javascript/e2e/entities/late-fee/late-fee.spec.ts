/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LateFeeComponentsPage from './late-fee.page-object';
import { LateFeeDeleteDialog } from './late-fee.page-object';
import LateFeeUpdatePage from './late-fee-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('LateFee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lateFeeUpdatePage: LateFeeUpdatePage;
  let lateFeeComponentsPage: LateFeeComponentsPage;
  let lateFeeDeleteDialog: LateFeeDeleteDialog;

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

  it('should load LateFees', async () => {
    await navBarPage.getEntityPage('late-fee');
    lateFeeComponentsPage = new LateFeeComponentsPage();
    expect(await lateFeeComponentsPage.getTitle().getText()).to.match(/Late Fees/);
  });

  it('should load create LateFee page', async () => {
    await lateFeeComponentsPage.clickOnCreateButton();
    lateFeeUpdatePage = new LateFeeUpdatePage();
    expect(await lateFeeUpdatePage.getPageTitle().getText()).to.match(/Create or edit a LateFee/);
  });

  it('should create and save LateFees', async () => {
    const nbButtonsBeforeCreate = await lateFeeComponentsPage.countDeleteButtons();

    await lateFeeUpdatePage.assignLateFeeSelectLastOption();
    await lateFeeUpdatePage.setLateFeeDaysInput('5');
    expect(await lateFeeUpdatePage.getLateFeeDaysInput()).to.eq('5');
    await lateFeeUpdatePage.fixedSelectLastOption();
    await lateFeeUpdatePage.percentageSelectLastOption();
    await lateFeeUpdatePage.setFixedChargesInput('5');
    expect(await lateFeeUpdatePage.getFixedChargesInput()).to.eq('5');
    await lateFeeUpdatePage.setPercentChargesInput('5');
    expect(await lateFeeUpdatePage.getPercentChargesInput()).to.eq('5');
    await lateFeeUpdatePage.lateFeeAssignmentFrequencySelectLastOption();
    await lateFeeUpdatePage.collegeSelectLastOption();
    await lateFeeUpdatePage.branchSelectLastOption();
    await waitUntilDisplayed(lateFeeUpdatePage.getSaveButton());
    await lateFeeUpdatePage.save();
    await waitUntilHidden(lateFeeUpdatePage.getSaveButton());
    expect(await lateFeeUpdatePage.getSaveButton().isPresent()).to.be.false;

    await lateFeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await lateFeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last LateFee', async () => {
    await lateFeeComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await lateFeeComponentsPage.countDeleteButtons();
    await lateFeeComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    lateFeeDeleteDialog = new LateFeeDeleteDialog();
    expect(await lateFeeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.lateFee.delete.question/);
    await lateFeeDeleteDialog.clickOnConfirmButton();

    await lateFeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await lateFeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
