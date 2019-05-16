/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CityComponentsPage from './city.page-object';
import { CityDeleteDialog } from './city.page-object';
import CityUpdatePage from './city-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('City e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cityUpdatePage: CityUpdatePage;
  let cityComponentsPage: CityComponentsPage;
  let cityDeleteDialog: CityDeleteDialog;

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

  it('should load Cities', async () => {
    await navBarPage.getEntityPage('city');
    cityComponentsPage = new CityComponentsPage();
    expect(await cityComponentsPage.getTitle().getText()).to.match(/Cities/);
  });

  it('should load create City page', async () => {
    await cityComponentsPage.clickOnCreateButton();
    cityUpdatePage = new CityUpdatePage();
    expect(await cityUpdatePage.getPageTitle().getText()).to.match(/Create or edit a City/);
  });

  it('should create and save Cities', async () => {
    const nbButtonsBeforeCreate = await cityComponentsPage.countDeleteButtons();

    await cityUpdatePage.setCityNameInput('cityName');
    expect(await cityUpdatePage.getCityNameInput()).to.match(/cityName/);
    await cityUpdatePage.setCityCodeInput('cityCode');
    expect(await cityUpdatePage.getCityCodeInput()).to.match(/cityCode/);
    await cityUpdatePage.setStdCodeInput('stdCode');
    expect(await cityUpdatePage.getStdCodeInput()).to.match(/stdCode/);
    await cityUpdatePage.stateSelectLastOption();
    await waitUntilDisplayed(cityUpdatePage.getSaveButton());
    await cityUpdatePage.save();
    await waitUntilHidden(cityUpdatePage.getSaveButton());
    expect(await cityUpdatePage.getSaveButton().isPresent()).to.be.false;

    await cityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await cityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last City', async () => {
    await cityComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await cityComponentsPage.countDeleteButtons();
    await cityComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    cityDeleteDialog = new CityDeleteDialog();
    expect(await cityDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.city.delete.question/);
    await cityDeleteDialog.clickOnConfirmButton();

    await cityComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await cityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
