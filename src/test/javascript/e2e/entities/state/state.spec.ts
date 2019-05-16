/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import StateComponentsPage from './state.page-object';
import { StateDeleteDialog } from './state.page-object';
import StateUpdatePage from './state-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('State e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let stateUpdatePage: StateUpdatePage;
  let stateComponentsPage: StateComponentsPage;
  let stateDeleteDialog: StateDeleteDialog;

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

  it('should load States', async () => {
    await navBarPage.getEntityPage('state');
    stateComponentsPage = new StateComponentsPage();
    expect(await stateComponentsPage.getTitle().getText()).to.match(/States/);
  });

  it('should load create State page', async () => {
    await stateComponentsPage.clickOnCreateButton();
    stateUpdatePage = new StateUpdatePage();
    expect(await stateUpdatePage.getPageTitle().getText()).to.match(/Create or edit a State/);
  });

  it('should create and save States', async () => {
    const nbButtonsBeforeCreate = await stateComponentsPage.countDeleteButtons();

    await stateUpdatePage.setStateNameInput('stateName');
    expect(await stateUpdatePage.getStateNameInput()).to.match(/stateName/);
    await stateUpdatePage.setDivisionTypeInput('divisionType');
    expect(await stateUpdatePage.getDivisionTypeInput()).to.match(/divisionType/);
    await stateUpdatePage.setStateCodeInput('stateCode');
    expect(await stateUpdatePage.getStateCodeInput()).to.match(/stateCode/);
    await stateUpdatePage.countrySelectLastOption();
    await waitUntilDisplayed(stateUpdatePage.getSaveButton());
    await stateUpdatePage.save();
    await waitUntilHidden(stateUpdatePage.getSaveButton());
    expect(await stateUpdatePage.getSaveButton().isPresent()).to.be.false;

    await stateComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await stateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last State', async () => {
    await stateComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await stateComponentsPage.countDeleteButtons();
    await stateComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    stateDeleteDialog = new StateDeleteDialog();
    expect(await stateDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.state.delete.question/);
    await stateDeleteDialog.clickOnConfirmButton();

    await stateComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await stateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
