/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import HolidayComponentsPage from './holiday.page-object';
import HolidayUpdatePage from './holiday-update.page-object';

const expect = chai.expect;

describe('Holiday e2e test', () => {
  let navBarPage: NavBarPage;
  let holidayUpdatePage: HolidayUpdatePage;
  let holidayComponentsPage: HolidayComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Holidays', async () => {
    navBarPage.getEntityPage('holiday');
    holidayComponentsPage = new HolidayComponentsPage();
    expect(await holidayComponentsPage.getTitle().getText()).to.match(/Holidays/);
  });

  it('should load create Holiday page', async () => {
    holidayComponentsPage.clickOnCreateButton();
    holidayUpdatePage = new HolidayUpdatePage();
    expect(await holidayUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.holiday.home.createOrEditLabel/);
  });

  it('should create and save Holidays', async () => {
    holidayUpdatePage.setHolidayDescInput('holidayDesc');
    expect(await holidayUpdatePage.getHolidayDescInput()).to.match(/holidayDesc/);
    holidayUpdatePage.setHolidayDateInput('01-01-2001');
    expect(await holidayUpdatePage.getHolidayDateInput()).to.eq('2001-01-01');
    holidayUpdatePage.statusSelectLastOption();
    holidayUpdatePage.academicYearSelectLastOption();
    await holidayUpdatePage.save();
    expect(await holidayUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
