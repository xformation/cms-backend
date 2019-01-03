/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BankAccountsComponentsPage from './bank-accounts.page-object';
import BankAccountsUpdatePage from './bank-accounts-update.page-object';

const expect = chai.expect;

describe('BankAccounts e2e test', () => {
  let navBarPage: NavBarPage;
  let bankAccountsUpdatePage: BankAccountsUpdatePage;
  let bankAccountsComponentsPage: BankAccountsComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load BankAccounts', async () => {
    navBarPage.getEntityPage('bank-accounts');
    bankAccountsComponentsPage = new BankAccountsComponentsPage();
    expect(await bankAccountsComponentsPage.getTitle().getText()).to.match(/Bank Accounts/);
  });

  it('should load create BankAccounts page', async () => {
    bankAccountsComponentsPage.clickOnCreateButton();
    bankAccountsUpdatePage = new BankAccountsUpdatePage();
    expect(await bankAccountsUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.bankAccounts.home.createOrEditLabel/);
  });

  it('should create and save BankAccounts', async () => {
    bankAccountsUpdatePage.nameOfBankSelectLastOption();
    bankAccountsUpdatePage.setAccountNumberInput('5');
    expect(await bankAccountsUpdatePage.getAccountNumberInput()).to.eq('5');
    bankAccountsUpdatePage.setTypeOfAccountInput('typeOfAccount');
    expect(await bankAccountsUpdatePage.getTypeOfAccountInput()).to.match(/typeOfAccount/);
    bankAccountsUpdatePage.setIfsCodeInput('ifsCode');
    expect(await bankAccountsUpdatePage.getIfsCodeInput()).to.match(/ifsCode/);
    bankAccountsUpdatePage.setBranchInput('branch');
    expect(await bankAccountsUpdatePage.getBranchInput()).to.match(/branch/);
    bankAccountsUpdatePage.setCorporateIdInput('5');
    expect(await bankAccountsUpdatePage.getCorporateIdInput()).to.eq('5');
    await bankAccountsUpdatePage.save();
    expect(await bankAccountsUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
