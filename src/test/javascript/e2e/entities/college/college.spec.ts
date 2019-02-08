/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import CollegeComponentsPage from './college.page-object';
import { CollegeDeleteDialog } from './college.page-object';
import CollegeUpdatePage from './college-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('College e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let collegeUpdatePage: CollegeUpdatePage;
  let collegeComponentsPage: CollegeComponentsPage;
  let collegeDeleteDialog: CollegeDeleteDialog;

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

  it('should load Colleges', async () => {
    await navBarPage.getEntityPage('college');
    collegeComponentsPage = new CollegeComponentsPage();
    expect(await collegeComponentsPage.getTitle().getText()).to.match(/Colleges/);
  });

  it('should load create College page', async () => {
    await collegeComponentsPage.clickOnCreateButton();
    collegeUpdatePage = new CollegeUpdatePage();
    expect(await collegeUpdatePage.getPageTitle().getText()).to.match(/Create or edit a College/);
  });

  it('should create and save Colleges', async () => {
    const nbButtonsBeforeCreate = await collegeComponentsPage.countDeleteButtons();

    await collegeUpdatePage.setShortNameInput('shortName');
    expect(await collegeUpdatePage.getShortNameInput()).to.match(/shortName/);
    await collegeUpdatePage.setLogoInput('5');
    expect(await collegeUpdatePage.getLogoInput()).to.eq('5');
    await collegeUpdatePage.setBackgroundImageInput('5');
    expect(await collegeUpdatePage.getBackgroundImageInput()).to.eq('5');
    await collegeUpdatePage.setInstructionInformationInput('instructionInformation');
    expect(await collegeUpdatePage.getInstructionInformationInput()).to.match(/instructionInformation/);
    await waitUntilDisplayed(collegeUpdatePage.getSaveButton());
    await collegeUpdatePage.save();
    await waitUntilHidden(collegeUpdatePage.getSaveButton());
    expect(await collegeUpdatePage.getSaveButton().isPresent()).to.be.false;

    await collegeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await collegeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last College', async () => {
    await collegeComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await collegeComponentsPage.countDeleteButtons();
    await collegeComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    collegeDeleteDialog = new CollegeDeleteDialog();
    expect(await collegeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.college.delete.question/);
    await collegeDeleteDialog.clickOnConfirmButton();

    await collegeComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await collegeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
