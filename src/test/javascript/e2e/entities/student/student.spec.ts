/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentComponentsPage from './student.page-object';
import StudentUpdatePage from './student-update.page-object';

const expect = chai.expect;

describe('Student e2e test', () => {
  let navBarPage: NavBarPage;
  let studentUpdatePage: StudentUpdatePage;
  let studentComponentsPage: StudentComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Students', async () => {
    navBarPage.getEntityPage('student');
    studentComponentsPage = new StudentComponentsPage();
    expect(await studentComponentsPage.getTitle().getText()).to.match(/Students/);
  });

  it('should load create Student page', async () => {
    studentComponentsPage.clickOnCreateButton();
    studentUpdatePage = new StudentUpdatePage();
    expect(await studentUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Student/);
  });

  /* it('should create and save Students', async () => {
        studentUpdatePage.setStudentNameInput('studentName');
        expect(await studentUpdatePage.getStudentNameInput()).to.match(/studentName/);
        studentUpdatePage.setStudentMiddleNameInput('studentMiddleName');
        expect(await studentUpdatePage.getStudentMiddleNameInput()).to.match(/studentMiddleName/);
        studentUpdatePage.setStudentLastNameInput('studentLastName');
        expect(await studentUpdatePage.getStudentLastNameInput()).to.match(/studentLastName/);
        studentUpdatePage.setFatherNameInput('fatherName');
        expect(await studentUpdatePage.getFatherNameInput()).to.match(/fatherName/);
        studentUpdatePage.setFatherMiddleNameInput('fatherMiddleName');
        expect(await studentUpdatePage.getFatherMiddleNameInput()).to.match(/fatherMiddleName/);
        studentUpdatePage.setFatherLastNameInput('fatherLastName');
        expect(await studentUpdatePage.getFatherLastNameInput()).to.match(/fatherLastName/);
        studentUpdatePage.setMotherNameInput('motherName');
        expect(await studentUpdatePage.getMotherNameInput()).to.match(/motherName/);
        studentUpdatePage.setMotherMiddleNameInput('motherMiddleName');
        expect(await studentUpdatePage.getMotherMiddleNameInput()).to.match(/motherMiddleName/);
        studentUpdatePage.setMotherLastNameInput('motherLastName');
        expect(await studentUpdatePage.getMotherLastNameInput()).to.match(/motherLastName/);
        studentUpdatePage.setAadharNoInput('5');
        expect(await studentUpdatePage.getAadharNoInput()).to.eq('5');
        studentUpdatePage.setDateOfBirthInput('01-01-2001');
        expect(await studentUpdatePage.getDateOfBirthInput()).to.eq('2001-01-01');
        studentUpdatePage.setPlaceOfBirthInput('placeOfBirth');
        expect(await studentUpdatePage.getPlaceOfBirthInput()).to.match(/placeOfBirth/);
        studentUpdatePage.religionSelectLastOption();
        studentUpdatePage.casteSelectLastOption();
        studentUpdatePage.setSubCasteInput('subCaste');
        expect(await studentUpdatePage.getSubCasteInput()).to.match(/subCaste/);
        studentUpdatePage.setAgeInput('5');
        expect(await studentUpdatePage.getAgeInput()).to.eq('5');
        studentUpdatePage.sexSelectLastOption();
        studentUpdatePage.bloodGroupSelectLastOption();
        studentUpdatePage.setAddressLineOneInput('addressLineOne');
        expect(await studentUpdatePage.getAddressLineOneInput()).to.match(/addressLineOne/);
        studentUpdatePage.setAddressLineTwoInput('addressLineTwo');
        expect(await studentUpdatePage.getAddressLineTwoInput()).to.match(/addressLineTwo/);
        studentUpdatePage.setAddressLineThreeInput('addressLineThree');
        expect(await studentUpdatePage.getAddressLineThreeInput()).to.match(/addressLineThree/);
        studentUpdatePage.setTownInput('town');
        expect(await studentUpdatePage.getTownInput()).to.match(/town/);
        studentUpdatePage.setStateInput('state');
        expect(await studentUpdatePage.getStateInput()).to.match(/state/);
        studentUpdatePage.setCountryInput('country');
        expect(await studentUpdatePage.getCountryInput()).to.match(/country/);
        studentUpdatePage.setPincodeInput('5');
        expect(await studentUpdatePage.getPincodeInput()).to.eq('5');
        studentUpdatePage.setStudentContactNumberInput('5');
        expect(await studentUpdatePage.getStudentContactNumberInput()).to.eq('5');
        studentUpdatePage.setAlternateContactNumberInput('5');
        expect(await studentUpdatePage.getAlternateContactNumberInput()).to.eq('5');
        studentUpdatePage.setStudentEmailAddressInput('studentEmailAddress');
        expect(await studentUpdatePage.getStudentEmailAddressInput()).to.match(/studentEmailAddress/);
        studentUpdatePage.setAlternateEmailAddressInput('alternateEmailAddress');
        expect(await studentUpdatePage.getAlternateEmailAddressInput()).to.match(/alternateEmailAddress/);
        studentUpdatePage.relationWithStudentSelectLastOption();
        studentUpdatePage.setNameInput('name');
        expect(await studentUpdatePage.getNameInput()).to.match(/name/);
        studentUpdatePage.setMiddleNameInput('middleName');
        expect(await studentUpdatePage.getMiddleNameInput()).to.match(/middleName/);
        studentUpdatePage.setLastNameInput('lastName');
        expect(await studentUpdatePage.getLastNameInput()).to.match(/lastName/);
        studentUpdatePage.setContactNoInput('5');
        expect(await studentUpdatePage.getContactNoInput()).to.eq('5');
        studentUpdatePage.setEmailAddressInput('emailAddress');
        expect(await studentUpdatePage.getEmailAddressInput()).to.match(/emailAddress/);
        studentUpdatePage.transportSelectLastOption();
        studentUpdatePage.messSelectLastOption();
        studentUpdatePage.gymSelectLastOption();
        studentUpdatePage.culturalClassSelectLastOption();
        studentUpdatePage.librarySelectLastOption();
        studentUpdatePage.sportsSelectLastOption();
        studentUpdatePage.swimmingSelectLastOption();
        studentUpdatePage.extraClassSelectLastOption();
        studentUpdatePage.handicraftsSelectLastOption();
        studentUpdatePage.addSelectLastOption();
        studentUpdatePage.setUploadPhotoInput('5');
        expect(await studentUpdatePage.getUploadPhotoInput()).to.eq('5');
        studentUpdatePage.setAdmissionNoInput('5');
        expect(await studentUpdatePage.getAdmissionNoInput()).to.eq('5');
        studentUpdatePage.setRollNoInput('rollNo');
        expect(await studentUpdatePage.getRollNoInput()).to.match(/rollNo/);
        studentUpdatePage.studentTypeSelectLastOption();
        studentUpdatePage.departmentSelectLastOption();
        studentUpdatePage.batchSelectLastOption();
        studentUpdatePage.sectionSelectLastOption();
        studentUpdatePage.branchSelectLastOption();
        await studentUpdatePage.save();
        expect(await studentUpdatePage.getSaveButton().isPresent()).to.be.false;
    });*/

  after(() => {
    navBarPage.autoSignOut();
  });
});
