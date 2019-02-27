/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import StudentComponentsPage from './student.page-object';
import { StudentDeleteDialog } from './student.page-object';
import StudentUpdatePage from './student-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Student e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studentUpdatePage: StudentUpdatePage;
  let studentComponentsPage: StudentComponentsPage;
  /*let studentDeleteDialog: StudentDeleteDialog;*/

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

  it('should load Students', async () => {
    await navBarPage.getEntityPage('student');
    studentComponentsPage = new StudentComponentsPage();
    expect(await studentComponentsPage.getTitle().getText()).to.match(/Students/);
  });

  it('should load create Student page', async () => {
    await studentComponentsPage.clickOnCreateButton();
    studentUpdatePage = new StudentUpdatePage();
    expect(await studentUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Student/);
  });

  /* it('should create and save Students', async () => {
        const nbButtonsBeforeCreate = await studentComponentsPage.countDeleteButtons();

        await studentUpdatePage.setStudentNameInput('studentName');
        expect(await studentUpdatePage.getStudentNameInput()).to.match(/studentName/);
        await studentUpdatePage.setStudentMiddleNameInput('studentMiddleName');
        expect(await studentUpdatePage.getStudentMiddleNameInput()).to.match(/studentMiddleName/);
        await studentUpdatePage.setStudentLastNameInput('studentLastName');
        expect(await studentUpdatePage.getStudentLastNameInput()).to.match(/studentLastName/);
        await studentUpdatePage.setFatherNameInput('fatherName');
        expect(await studentUpdatePage.getFatherNameInput()).to.match(/fatherName/);
        await studentUpdatePage.setFatherMiddleNameInput('fatherMiddleName');
        expect(await studentUpdatePage.getFatherMiddleNameInput()).to.match(/fatherMiddleName/);
        await studentUpdatePage.setFatherLastNameInput('fatherLastName');
        expect(await studentUpdatePage.getFatherLastNameInput()).to.match(/fatherLastName/);
        await studentUpdatePage.setMotherNameInput('motherName');
        expect(await studentUpdatePage.getMotherNameInput()).to.match(/motherName/);
        await studentUpdatePage.setMotherMiddleNameInput('motherMiddleName');
        expect(await studentUpdatePage.getMotherMiddleNameInput()).to.match(/motherMiddleName/);
        await studentUpdatePage.setMotherLastNameInput('motherLastName');
        expect(await studentUpdatePage.getMotherLastNameInput()).to.match(/motherLastName/);
        await studentUpdatePage.setAadharNoInput('5');
        expect(await studentUpdatePage.getAadharNoInput()).to.eq('5');
        await studentUpdatePage.setDateOfBirthInput('01-01-2001');
        expect(await studentUpdatePage.getDateOfBirthInput()).to.eq('2001-01-01');
        await studentUpdatePage.setPlaceOfBirthInput('placeOfBirth');
        expect(await studentUpdatePage.getPlaceOfBirthInput()).to.match(/placeOfBirth/);
        await studentUpdatePage.religionSelectLastOption();
        await studentUpdatePage.casteSelectLastOption();
        await studentUpdatePage.setSubCasteInput('subCaste');
        expect(await studentUpdatePage.getSubCasteInput()).to.match(/subCaste/);
        await studentUpdatePage.setAgeInput('5');
        expect(await studentUpdatePage.getAgeInput()).to.eq('5');
        await studentUpdatePage.sexSelectLastOption();
        await studentUpdatePage.bloodGroupSelectLastOption();
        await studentUpdatePage.setAddressLineOneInput('addressLineOne');
        expect(await studentUpdatePage.getAddressLineOneInput()).to.match(/addressLineOne/);
        await studentUpdatePage.setAddressLineTwoInput('addressLineTwo');
        expect(await studentUpdatePage.getAddressLineTwoInput()).to.match(/addressLineTwo/);
        await studentUpdatePage.setAddressLineThreeInput('addressLineThree');
        expect(await studentUpdatePage.getAddressLineThreeInput()).to.match(/addressLineThree/);
        await studentUpdatePage.setTownInput('town');
        expect(await studentUpdatePage.getTownInput()).to.match(/town/);
        await studentUpdatePage.setStateInput('state');
        expect(await studentUpdatePage.getStateInput()).to.match(/state/);
        await studentUpdatePage.setCountryInput('country');
        expect(await studentUpdatePage.getCountryInput()).to.match(/country/);
        await studentUpdatePage.setPincodeInput('5');
        expect(await studentUpdatePage.getPincodeInput()).to.eq('5');
        await studentUpdatePage.setStudentContactNumberInput('5');
        expect(await studentUpdatePage.getStudentContactNumberInput()).to.eq('5');
        await studentUpdatePage.setAlternateContactNumberInput('5');
        expect(await studentUpdatePage.getAlternateContactNumberInput()).to.eq('5');
        await studentUpdatePage.setStudentEmailAddressInput('studentEmailAddress');
        expect(await studentUpdatePage.getStudentEmailAddressInput()).to.match(/studentEmailAddress/);
        await studentUpdatePage.setAlternateEmailAddressInput('alternateEmailAddress');
        expect(await studentUpdatePage.getAlternateEmailAddressInput()).to.match(/alternateEmailAddress/);
        await studentUpdatePage.relationWithStudentSelectLastOption();
        await studentUpdatePage.setNameInput('name');
        expect(await studentUpdatePage.getNameInput()).to.match(/name/);
        await studentUpdatePage.setMiddleNameInput('middleName');
        expect(await studentUpdatePage.getMiddleNameInput()).to.match(/middleName/);
        await studentUpdatePage.setLastNameInput('lastName');
        expect(await studentUpdatePage.getLastNameInput()).to.match(/lastName/);
        await studentUpdatePage.setContactNoInput('5');
        expect(await studentUpdatePage.getContactNoInput()).to.eq('5');
        await studentUpdatePage.setEmailAddressInput('emailAddress');
        expect(await studentUpdatePage.getEmailAddressInput()).to.match(/emailAddress/);
        await studentUpdatePage.transportSelectLastOption();
        await studentUpdatePage.messSelectLastOption();
        await studentUpdatePage.gymSelectLastOption();
        await studentUpdatePage.culturalClassSelectLastOption();
        await studentUpdatePage.librarySelectLastOption();
        await studentUpdatePage.sportsSelectLastOption();
        await studentUpdatePage.swimmingSelectLastOption();
        await studentUpdatePage.extraClassSelectLastOption();
        await studentUpdatePage.handicraftsSelectLastOption();
        await studentUpdatePage.addSelectLastOption();
        await studentUpdatePage.setUploadPhotoInput('5');
        expect(await studentUpdatePage.getUploadPhotoInput()).to.eq('5');
        await studentUpdatePage.setAdmissionNoInput('5');
        expect(await studentUpdatePage.getAdmissionNoInput()).to.eq('5');
        await studentUpdatePage.setRollNoInput('rollNo');
        expect(await studentUpdatePage.getRollNoInput()).to.match(/rollNo/);
        await studentUpdatePage.studentTypeSelectLastOption();
        await studentUpdatePage.departmentSelectLastOption();
        await studentUpdatePage.batchSelectLastOption();
        await studentUpdatePage.sectionSelectLastOption();
        await studentUpdatePage.branchSelectLastOption();
        await waitUntilDisplayed(studentUpdatePage.getSaveButton());
        await studentUpdatePage.save();
        await waitUntilHidden(studentUpdatePage.getSaveButton());
        expect(await studentUpdatePage.getSaveButton().isPresent()).to.be.false;

        await studentComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
        expect(await studentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

  /* it('should delete last Student', async () => {
        await studentComponentsPage.waitUntilLoaded();
        const nbButtonsBeforeDelete = await studentComponentsPage.countDeleteButtons();
        await studentComponentsPage.clickOnLastDeleteButton();

        const deleteModal = element(by.className('modal'));
        await waitUntilDisplayed(deleteModal);

        studentDeleteDialog = new StudentDeleteDialog();
        expect(await studentDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/cmsApp.student.delete.question/);
        await studentDeleteDialog.clickOnConfirmButton();

        await studentComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
        expect(await studentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
