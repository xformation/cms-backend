/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TermComponentsPage from './term.page-object';
import { TermDeleteDialog } from './term.page-object';
import TermUpdatePage from './term-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Term e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let termUpdatePage: TermUpdatePage;
  let termComponentsPage: TermComponentsPage;
  let termDeleteDialog: TermDeleteDialog;

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

  it('should load Terms', async () => {
    await navBarPage.getEntityPage('term');
    termComponentsPage = new TermComponentsPage();
    expect(await termComponentsPage.getTitle().getText()).to.match(/Terms/);
  });

  it('should load create Term page', async () => {
    await termComponentsPage.clickOnCreateButton();
    termUpdatePage = new TermUpdatePage();
    expect(await termUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Term/);
  });

  it('should create and save Terms', async () => {
    const nbButtonsBeforeCreate = await termComponentsPage.countDeleteButtons();

    await termUpdatePage.setTermsDescInput('termsDesc');
    expect(await termUpdatePage.getTermsDescInput()).to.match(/termsDesc/);
    await termUpdatePage.setStartDateInput('01-01-2001');
    expect(await termUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    await termUpdatePage.setEndDateInput('01-01-2001');
    expect(await termUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    await termUpdatePage.termStatusSelectLastOption();
    await termUpdatePage.academicyearSelectLastOption();
    await waitUntilDisplayed(termUpdatePage.getSaveButton());
    await termUpdatePage.save();
    await waitUntilHidden(termUpdatePage.getSaveButton());
    expect(await termUpdatePage.getSaveButton().isPresent()).to.be.false;

    await termComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await termComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Term', async () => {
    await termComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await termComponentsPage.countDeleteButtons();
    await termComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    termDeleteDialog = new TermDeleteDialog();
    expect(await termDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.term.delete.question/);
    await termDeleteDialog.clickOnConfirmButton();

    await termComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await termComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
