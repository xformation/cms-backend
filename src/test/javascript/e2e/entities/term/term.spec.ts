/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import TermComponentsPage from './term.page-object';
import TermUpdatePage from './term-update.page-object';

const expect = chai.expect;

describe('Term e2e test', () => {
  let navBarPage: NavBarPage;
  let termUpdatePage: TermUpdatePage;
  let termComponentsPage: TermComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Terms', async () => {
    navBarPage.getEntityPage('term');
    termComponentsPage = new TermComponentsPage();
    expect(await termComponentsPage.getTitle().getText()).to.match(/Terms/);
  });

  it('should load create Term page', async () => {
    termComponentsPage.clickOnCreateButton();
    termUpdatePage = new TermUpdatePage();
    expect(await termUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.term.home.createOrEditLabel/);
  });

  it('should create and save Terms', async () => {
    termUpdatePage.setTermsDescInput('termsDesc');
    expect(await termUpdatePage.getTermsDescInput()).to.match(/termsDesc/);
    termUpdatePage.setStartDateInput('01-01-2001');
    expect(await termUpdatePage.getStartDateInput()).to.eq('2001-01-01');
    termUpdatePage.setEndDateInput('01-01-2001');
    expect(await termUpdatePage.getEndDateInput()).to.eq('2001-01-01');
    termUpdatePage.statusSelectLastOption();
    termUpdatePage.academicYearSelectLastOption();
    await termUpdatePage.save();
    expect(await termUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
