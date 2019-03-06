/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FeeCategoryComponentsPage from './fee-category.page-object';
import { FeeCategoryDeleteDialog } from './fee-category.page-object';
import FeeCategoryUpdatePage from './fee-category-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('FeeCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let feeCategoryUpdatePage: FeeCategoryUpdatePage;
  let feeCategoryComponentsPage: FeeCategoryComponentsPage;
  let feeCategoryDeleteDialog: FeeCategoryDeleteDialog;

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

  it('should load FeeCategories', async () => {
    await navBarPage.getEntityPage('fee-category');
    feeCategoryComponentsPage = new FeeCategoryComponentsPage();
    expect(await feeCategoryComponentsPage.getTitle().getText()).to.match(/Fee Categories/);
  });

  it('should load create FeeCategory page', async () => {
    await feeCategoryComponentsPage.clickOnCreateButton();
    feeCategoryUpdatePage = new FeeCategoryUpdatePage();
    expect(await feeCategoryUpdatePage.getPageTitle().getText()).to.match(/Create or edit a FeeCategory/);
  });

  it('should create and save FeeCategories', async () => {
    const nbButtonsBeforeCreate = await feeCategoryComponentsPage.countDeleteButtons();

    await feeCategoryUpdatePage.setCategoryNameInput('categoryName');
    expect(await feeCategoryUpdatePage.getCategoryNameInput()).to.match(/categoryName/);
    await feeCategoryUpdatePage.setDescriptionInput('description');
    expect(await feeCategoryUpdatePage.getDescriptionInput()).to.match(/description/);
    await waitUntilDisplayed(feeCategoryUpdatePage.getSaveButton());
    await feeCategoryUpdatePage.save();
    await waitUntilHidden(feeCategoryUpdatePage.getSaveButton());
    expect(await feeCategoryUpdatePage.getSaveButton().isPresent()).to.be.false;

    await feeCategoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await feeCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last FeeCategory', async () => {
    await feeCategoryComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await feeCategoryComponentsPage.countDeleteButtons();
    await feeCategoryComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    feeCategoryDeleteDialog = new FeeCategoryDeleteDialog();
    expect(await feeCategoryDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.feeCategory.delete.question/);
    await feeCategoryDeleteDialog.clickOnConfirmButton();

    await feeCategoryComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await feeCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
