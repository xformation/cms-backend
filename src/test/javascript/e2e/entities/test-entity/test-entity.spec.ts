/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TestEntityComponentsPage from './test-entity.page-object';
import TestEntityUpdatePage from './test-entity-update.page-object';

const expect = chai.expect;

describe('TestEntity e2e test', () => {
  let navBarPage: NavBarPage;
  let testEntityUpdatePage: TestEntityUpdatePage;
  let testEntityComponentsPage: TestEntityComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load TestEntities', async () => {
    navBarPage.getEntityPage('test-entity');
    testEntityComponentsPage = new TestEntityComponentsPage();
    expect(await testEntityComponentsPage.getTitle().getText()).to.match(/Test Entities/);
  });

  it('should load create TestEntity page', async () => {
    testEntityComponentsPage.clickOnCreateButton();
    testEntityUpdatePage = new TestEntityUpdatePage();
    expect(await testEntityUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.testEntity.home.createOrEditLabel/);
  });

  it('should create and save TestEntities', async () => {
    testEntityUpdatePage.setStudentNameInput('studentName');
    expect(await testEntityUpdatePage.getStudentNameInput()).to.match(/studentName/);
    await testEntityUpdatePage.save();
    expect(await testEntityUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
