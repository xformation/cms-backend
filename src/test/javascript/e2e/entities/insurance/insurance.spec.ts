/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import InsuranceComponentsPage from './insurance.page-object';
import { InsuranceDeleteDialog } from './insurance.page-object';
import InsuranceUpdatePage from './insurance-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Insurance e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let insuranceUpdatePage: InsuranceUpdatePage;
  let insuranceComponentsPage: InsuranceComponentsPage;
  let insuranceDeleteDialog: InsuranceDeleteDialog;

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

  it('should load Insurances', async () => {
    await navBarPage.getEntityPage('insurance');
    insuranceComponentsPage = new InsuranceComponentsPage();
    expect(await insuranceComponentsPage.getTitle().getText()).to.match(/Insurances/);
  });

  it('should load create Insurance page', async () => {
    await insuranceComponentsPage.clickOnCreateButton();
    insuranceUpdatePage = new InsuranceUpdatePage();
    expect(await insuranceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Insurance/);
  });

  it('should create and save Insurances', async () => {
    const nbButtonsBeforeCreate = await insuranceComponentsPage.countDeleteButtons();

    await insuranceUpdatePage.setInsuranceCompanyInput('insuranceCompany');
    expect(await insuranceUpdatePage.getInsuranceCompanyInput()).to.match(/insuranceCompany/);
    await insuranceUpdatePage.typeOfInsuranceSelectLastOption();
    await insuranceUpdatePage.setDateOfInsuranceInput('01-01-2001');
    expect(await insuranceUpdatePage.getDateOfInsuranceInput()).to.eq('2001-01-01');
    await insuranceUpdatePage.setValidTillInput('01-01-2001');
    expect(await insuranceUpdatePage.getValidTillInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(insuranceUpdatePage.getSaveButton());
    await insuranceUpdatePage.save();
    await waitUntilHidden(insuranceUpdatePage.getSaveButton());
    expect(await insuranceUpdatePage.getSaveButton().isPresent()).to.be.false;

    await insuranceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await insuranceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Insurance', async () => {
    await insuranceComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await insuranceComponentsPage.countDeleteButtons();
    await insuranceComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    insuranceDeleteDialog = new InsuranceDeleteDialog();
    expect(await insuranceDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.insurance.delete.question/);
    await insuranceDeleteDialog.clickOnConfirmButton();

    await insuranceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await insuranceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
