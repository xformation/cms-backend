/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import StudentFeeComponentsPage from './student-fee.page-object';
import { StudentFeeDeleteDialog } from './student-fee.page-object';
import StudentFeeUpdatePage from './student-fee-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('StudentFee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studentFeeUpdatePage: StudentFeeUpdatePage;
  let studentFeeComponentsPage: StudentFeeComponentsPage;
  let studentFeeDeleteDialog: StudentFeeDeleteDialog;

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

  it('should load StudentFees', async () => {
    await navBarPage.getEntityPage('student-fee');
    studentFeeComponentsPage = new StudentFeeComponentsPage();
    expect(await studentFeeComponentsPage.getTitle().getText()).to.match(/Student Fees/);
  });

  it('should load create StudentFee page', async () => {
    await studentFeeComponentsPage.clickOnCreateButton();
    studentFeeUpdatePage = new StudentFeeUpdatePage();
    expect(await studentFeeUpdatePage.getPageTitle().getText()).to.match(/Create or edit a StudentFee/);
  });

  it('should create and save StudentFees', async () => {
    const nbButtonsBeforeCreate = await studentFeeComponentsPage.countDeleteButtons();

    await studentFeeUpdatePage.setTotalFeeInput('totalFee');
    expect(await studentFeeUpdatePage.getTotalFeeInput()).to.match(/totalFee/);
    await studentFeeUpdatePage.setFeesPaidInput('feesPaid');
    expect(await studentFeeUpdatePage.getFeesPaidInput()).to.match(/feesPaid/);
    await studentFeeUpdatePage.setFeesDueInput('feesDue');
    expect(await studentFeeUpdatePage.getFeesDueInput()).to.match(/feesDue/);
    await studentFeeUpdatePage.setDueDateInput('01-01-2001');
    expect(await studentFeeUpdatePage.getDueDateInput()).to.eq('2001-01-01');
    await studentFeeUpdatePage.setTotalRemainingInput('totalRemaining');
    expect(await studentFeeUpdatePage.getTotalRemainingInput()).to.match(/totalRemaining/);
    await waitUntilDisplayed(studentFeeUpdatePage.getSaveButton());
    await studentFeeUpdatePage.save();
    await waitUntilHidden(studentFeeUpdatePage.getSaveButton());
    expect(await studentFeeUpdatePage.getSaveButton().isPresent()).to.be.false;

    await studentFeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await studentFeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last StudentFee', async () => {
    await studentFeeComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await studentFeeComponentsPage.countDeleteButtons();
    await studentFeeComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    studentFeeDeleteDialog = new StudentFeeDeleteDialog();
    expect(await studentFeeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.studentFee.delete.question/);
    await studentFeeDeleteDialog.clickOnConfirmButton();

    await studentFeeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await studentFeeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
