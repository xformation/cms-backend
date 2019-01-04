/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AcademicSubjectComponentsPage from './academic-subject.page-object';
import { AcademicSubjectDeleteDialog } from './academic-subject.page-object';
import AcademicSubjectUpdatePage from './academic-subject-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AcademicSubject e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let academicSubjectUpdatePage: AcademicSubjectUpdatePage;
  let academicSubjectComponentsPage: AcademicSubjectComponentsPage;
  let academicSubjectDeleteDialog: AcademicSubjectDeleteDialog;

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

  it('should load AcademicSubjects', async () => {
    await navBarPage.getEntityPage('academic-subject');
    academicSubjectComponentsPage = new AcademicSubjectComponentsPage();
    expect(await academicSubjectComponentsPage.getTitle().getText()).to.match(/Academic Subjects/);
  });

  it('should load create AcademicSubject page', async () => {
    await academicSubjectComponentsPage.clickOnCreateButton();
    academicSubjectUpdatePage = new AcademicSubjectUpdatePage();
    expect(await academicSubjectUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.academicSubject.home.createOrEditLabel/);
  });

  it('should create and save AcademicSubjects', async () => {
    const nbButtonsBeforeCreate = await academicSubjectComponentsPage.countDeleteButtons();

    await academicSubjectUpdatePage.setSubjectNameInput('subjectName');
    expect(await academicSubjectUpdatePage.getSubjectNameInput()).to.match(/subjectName/);
    const selectedElectiveSub = await academicSubjectUpdatePage.getElectiveSubInput().isSelected();
    if (selectedElectiveSub) {
      await academicSubjectUpdatePage.getElectiveSubInput().click();
      expect(await academicSubjectUpdatePage.getElectiveSubInput().isSelected()).to.be.false;
    } else {
      await academicSubjectUpdatePage.getElectiveSubInput().click();
      expect(await academicSubjectUpdatePage.getElectiveSubInput().isSelected()).to.be.true;
    }
    await academicSubjectUpdatePage.academicDepartmentSelectLastOption();
    await waitUntilDisplayed(academicSubjectUpdatePage.getSaveButton());
    await academicSubjectUpdatePage.save();
    await waitUntilHidden(academicSubjectUpdatePage.getSaveButton());
    expect(await academicSubjectUpdatePage.getSaveButton().isPresent()).to.be.false;

    await academicSubjectComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await academicSubjectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last AcademicSubject', async () => {
    await academicSubjectComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await academicSubjectComponentsPage.countDeleteButtons();
    await academicSubjectComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    academicSubjectDeleteDialog = new AcademicSubjectDeleteDialog();
    expect(await academicSubjectDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.academicSubject.delete.question/);
    await academicSubjectDeleteDialog.clickOnConfirmButton();

    await academicSubjectComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await academicSubjectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
