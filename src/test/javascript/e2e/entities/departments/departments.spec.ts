/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import DepartmentsComponentsPage from './departments.page-object';
import DepartmentsUpdatePage from './departments-update.page-object';

const expect = chai.expect;

describe('Departments e2e test', () => {
  let navBarPage: NavBarPage;
  let departmentsUpdatePage: DepartmentsUpdatePage;
  let departmentsComponentsPage: DepartmentsComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Departments', async () => {
    navBarPage.getEntityPage('departments');
    departmentsComponentsPage = new DepartmentsComponentsPage();
    expect(await departmentsComponentsPage.getTitle().getText()).to.match(/Departments/);
  });

  it('should load create Departments page', async () => {
    departmentsComponentsPage.clickOnCreateButton();
    departmentsUpdatePage = new DepartmentsUpdatePage();
    expect(await departmentsUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.departments.home.createOrEditLabel/);
  });

  it('should create and save Departments', async () => {
    departmentsUpdatePage.setNameInput('name');
    expect(await departmentsUpdatePage.getNameInput()).to.match(/name/);
    departmentsUpdatePage.setDescriptionInput('description');
    expect(await departmentsUpdatePage.getDescriptionInput()).to.match(/description/);
    departmentsUpdatePage.setDeptHeadInput('deptHead');
    expect(await departmentsUpdatePage.getDeptHeadInput()).to.match(/deptHead/);
    await departmentsUpdatePage.save();
    expect(await departmentsUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
