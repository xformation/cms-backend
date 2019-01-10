/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import BatchComponentsPage from './batch.page-object';
import BatchUpdatePage from './batch-update.page-object';

const expect = chai.expect;

describe('Batch e2e test', () => {
  let navBarPage: NavBarPage;
  let batchUpdatePage: BatchUpdatePage;
  let batchComponentsPage: BatchComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Batches', async () => {
    navBarPage.getEntityPage('batch');
    batchComponentsPage = new BatchComponentsPage();
    expect(await batchComponentsPage.getTitle().getText()).to.match(/Batches/);
  });

  it('should load create Batch page', async () => {
    batchComponentsPage.clickOnCreateButton();
    batchUpdatePage = new BatchUpdatePage();
    expect(await batchUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.batch.home.createOrEditLabel/);
  });

  it('should create and save Batches', async () => {
    batchUpdatePage.batchSelectLastOption();
    batchUpdatePage.departmentSelectLastOption();
    await batchUpdatePage.save();
    expect(await batchUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
