/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LibraryComponentsPage from './library.page-object';
import { LibraryDeleteDialog } from './library.page-object';
import LibraryUpdatePage from './library-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Library e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let libraryUpdatePage: LibraryUpdatePage;
  let libraryComponentsPage: LibraryComponentsPage;
  let libraryDeleteDialog: LibraryDeleteDialog;

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

  it('should load Libraries', async () => {
    await navBarPage.getEntityPage('library');
    libraryComponentsPage = new LibraryComponentsPage();
    expect(await libraryComponentsPage.getTitle().getText()).to.match(/Libraries/);
  });

  it('should load create Library page', async () => {
    await libraryComponentsPage.clickOnCreateButton();
    libraryUpdatePage = new LibraryUpdatePage();
    expect(await libraryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Library/);
  });

  it('should create and save Libraries', async () => {
    const nbButtonsBeforeCreate = await libraryComponentsPage.countDeleteButtons();

    await libraryUpdatePage.setBookTitleInput('bookTitle');
    expect(await libraryUpdatePage.getBookTitleInput()).to.match(/bookTitle/);
    await libraryUpdatePage.setAuthorInput('author');
    expect(await libraryUpdatePage.getAuthorInput()).to.match(/author/);
    await libraryUpdatePage.setNoOfCopiesInput('5');
    expect(await libraryUpdatePage.getNoOfCopiesInput()).to.eq('5');
    await libraryUpdatePage.setBookNoInput('5');
    expect(await libraryUpdatePage.getBookNoInput()).to.eq('5');
    await libraryUpdatePage.setAdditionalInfoInput('additionalInfo');
    expect(await libraryUpdatePage.getAdditionalInfoInput()).to.match(/additionalInfo/);
    await libraryUpdatePage.setUniqueNoInput('5');
    expect(await libraryUpdatePage.getUniqueNoInput()).to.eq('5');
    await libraryUpdatePage.batchSelectLastOption();
    await libraryUpdatePage.subjectSelectLastOption();
    await waitUntilDisplayed(libraryUpdatePage.getSaveButton());
    await libraryUpdatePage.save();
    await waitUntilHidden(libraryUpdatePage.getSaveButton());
    expect(await libraryUpdatePage.getSaveButton().isPresent()).to.be.false;

    await libraryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await libraryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Library', async () => {
    await libraryComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await libraryComponentsPage.countDeleteButtons();
    await libraryComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    libraryDeleteDialog = new LibraryDeleteDialog();
    expect(await libraryDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.library.delete.question/);
    await libraryDeleteDialog.clickOnConfirmButton();

    await libraryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await libraryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
