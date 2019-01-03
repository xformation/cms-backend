/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CollegeBranchesComponentsPage from './college-branches.page-object';
import CollegeBranchesUpdatePage from './college-branches-update.page-object';

const expect = chai.expect;

describe('CollegeBranches e2e test', () => {
  let navBarPage: NavBarPage;
  let collegeBranchesUpdatePage: CollegeBranchesUpdatePage;
  let collegeBranchesComponentsPage: CollegeBranchesComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load CollegeBranches', async () => {
    navBarPage.getEntityPage('college-branches');
    collegeBranchesComponentsPage = new CollegeBranchesComponentsPage();
    expect(await collegeBranchesComponentsPage.getTitle().getText()).to.match(/College Branches/);
  });

  it('should load create CollegeBranches page', async () => {
    collegeBranchesComponentsPage.clickOnCreateButton();
    collegeBranchesUpdatePage = new CollegeBranchesUpdatePage();
    expect(await collegeBranchesUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.collegeBranches.home.createOrEditLabel/);
  });

  it('should create and save CollegeBranches', async () => {
    collegeBranchesUpdatePage.setBranchNameInput('branchName');
    expect(await collegeBranchesUpdatePage.getBranchNameInput()).to.match(/branchName/);
    collegeBranchesUpdatePage.setDescriptionInput('description');
    expect(await collegeBranchesUpdatePage.getDescriptionInput()).to.match(/description/);
    collegeBranchesUpdatePage.setCollegeHeadInput('collegeHead');
    expect(await collegeBranchesUpdatePage.getCollegeHeadInput()).to.match(/collegeHead/);
    await collegeBranchesUpdatePage.save();
    expect(await collegeBranchesUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
