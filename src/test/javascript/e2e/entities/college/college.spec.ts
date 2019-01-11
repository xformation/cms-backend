/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CollegeComponentsPage from './college.page-object';
import CollegeUpdatePage from './college-update.page-object';

const expect = chai.expect;

describe('College e2e test', () => {
  let navBarPage: NavBarPage;
  let collegeUpdatePage: CollegeUpdatePage;
  let collegeComponentsPage: CollegeComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Colleges', async () => {
    navBarPage.getEntityPage('college');
    collegeComponentsPage = new CollegeComponentsPage();
    expect(await collegeComponentsPage.getTitle().getText()).to.match(/Colleges/);
  });

  it('should load create College page', async () => {
    collegeComponentsPage.clickOnCreateButton();
    collegeUpdatePage = new CollegeUpdatePage();
    expect(await collegeUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.college.home.createOrEditLabel/);
  });

  it('should create and save Colleges', async () => {
    collegeUpdatePage.setShortNameInput('shortName');
    expect(await collegeUpdatePage.getShortNameInput()).to.match(/shortName/);
    collegeUpdatePage.setLogoInput('5');
    expect(await collegeUpdatePage.getLogoInput()).to.eq('5');
    collegeUpdatePage.setBackgroundImageInput('5');
    expect(await collegeUpdatePage.getBackgroundImageInput()).to.eq('5');
    collegeUpdatePage.setInstructionInformationInput('instructionInformation');
    expect(await collegeUpdatePage.getInstructionInformationInput()).to.match(/instructionInformation/);
    collegeUpdatePage.branchSelectLastOption();
    await collegeUpdatePage.save();
    expect(await collegeUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
