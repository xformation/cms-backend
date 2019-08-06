/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ContractComponentsPage from './contract.page-object';
import { ContractDeleteDialog } from './contract.page-object';
import ContractUpdatePage from './contract-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Contract e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let contractUpdatePage: ContractUpdatePage;
  let contractComponentsPage: ContractComponentsPage;
  let contractDeleteDialog: ContractDeleteDialog;

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

  it('should load Contracts', async () => {
    await navBarPage.getEntityPage('contract');
    contractComponentsPage = new ContractComponentsPage();
    expect(await contractComponentsPage.getTitle().getText()).to.match(/Contracts/);
  });

  it('should load create Contract page', async () => {
    await contractComponentsPage.clickOnCreateButton();
    contractUpdatePage = new ContractUpdatePage();
    expect(await contractUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Contract/);
  });

  it('should create and save Contracts', async () => {
    const nbButtonsBeforeCreate = await contractComponentsPage.countDeleteButtons();

    await contractUpdatePage.setVendorNameInput('vendorName');
    expect(await contractUpdatePage.getVendorNameInput()).to.match(/vendorName/);
    await contractUpdatePage.typeOfOwnerShipSelectLastOption();
    await contractUpdatePage.setDurationOfContractInput('durationOfContract');
    expect(await contractUpdatePage.getDurationOfContractInput()).to.match(/durationOfContract/);
    await contractUpdatePage.setStartDateInput('01-01-2001');
    expect(await contractUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    await contractUpdatePage.setEndDateInput('01-01-2001');
    expect(await contractUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(contractUpdatePage.getSaveButton());
    await contractUpdatePage.save();
    await waitUntilHidden(contractUpdatePage.getSaveButton());
    expect(await contractUpdatePage.getSaveButton().isPresent()).to.be.false;

    await contractComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await contractComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Contract', async () => {
    await contractComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await contractComponentsPage.countDeleteButtons();
    await contractComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    contractDeleteDialog = new ContractDeleteDialog();
    expect(await contractDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.contract.delete.question/);
    await contractDeleteDialog.clickOnConfirmButton();

    await contractComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await contractComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
