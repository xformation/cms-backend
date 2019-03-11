/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import InvoiceComponentsPage from './invoice.page-object';
import InvoiceUpdatePage from './invoice-update.page-object';

const expect = chai.expect;

describe('Invoice e2e test', () => {
  let navBarPage: NavBarPage;
  let invoiceUpdatePage: InvoiceUpdatePage;
  let invoiceComponentsPage: InvoiceComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Invoices', async () => {
    navBarPage.getEntityPage('invoice');
    invoiceComponentsPage = new InvoiceComponentsPage();
    expect(await invoiceComponentsPage.getTitle().getText()).to.match(/Invoices/);
  });

  it('should load create Invoice page', async () => {
    invoiceComponentsPage.clickOnCreateButton();
    invoiceUpdatePage = new InvoiceUpdatePage();
    expect(await invoiceUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Invoice/);
  });

  it('should create and save Invoices', async () => {
    invoiceUpdatePage.setInvoiceNumberInput('invoiceNumber');
    expect(await invoiceUpdatePage.getInvoiceNumberInput()).to.match(/invoiceNumber/);
    invoiceUpdatePage.setAmountPaidInput('5');
    expect(await invoiceUpdatePage.getAmountPaidInput()).to.eq('5');
    invoiceUpdatePage.setPaymentDateInput('01-01-2001');
    expect(await invoiceUpdatePage.getPaymentDateInput()).to.eq('2001-01-01');
    invoiceUpdatePage.setNextPaymentDateInput('01-01-2001');
    expect(await invoiceUpdatePage.getNextPaymentDateInput()).to.eq('2001-01-01');
    invoiceUpdatePage.setOutStandingAmountInput('5');
    expect(await invoiceUpdatePage.getOutStandingAmountInput()).to.eq('5');
    invoiceUpdatePage.modeOfPaymentSelectLastOption();
    invoiceUpdatePage.setChequeNumberInput('5');
    expect(await invoiceUpdatePage.getChequeNumberInput()).to.eq('5');
    invoiceUpdatePage.setDemandDraftNumberInput('5');
    expect(await invoiceUpdatePage.getDemandDraftNumberInput()).to.eq('5');
    invoiceUpdatePage.setOnlineTxnRefNumberInput('onlineTxnRefNumber');
    expect(await invoiceUpdatePage.getOnlineTxnRefNumberInput()).to.match(/onlineTxnRefNumber/);
    invoiceUpdatePage.paymentStatusSelectLastOption();
    invoiceUpdatePage.setCommentsInput('comments');
    expect(await invoiceUpdatePage.getCommentsInput()).to.match(/comments/);
    invoiceUpdatePage.setUpdatedByInput('updatedBy');
    expect(await invoiceUpdatePage.getUpdatedByInput()).to.match(/updatedBy/);
    invoiceUpdatePage.setUpdatedOnInput('01-01-2001');
    expect(await invoiceUpdatePage.getUpdatedOnInput()).to.eq('2001-01-01');
    invoiceUpdatePage.feeCategorySelectLastOption();
    invoiceUpdatePage.feeDetailsSelectLastOption();
    invoiceUpdatePage.dueDateSelectLastOption();
    invoiceUpdatePage.paymentRemainderSelectLastOption();
    invoiceUpdatePage.collegeSelectLastOption();
    invoiceUpdatePage.branchSelectLastOption();
    invoiceUpdatePage.studentSelectLastOption();
    invoiceUpdatePage.academicYearSelectLastOption();
    await invoiceUpdatePage.save();
    expect(await invoiceUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
