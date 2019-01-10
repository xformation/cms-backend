/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SubjectComponentsPage from './subject.page-object';
import SubjectUpdatePage from './subject-update.page-object';

const expect = chai.expect;

describe('Subject e2e test', () => {
  let navBarPage: NavBarPage;
  let subjectUpdatePage: SubjectUpdatePage;
  let subjectComponentsPage: SubjectComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Subjects', async () => {
    navBarPage.getEntityPage('subject');
    subjectComponentsPage = new SubjectComponentsPage();
    expect(await subjectComponentsPage.getTitle().getText()).to.match(/Subjects/);
  });

  it('should load create Subject page', async () => {
    subjectComponentsPage.clickOnCreateButton();
    subjectUpdatePage = new SubjectUpdatePage();
    expect(await subjectUpdatePage.getPageTitle().getAttribute('id')).to.match(/cmsApp.subject.home.createOrEditLabel/);
  });

  it('should create and save Subjects', async () => {
    subjectUpdatePage.commonSubSelectLastOption();
    subjectUpdatePage.electiveSubSelectLastOption();
    subjectUpdatePage.departmentSelectLastOption();
    subjectUpdatePage.studentSelectLastOption();
    subjectUpdatePage.teacherSelectLastOption();
    await subjectUpdatePage.save();
    expect(await subjectUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
