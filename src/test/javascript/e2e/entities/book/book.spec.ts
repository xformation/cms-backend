/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BookComponentsPage from './book.page-object';
import { BookDeleteDialog } from './book.page-object';
import BookUpdatePage from './book-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Book e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let bookUpdatePage: BookUpdatePage;
  let bookComponentsPage: BookComponentsPage;
  let bookDeleteDialog: BookDeleteDialog;

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

  it('should load Books', async () => {
    await navBarPage.getEntityPage('book');
    bookComponentsPage = new BookComponentsPage();
    expect(await bookComponentsPage.getTitle().getText()).to.match(/Books/);
  });

  it('should load create Book page', async () => {
    await bookComponentsPage.clickOnCreateButton();
    bookUpdatePage = new BookUpdatePage();
    expect(await bookUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Book/);
  });

  it('should create and save Books', async () => {
    const nbButtonsBeforeCreate = await bookComponentsPage.countDeleteButtons();

    await bookUpdatePage.setIssueDateInput('01-01-2001');
    expect(await bookUpdatePage.getIssueDateInput()).to.eq('2001-01-01');
    await bookUpdatePage.setDueDateInput('01-01-2001');
    expect(await bookUpdatePage.getDueDateInput()).to.eq('2001-01-01');
    await bookUpdatePage.setNoOfCopiesAvailableInput('5');
    expect(await bookUpdatePage.getNoOfCopiesAvailableInput()).to.eq('5');
    await bookUpdatePage.statusSelectLastOption();
    await bookUpdatePage.setReceivedDateInput('01-01-2001');
    expect(await bookUpdatePage.getReceivedDateInput()).to.eq('2001-01-01');
    await bookUpdatePage.studentSelectLastOption();
    await bookUpdatePage.librarySelectLastOption();
    await waitUntilDisplayed(bookUpdatePage.getSaveButton());
    await bookUpdatePage.save();
    await waitUntilHidden(bookUpdatePage.getSaveButton());
    expect(await bookUpdatePage.getSaveButton().isPresent()).to.be.false;

    await bookComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await bookComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Book', async () => {
    await bookComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await bookComponentsPage.countDeleteButtons();
    await bookComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    bookDeleteDialog = new BookDeleteDialog();
    expect(await bookDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.book.delete.question/);
    await bookDeleteDialog.clickOnConfirmButton();

    await bookComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await bookComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
