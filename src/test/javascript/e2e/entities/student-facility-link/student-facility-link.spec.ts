/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import StudentFacilityLinkComponentsPage from './student-facility-link.page-object';
import StudentFacilityLinkUpdatePage from './student-facility-link-update.page-object';

const expect = chai.expect;

describe('StudentFacilityLink e2e test', () => {
  let navBarPage: NavBarPage;
  let studentFacilityLinkUpdatePage: StudentFacilityLinkUpdatePage;
  let studentFacilityLinkComponentsPage: StudentFacilityLinkComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load StudentFacilityLinks', async () => {
    navBarPage.getEntityPage('student-facility-link');
    studentFacilityLinkComponentsPage = new StudentFacilityLinkComponentsPage();
    expect(await studentFacilityLinkComponentsPage.getTitle().getText()).to.match(/Student Facility Links/);
  });

  it('should load create StudentFacilityLink page', async () => {
    studentFacilityLinkComponentsPage.clickOnCreateButton();
    studentFacilityLinkUpdatePage = new StudentFacilityLinkUpdatePage();
    expect(await studentFacilityLinkUpdatePage.getPageTitle().getText()).to.match(/Create or edit a StudentFacilityLink/);
  });

  it('should create and save StudentFacilityLinks', async () => {
    studentFacilityLinkUpdatePage.setLinkDescInput('linkDesc');
    expect(await studentFacilityLinkUpdatePage.getLinkDescInput()).to.match(/linkDesc/);
    studentFacilityLinkUpdatePage.studentSelectLastOption();
    studentFacilityLinkUpdatePage.facilitySelectLastOption();
    await studentFacilityLinkUpdatePage.save();
    expect(await studentFacilityLinkUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
