/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SectionComponentsPage from './section.page-object';
import { SectionDeleteDialog } from './section.page-object';
import SectionUpdatePage from './section-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Section e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sectionUpdatePage: SectionUpdatePage;
  let sectionComponentsPage: SectionComponentsPage;
  let sectionDeleteDialog: SectionDeleteDialog;

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

  it('should load Sections', async () => {
    await navBarPage.getEntityPage('section');
    sectionComponentsPage = new SectionComponentsPage();
    expect(await sectionComponentsPage.getTitle().getText()).to.match(/Sections/);
  });

  it('should load create Section page', async () => {
    await sectionComponentsPage.clickOnCreateButton();
    sectionUpdatePage = new SectionUpdatePage();
    expect(await sectionUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Section/);
  });

  it('should create and save Sections', async () => {
    const nbButtonsBeforeCreate = await sectionComponentsPage.countDeleteButtons();

    await sectionUpdatePage.sectionSelectLastOption();
    await sectionUpdatePage.batchSelectLastOption();
    await waitUntilDisplayed(sectionUpdatePage.getSaveButton());
    await sectionUpdatePage.save();
    await waitUntilHidden(sectionUpdatePage.getSaveButton());
    expect(await sectionUpdatePage.getSaveButton().isPresent()).to.be.false;

    await sectionComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await sectionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Section', async () => {
    await sectionComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await sectionComponentsPage.countDeleteButtons();
    await sectionComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    sectionDeleteDialog = new SectionDeleteDialog();
    expect(await sectionDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.section.delete.question/);
    await sectionDeleteDialog.clickOnConfirmButton();

    await sectionComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await sectionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
