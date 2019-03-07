/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CurrencyComponentsPage from './currency.page-object';
import { CurrencyDeleteDialog } from './currency.page-object';
import CurrencyUpdatePage from './currency-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Currency e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let currencyUpdatePage: CurrencyUpdatePage;
  let currencyComponentsPage: CurrencyComponentsPage;
  let currencyDeleteDialog: CurrencyDeleteDialog;

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

  it('should load Currencies', async () => {
    await navBarPage.getEntityPage('currency');
    currencyComponentsPage = new CurrencyComponentsPage();
    expect(await currencyComponentsPage.getTitle().getText()).to.match(/Currencies/);
  });

  it('should load create Currency page', async () => {
    await currencyComponentsPage.clickOnCreateButton();
    currencyUpdatePage = new CurrencyUpdatePage();
    expect(await currencyUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Currency/);
  });

  it('should create and save Currencies', async () => {
    const nbButtonsBeforeCreate = await currencyComponentsPage.countDeleteButtons();

    await currencyUpdatePage.setCurrencyNameInput('currencyName');
    expect(await currencyUpdatePage.getCurrencyNameInput()).to.match(/currencyName/);
    await currencyUpdatePage.setCurrencyCodeInput('currencyCode');
    expect(await currencyUpdatePage.getCurrencyCodeInput()).to.match(/currencyCode/);
    await currencyUpdatePage.setCurrencySymbolInput('currencySymbol');
    expect(await currencyUpdatePage.getCurrencySymbolInput()).to.match(/currencySymbol/);
    await currencyUpdatePage.countrySelectLastOption();
    await waitUntilDisplayed(currencyUpdatePage.getSaveButton());
    await currencyUpdatePage.save();
    await waitUntilHidden(currencyUpdatePage.getSaveButton());
    expect(await currencyUpdatePage.getSaveButton().isPresent()).to.be.false;

    await currencyComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await currencyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Currency', async () => {
    await currencyComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await currencyComponentsPage.countDeleteButtons();
    await currencyComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    currencyDeleteDialog = new CurrencyDeleteDialog();
    expect(await currencyDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.currency.delete.question/);
    await currencyDeleteDialog.clickOnConfirmButton();

    await currencyComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await currencyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
