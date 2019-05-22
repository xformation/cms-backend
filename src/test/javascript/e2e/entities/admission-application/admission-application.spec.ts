/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AdmissionApplicationComponentsPage from './admission-application.page-object';
import { AdmissionApplicationDeleteDialog } from './admission-application.page-object';
import AdmissionApplicationUpdatePage from './admission-application-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AdmissionApplication e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let admissionApplicationUpdatePage: AdmissionApplicationUpdatePage;
  let admissionApplicationComponentsPage: AdmissionApplicationComponentsPage;
  let admissionApplicationDeleteDialog: AdmissionApplicationDeleteDialog;

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

  it('should load AdmissionApplications', async () => {
    await navBarPage.getEntityPage('admission-application');
    admissionApplicationComponentsPage = new AdmissionApplicationComponentsPage();
    expect(await admissionApplicationComponentsPage.getTitle().getText()).to.match(/Admission Applications/);
  });

  it('should load create AdmissionApplication page', async () => {
    await admissionApplicationComponentsPage.clickOnCreateButton();
    admissionApplicationUpdatePage = new AdmissionApplicationUpdatePage();
    expect(await admissionApplicationUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AdmissionApplication/);
  });

  it('should create and save AdmissionApplications', async () => {
    const nbButtonsBeforeCreate = await admissionApplicationComponentsPage.countDeleteButtons();

    await admissionApplicationUpdatePage.admissionStatusSelectLastOption();
    await admissionApplicationUpdatePage.courseSelectLastOption();
    await admissionApplicationUpdatePage.setDateInput('01-01-2001');
    expect(await admissionApplicationUpdatePage.getDateInput()).to.eq('2001-01-01');
    await admissionApplicationUpdatePage.setCommentsInput('comments');
    expect(await admissionApplicationUpdatePage.getCommentsInput()).to.match(/comments/);
    await admissionApplicationUpdatePage.studentSelectLastOption();
    await waitUntilDisplayed(admissionApplicationUpdatePage.getSaveButton());
    await admissionApplicationUpdatePage.save();
    await waitUntilHidden(admissionApplicationUpdatePage.getSaveButton());
    expect(await admissionApplicationUpdatePage.getSaveButton().isPresent()).to.be.false;

    await admissionApplicationComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await admissionApplicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AdmissionApplication', async () => {
    await admissionApplicationComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await admissionApplicationComponentsPage.countDeleteButtons();
    await admissionApplicationComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    admissionApplicationDeleteDialog = new AdmissionApplicationDeleteDialog();
    expect(await admissionApplicationDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /cmsApp.admissionApplication.delete.question/
    );
    await admissionApplicationDeleteDialog.clickOnConfirmButton();

    await admissionApplicationComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await admissionApplicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
