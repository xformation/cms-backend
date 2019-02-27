/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TeacherComponentsPage from './teacher.page-object';
import { TeacherDeleteDialog } from './teacher.page-object';
import TeacherUpdatePage from './teacher-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Teacher e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let teacherUpdatePage: TeacherUpdatePage;
  let teacherComponentsPage: TeacherComponentsPage;
  let teacherDeleteDialog: TeacherDeleteDialog;

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

  it('should load Teachers', async () => {
    await navBarPage.getEntityPage('teacher');
    teacherComponentsPage = new TeacherComponentsPage();
    expect(await teacherComponentsPage.getTitle().getText()).to.match(/Teachers/);
  });

  it('should load create Teacher page', async () => {
    await teacherComponentsPage.clickOnCreateButton();
    teacherUpdatePage = new TeacherUpdatePage();
    expect(await teacherUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Teacher/);
  });

  it('should create and save Teachers', async () => {
    const nbButtonsBeforeCreate = await teacherComponentsPage.countDeleteButtons();

    await teacherUpdatePage.setTeacherNameInput('teacherName');
    expect(await teacherUpdatePage.getTeacherNameInput()).to.match(/teacherName/);
    await teacherUpdatePage.setTeacherMiddleNameInput('teacherMiddleName');
    expect(await teacherUpdatePage.getTeacherMiddleNameInput()).to.match(/teacherMiddleName/);
    await teacherUpdatePage.setTeacherLastNameInput('teacherLastName');
    expect(await teacherUpdatePage.getTeacherLastNameInput()).to.match(/teacherLastName/);
    await teacherUpdatePage.setFatherNameInput('fatherName');
    expect(await teacherUpdatePage.getFatherNameInput()).to.match(/fatherName/);
    await teacherUpdatePage.setFatherMiddleNameInput('fatherMiddleName');
    expect(await teacherUpdatePage.getFatherMiddleNameInput()).to.match(/fatherMiddleName/);
    await teacherUpdatePage.setFatherLastNameInput('fatherLastName');
    expect(await teacherUpdatePage.getFatherLastNameInput()).to.match(/fatherLastName/);
    await teacherUpdatePage.setMotherNameInput('motherName');
    expect(await teacherUpdatePage.getMotherNameInput()).to.match(/motherName/);
    await teacherUpdatePage.setMotherMiddleNameInput('motherMiddleName');
    expect(await teacherUpdatePage.getMotherMiddleNameInput()).to.match(/motherMiddleName/);
    await teacherUpdatePage.setMotherLastNameInput('motherLastName');
    expect(await teacherUpdatePage.getMotherLastNameInput()).to.match(/motherLastName/);
    await teacherUpdatePage.setAadharNoInput('5');
    expect(await teacherUpdatePage.getAadharNoInput()).to.eq('5');
    await teacherUpdatePage.setDateOfBirthInput('01-01-2001');
    expect(await teacherUpdatePage.getDateOfBirthInput()).to.eq('2001-01-01');
    await teacherUpdatePage.setPlaceOfBirthInput('placeOfBirth');
    expect(await teacherUpdatePage.getPlaceOfBirthInput()).to.match(/placeOfBirth/);
    await teacherUpdatePage.religionSelectLastOption();
    await teacherUpdatePage.casteSelectLastOption();
    await teacherUpdatePage.setSubCasteInput('subCaste');
    expect(await teacherUpdatePage.getSubCasteInput()).to.match(/subCaste/);
    await teacherUpdatePage.setAgeInput('5');
    expect(await teacherUpdatePage.getAgeInput()).to.eq('5');
    await teacherUpdatePage.sexSelectLastOption();
    await teacherUpdatePage.bloodGroupSelectLastOption();
    await teacherUpdatePage.setAddressLineOneInput('addressLineOne');
    expect(await teacherUpdatePage.getAddressLineOneInput()).to.match(/addressLineOne/);
    await teacherUpdatePage.setAddressLineTwoInput('addressLineTwo');
    expect(await teacherUpdatePage.getAddressLineTwoInput()).to.match(/addressLineTwo/);
    await teacherUpdatePage.setAddressLineThreeInput('addressLineThree');
    expect(await teacherUpdatePage.getAddressLineThreeInput()).to.match(/addressLineThree/);
    await teacherUpdatePage.setTownInput('town');
    expect(await teacherUpdatePage.getTownInput()).to.match(/town/);
    await teacherUpdatePage.setStateInput('state');
    expect(await teacherUpdatePage.getStateInput()).to.match(/state/);
    await teacherUpdatePage.setCountryInput('country');
    expect(await teacherUpdatePage.getCountryInput()).to.match(/country/);
    await teacherUpdatePage.setPincodeInput('5');
    expect(await teacherUpdatePage.getPincodeInput()).to.eq('5');
    await teacherUpdatePage.setTeacherContactNumberInput('5');
    expect(await teacherUpdatePage.getTeacherContactNumberInput()).to.eq('5');
    await teacherUpdatePage.setAlternateContactNumberInput('5');
    expect(await teacherUpdatePage.getAlternateContactNumberInput()).to.eq('5');
    await teacherUpdatePage.setTeacherEmailAddressInput('teacherEmailAddress');
    expect(await teacherUpdatePage.getTeacherEmailAddressInput()).to.match(/teacherEmailAddress/);
    await teacherUpdatePage.setAlternateEmailAddressInput('alternateEmailAddress');
    expect(await teacherUpdatePage.getAlternateEmailAddressInput()).to.match(/alternateEmailAddress/);
    await teacherUpdatePage.relationWithStaffSelectLastOption();
    await teacherUpdatePage.setNameInput('name');
    expect(await teacherUpdatePage.getNameInput()).to.match(/name/);
    await teacherUpdatePage.setMiddleNameInput('middleName');
    expect(await teacherUpdatePage.getMiddleNameInput()).to.match(/middleName/);
    await teacherUpdatePage.setLastNameInput('lastName');
    expect(await teacherUpdatePage.getLastNameInput()).to.match(/lastName/);
    await teacherUpdatePage.setContactNoInput('5');
    expect(await teacherUpdatePage.getContactNoInput()).to.eq('5');
    await teacherUpdatePage.setEmailAddressInput('emailAddress');
    expect(await teacherUpdatePage.getEmailAddressInput()).to.match(/emailAddress/);
    await teacherUpdatePage.setUploadPhotoInput('5');
    expect(await teacherUpdatePage.getUploadPhotoInput()).to.eq('5');
    await teacherUpdatePage.setEmployeeIdInput('5');
    expect(await teacherUpdatePage.getEmployeeIdInput()).to.eq('5');
    await teacherUpdatePage.setDesignationInput('designation');
    expect(await teacherUpdatePage.getDesignationInput()).to.match(/designation/);
    await teacherUpdatePage.staffTypeSelectLastOption();
    await teacherUpdatePage.departmentSelectLastOption();
    await teacherUpdatePage.branchSelectLastOption();
    await waitUntilDisplayed(teacherUpdatePage.getSaveButton());
    await teacherUpdatePage.save();
    await waitUntilHidden(teacherUpdatePage.getSaveButton());
    expect(await teacherUpdatePage.getSaveButton().isPresent()).to.be.false;

    await teacherComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await teacherComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Teacher', async () => {
    await teacherComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await teacherComponentsPage.countDeleteButtons();
    await teacherComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    teacherDeleteDialog = new TeacherDeleteDialog();
    expect(await teacherDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.teacher.delete.question/);
    await teacherDeleteDialog.clickOnConfirmButton();

    await teacherComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await teacherComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
