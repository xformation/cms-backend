/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PaymentRemainderComponentsPage from './payment-remainder.page-object';
import { PaymentRemainderDeleteDialog } from './payment-remainder.page-object';
import PaymentRemainderUpdatePage from './payment-remainder-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('PaymentRemainder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paymentRemainderUpdatePage: PaymentRemainderUpdatePage;
  let paymentRemainderComponentsPage: PaymentRemainderComponentsPage;
  let paymentRemainderDeleteDialog: PaymentRemainderDeleteDialog;

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

  it('should load PaymentRemainders', async () => {
    await navBarPage.getEntityPage('payment-remainder');
    paymentRemainderComponentsPage = new PaymentRemainderComponentsPage();
    expect(await paymentRemainderComponentsPage.getTitle().getText()).to.match(/Payment Remainders/);
  });

  it('should load create PaymentRemainder page', async () => {
    await paymentRemainderComponentsPage.clickOnCreateButton();
    paymentRemainderUpdatePage = new PaymentRemainderUpdatePage();
    expect(await paymentRemainderUpdatePage.getPageTitle().getText()).to.match(/Create or edit a PaymentRemainder/);
  });

  it('should create and save PaymentRemainders', async () => {
    const nbButtonsBeforeCreate = await paymentRemainderComponentsPage.countDeleteButtons();

    await paymentRemainderUpdatePage.feeRemainderSelectLastOption();
    await paymentRemainderUpdatePage.setNoticeDayInput('5');
    expect(await paymentRemainderUpdatePage.getNoticeDayInput()).to.eq('5');
    await paymentRemainderUpdatePage.overDueRemainderSelectLastOption();
    await paymentRemainderUpdatePage.setRemainderRecipientsInput('remainderRecipients');
    expect(await paymentRemainderUpdatePage.getRemainderRecipientsInput()).to.match(/remainderRecipients/);
    await paymentRemainderUpdatePage.dueDateSelectLastOption();
    await paymentRemainderUpdatePage.collegeSelectLastOption();
    await paymentRemainderUpdatePage.branchSelectLastOption();
    await waitUntilDisplayed(paymentRemainderUpdatePage.getSaveButton());
    await paymentRemainderUpdatePage.save();
    await waitUntilHidden(paymentRemainderUpdatePage.getSaveButton());
    expect(await paymentRemainderUpdatePage.getSaveButton().isPresent()).to.be.false;

    await paymentRemainderComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await paymentRemainderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last PaymentRemainder', async () => {
    await paymentRemainderComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await paymentRemainderComponentsPage.countDeleteButtons();
    await paymentRemainderComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    paymentRemainderDeleteDialog = new PaymentRemainderDeleteDialog();
    expect(await paymentRemainderDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.paymentRemainder.delete.question/);
    await paymentRemainderDeleteDialog.clickOnConfirmButton();

    await paymentRemainderComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await paymentRemainderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
