/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BankAccountsComponentsPage from './bank-accounts.page-object';
import { BankAccountsDeleteDialog } from './bank-accounts.page-object';
import BankAccountsUpdatePage from './bank-accounts-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('BankAccounts e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let bankAccountsUpdatePage: BankAccountsUpdatePage;
  let bankAccountsComponentsPage: BankAccountsComponentsPage;
  let bankAccountsDeleteDialog: BankAccountsDeleteDialog;

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

  it('should load BankAccounts', async () => {
    await navBarPage.getEntityPage('bank-accounts');
    bankAccountsComponentsPage = new BankAccountsComponentsPage();
    expect(await bankAccountsComponentsPage.getTitle().getText()).to.match(/Bank Accounts/);
  });

  it('should load create BankAccounts page', async () => {
    await bankAccountsComponentsPage.clickOnCreateButton();
    bankAccountsUpdatePage = new BankAccountsUpdatePage();
    expect(await bankAccountsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a BankAccounts/);
  });

  it('should create and save BankAccounts', async () => {
    const nbButtonsBeforeCreate = await bankAccountsComponentsPage.countDeleteButtons();

    await bankAccountsUpdatePage.nameOfBankSelectLastOption();
    await bankAccountsUpdatePage.setAccountNumberInput('5');
    expect(await bankAccountsUpdatePage.getAccountNumberInput()).to.eq('5');
    await bankAccountsUpdatePage.setTypeOfAccountInput('typeOfAccount');
    expect(await bankAccountsUpdatePage.getTypeOfAccountInput()).to.match(/typeOfAccount/);
    await bankAccountsUpdatePage.setIfscCodeInput('ifscCode');
    expect(await bankAccountsUpdatePage.getIfscCodeInput()).to.match(/ifscCode/);
    await bankAccountsUpdatePage.setBranchAddressInput('branchAddress');
    expect(await bankAccountsUpdatePage.getBranchAddressInput()).to.match(/branchAddress/);
    await bankAccountsUpdatePage.setCorporateIdInput('5');
    expect(await bankAccountsUpdatePage.getCorporateIdInput()).to.eq('5');
    await bankAccountsUpdatePage.branchSelectLastOption();
    await bankAccountsUpdatePage.collegeSelectLastOption();
    await waitUntilDisplayed(bankAccountsUpdatePage.getSaveButton());
    await bankAccountsUpdatePage.save();
    await waitUntilHidden(bankAccountsUpdatePage.getSaveButton());
    expect(await bankAccountsUpdatePage.getSaveButton().isPresent()).to.be.false;

    await bankAccountsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await bankAccountsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last BankAccounts', async () => {
    await bankAccountsComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await bankAccountsComponentsPage.countDeleteButtons();
    await bankAccountsComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    bankAccountsDeleteDialog = new BankAccountsDeleteDialog();
    expect(await bankAccountsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.bankAccounts.delete.question/);
    await bankAccountsDeleteDialog.clickOnConfirmButton();

    await bankAccountsComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await bankAccountsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
