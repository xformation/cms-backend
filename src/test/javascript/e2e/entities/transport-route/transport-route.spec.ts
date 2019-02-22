/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TransportRouteComponentsPage from './transport-route.page-object';
import { TransportRouteDeleteDialog } from './transport-route.page-object';
import TransportRouteUpdatePage from './transport-route-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('TransportRoute e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transportRouteUpdatePage: TransportRouteUpdatePage;
  let transportRouteComponentsPage: TransportRouteComponentsPage;
  let transportRouteDeleteDialog: TransportRouteDeleteDialog;

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

  it('should load TransportRoutes', async () => {
    await navBarPage.getEntityPage('transport-route');
    transportRouteComponentsPage = new TransportRouteComponentsPage();
    expect(await transportRouteComponentsPage.getTitle().getText()).to.match(/Transport Routes/);
  });

  it('should load create TransportRoute page', async () => {
    await transportRouteComponentsPage.clickOnCreateButton();
    transportRouteUpdatePage = new TransportRouteUpdatePage();
    expect(await transportRouteUpdatePage.getPageTitle().getText()).to.match(/Create or edit a TransportRoute/);
  });

  it('should create and save TransportRoutes', async () => {
    const nbButtonsBeforeCreate = await transportRouteComponentsPage.countDeleteButtons();

    await transportRouteUpdatePage.setRouteNameInput('routeName');
    expect(await transportRouteUpdatePage.getRouteNameInput()).to.match(/routeName/);
    await transportRouteUpdatePage.setRouteDetailsInput('routeDetails');
    expect(await transportRouteUpdatePage.getRouteDetailsInput()).to.match(/routeDetails/);
    await transportRouteUpdatePage.setRouteMapUrlInput('routeMapUrl');
    expect(await transportRouteUpdatePage.getRouteMapUrlInput()).to.match(/routeMapUrl/);
    await waitUntilDisplayed(transportRouteUpdatePage.getSaveButton());
    await transportRouteUpdatePage.save();
    await waitUntilHidden(transportRouteUpdatePage.getSaveButton());
    expect(await transportRouteUpdatePage.getSaveButton().isPresent()).to.be.false;

    await transportRouteComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await transportRouteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last TransportRoute', async () => {
    await transportRouteComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await transportRouteComponentsPage.countDeleteButtons();
    await transportRouteComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    transportRouteDeleteDialog = new TransportRouteDeleteDialog();
    expect(await transportRouteDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.transportRoute.delete.question/);
    await transportRouteDeleteDialog.clickOnConfirmButton();

    await transportRouteComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await transportRouteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
