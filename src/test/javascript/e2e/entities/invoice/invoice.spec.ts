/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import InvoiceComponentsPage from './invoice.page-object';
import { InvoiceDeleteDialog } from './invoice.page-object';
import InvoiceUpdatePage from './invoice-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Invoice e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invoiceUpdatePage: InvoiceUpdatePage;
  let invoiceComponentsPage: InvoiceComponentsPage;
  let invoiceDeleteDialog: InvoiceDeleteDialog;

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

  it('should load Invoices', async () => {
    await navBarPage.getEntityPage('invoice');
    invoiceComponentsPage = new InvoiceComponentsPage();
    expect(await invoiceComponentsPage.getTitle().getText()).to.match(/Invoices/);
  });

  it('should load create Invoice page', async () => {
    await invoiceComponentsPage.clickOnCreateButton();
    invoiceUpdatePage = new InvoiceUpdatePage();
    expect(await invoiceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Invoice/);
  });

  it('should create and save Invoices', async () => {
    const nbButtonsBeforeCreate = await invoiceComponentsPage.countDeleteButtons();

    await invoiceUpdatePage.setInvoiceNumberInput('invoiceNumber');
    expect(await invoiceUpdatePage.getInvoiceNumberInput()).to.match(/invoiceNumber/);
    await invoiceUpdatePage.setAmountPaidInput('5');
    expect(await invoiceUpdatePage.getAmountPaidInput()).to.eq('5');
    await invoiceUpdatePage.setPaymentDateInput('01-01-2001');
    expect(await invoiceUpdatePage.getPaymentDateInput()).to.eq('2001-01-01');
    await invoiceUpdatePage.setNextPaymentDateInput('01-01-2001');
    expect(await invoiceUpdatePage.getNextPaymentDateInput()).to.eq('2001-01-01');
    await invoiceUpdatePage.setOutStandingAmountInput('5');
    expect(await invoiceUpdatePage.getOutStandingAmountInput()).to.eq('5');
    await invoiceUpdatePage.modeOfPaymentSelectLastOption();
    await invoiceUpdatePage.setChequeNumberInput('5');
    expect(await invoiceUpdatePage.getChequeNumberInput()).to.eq('5');
    await invoiceUpdatePage.setDemandDraftNumberInput('5');
    expect(await invoiceUpdatePage.getDemandDraftNumberInput()).to.eq('5');
    await invoiceUpdatePage.setOnlineTxnRefNumberInput('onlineTxnRefNumber');
    expect(await invoiceUpdatePage.getOnlineTxnRefNumberInput()).to.match(/onlineTxnRefNumber/);
    await invoiceUpdatePage.paymentStatusSelectLastOption();
    await invoiceUpdatePage.setCommentsInput('comments');
    expect(await invoiceUpdatePage.getCommentsInput()).to.match(/comments/);
    await invoiceUpdatePage.setUpdatedByInput('updatedBy');
    expect(await invoiceUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    await invoiceUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await invoiceUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    await invoiceUpdatePage.feeCategorySelectLastOption();
    await invoiceUpdatePage.feeDetailsSelectLastOption();
    await invoiceUpdatePage.dueDateSelectLastOption();
    await invoiceUpdatePage.paymentRemainderSelectLastOption();
    await invoiceUpdatePage.collegeSelectLastOption();
    await invoiceUpdatePage.branchSelectLastOption();
    await invoiceUpdatePage.studentSelectLastOption();
    await invoiceUpdatePage.academicYearSelectLastOption();
    await waitUntilDisplayed(invoiceUpdatePage.getSaveButton());
    await invoiceUpdatePage.save();
    await waitUntilHidden(invoiceUpdatePage.getSaveButton());
    expect(await invoiceUpdatePage.getSaveButton().isPresent()).to.be.false;

    await invoiceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await invoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Invoice', async () => {
    await invoiceComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await invoiceComponentsPage.countDeleteButtons();
    await invoiceComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    invoiceDeleteDialog = new InvoiceDeleteDialog();
    expect(await invoiceDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.invoice.delete.question/);
    await invoiceDeleteDialog.clickOnConfirmButton();

    await invoiceComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await invoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
