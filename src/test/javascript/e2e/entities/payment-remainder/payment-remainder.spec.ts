/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import PaymentRemainderComponentsPage from './payment-remainder.page-object';
import PaymentRemainderUpdatePage from './payment-remainder-update.page-object';

const expect = chai.expect;

describe('PaymentRemainder e2e test', () => {
  let navBarPage: NavBarPage;
  let paymentRemainderUpdatePage: PaymentRemainderUpdatePage;
  let paymentRemainderComponentsPage: PaymentRemainderComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load PaymentRemainders', async () => {
    navBarPage.getEntityPage('payment-remainder');
    paymentRemainderComponentsPage = new PaymentRemainderComponentsPage();
    expect(await paymentRemainderComponentsPage.getTitle().getText()).to.match(/Payment Remainders/);
  });

  it('should load create PaymentRemainder page', async () => {
    paymentRemainderComponentsPage.clickOnCreateButton();
    paymentRemainderUpdatePage = new PaymentRemainderUpdatePage();
    expect(await paymentRemainderUpdatePage.getPageTitle().getText()).to.match(/Create or edit a PaymentRemainder/);
  });

  it('should create and save PaymentRemainders', async () => {
    paymentRemainderUpdatePage.feeRemainderSelectLastOption();
    paymentRemainderUpdatePage.setNoticeDayInput('5');
    expect(await paymentRemainderUpdatePage.getNoticeDayInput()).to.eq('5');
    paymentRemainderUpdatePage.overDueRemainderSelectLastOption();
    paymentRemainderUpdatePage.setRemainderRecipientsInput('remainderRecipients');
    expect(await paymentRemainderUpdatePage.getRemainderRecipientsInput()).to.match(/remainderRecipients/);
    paymentRemainderUpdatePage.dueDateSelectLastOption();
    paymentRemainderUpdatePage.collegeSelectLastOption();
    paymentRemainderUpdatePage.branchSelectLastOption();
    await paymentRemainderUpdatePage.save();
    expect(await paymentRemainderUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
