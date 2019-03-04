/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SectionComponentsPage from './section.page-object';
import SectionUpdatePage from './section-update.page-object';

const expect = chai.expect;

describe('Section e2e test', () => {
  let navBarPage: NavBarPage;
  let sectionUpdatePage: SectionUpdatePage;
  let sectionComponentsPage: SectionComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Sections', async () => {
    navBarPage.getEntityPage('section');
    sectionComponentsPage = new SectionComponentsPage();
    expect(await sectionComponentsPage.getTitle().getText()).to.match(/Sections/);
  });

  it('should load create Section page', async () => {
    sectionComponentsPage.clickOnCreateButton();
    sectionUpdatePage = new SectionUpdatePage();
    expect(await sectionUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Section/);
  });

  it('should create and save Sections', async () => {
    sectionUpdatePage.sectionSelectLastOption();
    sectionUpdatePage.batchSelectLastOption();
    await sectionUpdatePage.save();
    expect(await sectionUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
