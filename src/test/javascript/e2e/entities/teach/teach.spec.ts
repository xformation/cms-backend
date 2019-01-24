/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TeachComponentsPage from './teach.page-object';
import TeachUpdatePage from './teach-update.page-object';

const expect = chai.expect;

describe('Teach e2e test', () => {
  let navBarPage: NavBarPage;
  let teachUpdatePage: TeachUpdatePage;
  let teachComponentsPage: TeachComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Teaches', async () => {
    navBarPage.getEntityPage('teach');
    teachComponentsPage = new TeachComponentsPage();
    expect(await teachComponentsPage.getTitle().getText()).to.match(/Teaches/);
  });

  it('should load create Teach page', async () => {
    teachComponentsPage.clickOnCreateButton();
    teachUpdatePage = new TeachUpdatePage();
    expect(await teachUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Teach/);
  });

  it('should create and save Teaches', async () => {
    teachUpdatePage.setDescInput('desc');
    expect(await teachUpdatePage.getDescInput()).to.match(/desc/);
    teachUpdatePage.subjectSelectLastOption();
    teachUpdatePage.teacherSelectLastOption();
    await teachUpdatePage.save();
    expect(await teachUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
