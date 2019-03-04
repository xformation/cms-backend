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
    expect(await branchUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Branch/);
  });

  it('should create and save Branches', async () => {
    branchUpdatePage.setBranchNameInput('branchName');
    expect(await branchUpdatePage.getBranchNameInput()).to.match(/branchName/);
    branchUpdatePage.setAddress1Input('address1');
    expect(await branchUpdatePage.getAddress1Input()).to.match(/address1/);
    branchUpdatePage.setAddress2Input('address2');
    expect(await branchUpdatePage.getAddress2Input()).to.match(/address2/);
    branchUpdatePage.setBranchHeadInput('branchHead');
    expect(await branchUpdatePage.getBranchHeadInput()).to.match(/branchHead/);
    branchUpdatePage.collegeSelectLastOption();
    branchUpdatePage.citySelectLastOption();
    branchUpdatePage.stateSelectLastOption();
    await branchUpdatePage.save();
    expect(await branchUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
