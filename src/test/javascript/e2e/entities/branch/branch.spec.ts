/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BranchComponentsPage from './branch.page-object';
import { BranchDeleteDialog } from './branch.page-object';
import BranchUpdatePage from './branch-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Branch e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let branchUpdatePage: BranchUpdatePage;
  let branchComponentsPage: BranchComponentsPage;
  let branchDeleteDialog: BranchDeleteDialog;

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

  it('should load Branches', async () => {
    await navBarPage.getEntityPage('branch');
    branchComponentsPage = new BranchComponentsPage();
    expect(await branchComponentsPage.getTitle().getText()).to.match(/Branches/);
  });

  it('should load create Branch page', async () => {
    await branchComponentsPage.clickOnCreateButton();
    branchUpdatePage = new BranchUpdatePage();
    expect(await branchUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Branch/);
  });

  it('should create and save Branches', async () => {
    const nbButtonsBeforeCreate = await branchComponentsPage.countDeleteButtons();

    await branchUpdatePage.setBranchNameInput('branchName');
    expect(await branchUpdatePage.getBranchNameInput()).to.match(/branchName/);
    await branchUpdatePage.setAddress1Input('address1');
    expect(await branchUpdatePage.getAddress1Input()).to.match(/address1/);
    await branchUpdatePage.setAddress2Input('address2');
    expect(await branchUpdatePage.getAddress2Input()).to.match(/address2/);
    await branchUpdatePage.setBranchHeadInput('branchHead');
    expect(await branchUpdatePage.getBranchHeadInput()).to.match(/branchHead/);
    await branchUpdatePage.collegeSelectLastOption();
    await branchUpdatePage.citySelectLastOption();
    await branchUpdatePage.stateSelectLastOption();
    await waitUntilDisplayed(branchUpdatePage.getSaveButton());
    await branchUpdatePage.save();
    await waitUntilHidden(branchUpdatePage.getSaveButton());
    expect(await branchUpdatePage.getSaveButton().isPresent()).to.be.false;

    await branchComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await branchComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Branch', async () => {
    await branchComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await branchComponentsPage.countDeleteButtons();
    await branchComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    branchDeleteDialog = new BranchDeleteDialog();
    expect(await branchDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.branch.delete.question/);
    await branchDeleteDialog.clickOnConfirmButton();

    await branchComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await branchComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
