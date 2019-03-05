/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TeacherComponentsPage from './teacher.page-object';
import TeacherUpdatePage from './teacher-update.page-object';

const expect = chai.expect;

describe('Teacher e2e test', () => {
  let navBarPage: NavBarPage;
  let teacherUpdatePage: TeacherUpdatePage;
  let teacherComponentsPage: TeacherComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Teachers', async () => {
    navBarPage.getEntityPage('teacher');
    teacherComponentsPage = new TeacherComponentsPage();
    expect(await teacherComponentsPage.getTitle().getText()).to.match(/Teachers/);
  });

  it('should load create Teacher page', async () => {
    teacherComponentsPage.clickOnCreateButton();
    teacherUpdatePage = new TeacherUpdatePage();
    expect(await teacherUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Teacher/);
  });

  it('should create and save Teachers', async () => {
    teacherUpdatePage.setTeacherNameInput('teacherName');
    expect(await teacherUpdatePage.getTeacherNameInput()).to.match(/teacherName/);
    teacherUpdatePage.setTeacherMiddleNameInput('teacherMiddleName');
    expect(await teacherUpdatePage.getTeacherMiddleNameInput()).to.match(/teacherMiddleName/);
    teacherUpdatePage.setTeacherLastNameInput('teacherLastName');
    expect(await teacherUpdatePage.getTeacherLastNameInput()).to.match(/teacherLastName/);
    teacherUpdatePage.setFatherNameInput('fatherName');
    expect(await teacherUpdatePage.getFatherNameInput()).to.match(/fatherName/);
    teacherUpdatePage.setFatherMiddleNameInput('fatherMiddleName');
    expect(await teacherUpdatePage.getFatherMiddleNameInput()).to.match(/fatherMiddleName/);
    teacherUpdatePage.setFatherLastNameInput('fatherLastName');
    expect(await teacherUpdatePage.getFatherLastNameInput()).to.match(/fatherLastName/);
    teacherUpdatePage.setSpouseNameInput('spouseName');
    expect(await teacherUpdatePage.getSpouseNameInput()).to.match(/spouseName/);
    teacherUpdatePage.setSpouseMiddleNameInput('spouseMiddleName');
    expect(await teacherUpdatePage.getSpouseMiddleNameInput()).to.match(/spouseMiddleName/);
    teacherUpdatePage.setSpouseLastNameInput('spouseLastName');
    expect(await teacherUpdatePage.getSpouseLastNameInput()).to.match(/spouseLastName/);
    teacherUpdatePage.setMotherNameInput('motherName');
    expect(await teacherUpdatePage.getMotherNameInput()).to.match(/motherName/);
    teacherUpdatePage.setMotherMiddleNameInput('motherMiddleName');
    expect(await teacherUpdatePage.getMotherMiddleNameInput()).to.match(/motherMiddleName/);
    teacherUpdatePage.setMotherLastNameInput('motherLastName');
    expect(await teacherUpdatePage.getMotherLastNameInput()).to.match(/motherLastName/);
    teacherUpdatePage.setAadharNoInput('5');
    expect(await teacherUpdatePage.getAadharNoInput()).to.eq('5');
    teacherUpdatePage.setDateOfBirthInput('01-01-2001');
    expect(await teacherUpdatePage.getDateOfBirthInput()).to.eq('2001-01-01');
    teacherUpdatePage.setPlaceOfBirthInput('placeOfBirth');
    expect(await teacherUpdatePage.getPlaceOfBirthInput()).to.match(/placeOfBirth/);
    teacherUpdatePage.religionSelectLastOption();
    teacherUpdatePage.casteSelectLastOption();
    teacherUpdatePage.setSubCasteInput('subCaste');
    expect(await teacherUpdatePage.getSubCasteInput()).to.match(/subCaste/);
    teacherUpdatePage.setAgeInput('5');
    expect(await teacherUpdatePage.getAgeInput()).to.eq('5');
    teacherUpdatePage.sexSelectLastOption();
    teacherUpdatePage.bloodGroupSelectLastOption();
    teacherUpdatePage.setAddressLineOneInput('addressLineOne');
    expect(await teacherUpdatePage.getAddressLineOneInput()).to.match(/addressLineOne/);
    teacherUpdatePage.setAddressLineTwoInput('addressLineTwo');
    expect(await teacherUpdatePage.getAddressLineTwoInput()).to.match(/addressLineTwo/);
    teacherUpdatePage.setAddressLineThreeInput('addressLineThree');
    expect(await teacherUpdatePage.getAddressLineThreeInput()).to.match(/addressLineThree/);
    teacherUpdatePage.setTownInput('town');
    expect(await teacherUpdatePage.getTownInput()).to.match(/town/);
    teacherUpdatePage.setStateInput('state');
    expect(await teacherUpdatePage.getStateInput()).to.match(/state/);
    teacherUpdatePage.setCountryInput('country');
    expect(await teacherUpdatePage.getCountryInput()).to.match(/country/);
    teacherUpdatePage.setPincodeInput('5');
    expect(await teacherUpdatePage.getPincodeInput()).to.eq('5');
    teacherUpdatePage.setTeacherContactNumberInput('teacherContactNumber');
    expect(await teacherUpdatePage.getTeacherContactNumberInput()).to.match(/teacherContactNumber/);
    teacherUpdatePage.setAlternateContactNumberInput('alternateContactNumber');
    expect(await teacherUpdatePage.getAlternateContactNumberInput()).to.match(/alternateContactNumber/);
    teacherUpdatePage.setTeacherEmailAddressInput('teacherEmailAddress');
    expect(await teacherUpdatePage.getTeacherEmailAddressInput()).to.match(/teacherEmailAddress/);
    teacherUpdatePage.setAlternateEmailAddressInput('alternateEmailAddress');
    expect(await teacherUpdatePage.getAlternateEmailAddressInput()).to.match(/alternateEmailAddress/);
    teacherUpdatePage.relationWithStaffSelectLastOption();
    teacherUpdatePage.setEmergencyContactNameInput('emergencyContactName');
    expect(await teacherUpdatePage.getEmergencyContactNameInput()).to.match(/emergencyContactName/);
    teacherUpdatePage.setEmergencyContactMiddleNameInput('emergencyContactMiddleName');
    expect(await teacherUpdatePage.getEmergencyContactMiddleNameInput()).to.match(/emergencyContactMiddleName/);
    teacherUpdatePage.setEmergencyContactLastNameInput('emergencyContactLastName');
    expect(await teacherUpdatePage.getEmergencyContactLastNameInput()).to.match(/emergencyContactLastName/);
    teacherUpdatePage.setEmergencyContactNoInput('emergencyContactNo');
    expect(await teacherUpdatePage.getEmergencyContactNoInput()).to.match(/emergencyContactNo/);
    teacherUpdatePage.setEmergencyContactEmailAddressInput('emergencyContactEmailAddress');
    expect(await teacherUpdatePage.getEmergencyContactEmailAddressInput()).to.match(/emergencyContactEmailAddress/);
    teacherUpdatePage.setUploadPhotoInput('uploadPhoto');
    expect(await teacherUpdatePage.getUploadPhotoInput()).to.match(/uploadPhoto/);
    teacherUpdatePage.setEmployeeIdInput('5');
    expect(await teacherUpdatePage.getEmployeeIdInput()).to.eq('5');
    teacherUpdatePage.setDesignationInput('designation');
    expect(await teacherUpdatePage.getDesignationInput()).to.match(/designation/);
    teacherUpdatePage.staffTypeSelectLastOption();
    teacherUpdatePage.departmentSelectLastOption();
    teacherUpdatePage.branchSelectLastOption();
    await teacherUpdatePage.save();
    expect(await teacherUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
