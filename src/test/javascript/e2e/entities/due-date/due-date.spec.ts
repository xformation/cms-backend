/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import DueDateComponentsPage from './due-date.page-object';
import DueDateUpdatePage from './due-date-update.page-object';

const expect = chai.expect;

describe('DueDate e2e test', () => {
  let navBarPage: NavBarPage;
  let dueDateUpdatePage: DueDateUpdatePage;
  let dueDateComponentsPage: DueDateComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load DueDates', async () => {
    navBarPage.getEntityPage('due-date');
    dueDateComponentsPage = new DueDateComponentsPage();
    expect(await dueDateComponentsPage.getTitle().getText()).to.match(/Due Dates/);
  });

  it('should load create DueDate page', async () => {
    dueDateComponentsPage.clickOnCreateButton();
    dueDateUpdatePage = new DueDateUpdatePage();
    expect(await dueDateUpdatePage.getPageTitle().getText()).to.match(/Create or edit a DueDate/);
  });

  it('should create and save DueDates', async () => {
    dueDateUpdatePage.setPaymentMethodInput('paymentMethod');
    expect(await dueDateUpdatePage.getPaymentMethodInput()).to.match(/paymentMethod/);
    dueDateUpdatePage.setInstallmentsInput('5');
    expect(await dueDateUpdatePage.getInstallmentsInput()).to.eq('5');
    dueDateUpdatePage.setDayDescInput('dayDesc');
    expect(await dueDateUpdatePage.getDayDescInput()).to.match(/dayDesc/);
    dueDateUpdatePage.frequencySelectLastOption();
    dueDateUpdatePage.collegeSelectLastOption();
    dueDateUpdatePage.branchSelectLastOption();
    await dueDateUpdatePage.save();
    expect(await dueDateUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
