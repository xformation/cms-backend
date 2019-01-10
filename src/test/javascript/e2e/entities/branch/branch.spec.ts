/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BranchComponentsPage from './branch.page-object';
import BranchUpdatePage from './branch-update.page-object';

const expect = chai.expect;

describe('Branch e2e test', () => {
  let navBarPage: NavBarPage;
  let branchUpdatePage: BranchUpdatePage;
  let branchComponentsPage: BranchComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Branches', async () => {
    navBarPage.getEntityPage('branch');
    branchComponentsPage = new BranchComponentsPage();
    expect(await branchComponentsPage.getTitle().getText()).to.match(/Branches/);
  });

  it('should load create Branch page', async () => {
    branchComponentsPage.clickOnCreateButton();
    branchUpdatePage = new BranchUpdatePage();
    expect(await branchUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.branch.home.createOrEditLabel/);
  });

  it('should create and save Branches', async () => {
    branchUpdatePage.setBranchNameInput('branchName');
    expect(await branchUpdatePage.getBranchNameInput()).to.match(/branchName/);
    branchUpdatePage.setDescriptionInput('description');
    expect(await branchUpdatePage.getDescriptionInput()).to.match(/description/);
    branchUpdatePage.setCollegeHeadInput('collegeHead');
    expect(await branchUpdatePage.getCollegeHeadInput()).to.match(/collegeHead/);
    branchUpdatePage.collegeSelectLastOption();
    branchUpdatePage.collegeSelectLastOption();
    await branchUpdatePage.save();
    expect(await branchUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
