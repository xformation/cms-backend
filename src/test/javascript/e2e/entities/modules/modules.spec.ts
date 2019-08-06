/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ModulesComponentsPage from './modules.page-object';
import { ModulesDeleteDialog } from './modules.page-object';
import ModulesUpdatePage from './modules-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Modules e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let modulesUpdatePage: ModulesUpdatePage;
  let modulesComponentsPage: ModulesComponentsPage;
  let modulesDeleteDialog: ModulesDeleteDialog;

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

  it('should load Modules', async () => {
    await navBarPage.getEntityPage('modules');
    modulesComponentsPage = new ModulesComponentsPage();
    expect(await modulesComponentsPage.getTitle().getText()).to.match(/Modules/);
  });

  it('should load create Modules page', async () => {
    await modulesComponentsPage.clickOnCreateButton();
    modulesUpdatePage = new ModulesUpdatePage();
    expect(await modulesUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Modules/);
  });

  it('should create and save Modules', async () => {
    const nbButtonsBeforeCreate = await modulesComponentsPage.countDeleteButtons();

    await modulesUpdatePage.setModuleNameInput('moduleName');
    expect(await modulesUpdatePage.getModuleNameInput()).to.match(/moduleName/);
    await modulesUpdatePage.setSubModuleNameInput('subModuleName');
    expect(await modulesUpdatePage.getSubModuleNameInput()).to.match(/subModuleName/);
    await modulesUpdatePage.setUrlInput('url');
    expect(await modulesUpdatePage.getUrlInput()).to.match(/url/);
    await modulesUpdatePage.statusSelectLastOption();
    await waitUntilDisplayed(modulesUpdatePage.getSaveButton());
    await modulesUpdatePage.save();
    await waitUntilHidden(modulesUpdatePage.getSaveButton());
    expect(await modulesUpdatePage.getSaveButton().isPresent()).to.be.false;

    await modulesComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await modulesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Modules', async () => {
    await modulesComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await modulesComponentsPage.countDeleteButtons();
    await modulesComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    modulesDeleteDialog = new ModulesDeleteDialog();
    expect(await modulesDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.modules.delete.question/);
    await modulesDeleteDialog.clickOnConfirmButton();

    await modulesComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await modulesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
