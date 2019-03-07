/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AttendanceMasterComponentsPage from './attendance-master.page-object';
import { AttendanceMasterDeleteDialog } from './attendance-master.page-object';
import AttendanceMasterUpdatePage from './attendance-master-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AttendanceMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let attendanceMasterUpdatePage: AttendanceMasterUpdatePage;
  let attendanceMasterComponentsPage: AttendanceMasterComponentsPage;
  let attendanceMasterDeleteDialog: AttendanceMasterDeleteDialog;

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

  it('should load AttendanceMasters', async () => {
    await navBarPage.getEntityPage('attendance-master');
    attendanceMasterComponentsPage = new AttendanceMasterComponentsPage();
    expect(await attendanceMasterComponentsPage.getTitle().getText()).to.match(/Attendance Masters/);
  });

  it('should load create AttendanceMaster page', async () => {
    await attendanceMasterComponentsPage.clickOnCreateButton();
    attendanceMasterUpdatePage = new AttendanceMasterUpdatePage();
    expect(await attendanceMasterUpdatePage.getPageTitle().getText()).to.match(/Create or edit a AttendanceMaster/);
  });

  it('should create and save AttendanceMasters', async () => {
    const nbButtonsBeforeCreate = await attendanceMasterComponentsPage.countDeleteButtons();

    await attendanceMasterUpdatePage.setDescInput('desc');
    expect(await attendanceMasterUpdatePage.getDescInput()).to.match(/desc/);
    await attendanceMasterUpdatePage.batchSelectLastOption();
    await attendanceMasterUpdatePage.sectionSelectLastOption();
    await attendanceMasterUpdatePage.teachSelectLastOption();
    await waitUntilDisplayed(attendanceMasterUpdatePage.getSaveButton());
    await attendanceMasterUpdatePage.save();
    await waitUntilHidden(attendanceMasterUpdatePage.getSaveButton());
    expect(await attendanceMasterUpdatePage.getSaveButton().isPresent()).to.be.false;

    await attendanceMasterComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await attendanceMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AttendanceMaster', async () => {
    await attendanceMasterComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await attendanceMasterComponentsPage.countDeleteButtons();
    await attendanceMasterComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    attendanceMasterDeleteDialog = new AttendanceMasterDeleteDialog();
    expect(await attendanceMasterDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.attendanceMaster.delete.question/);
    await attendanceMasterDeleteDialog.clickOnConfirmButton();

    await attendanceMasterComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await attendanceMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
