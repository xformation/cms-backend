/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import CompetitiveExamComponentsPage from './competitive-exam.page-object';
import CompetitiveExamUpdatePage from './competitive-exam-update.page-object';

const expect = chai.expect;

describe('CompetitiveExam e2e test', () => {
  let navBarPage: NavBarPage;
  let competitiveExamUpdatePage: CompetitiveExamUpdatePage;
  let competitiveExamComponentsPage: CompetitiveExamComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load CompetitiveExams', async () => {
    navBarPage.getEntityPage('competitive-exam');
    competitiveExamComponentsPage = new CompetitiveExamComponentsPage();
    expect(await competitiveExamComponentsPage.getTitle().getText()).to.match(/Competitive Exams/);
  });

  it('should load create CompetitiveExam page', async () => {
    competitiveExamComponentsPage.clickOnCreateButton();
    competitiveExamUpdatePage = new CompetitiveExamUpdatePage();
    expect(await competitiveExamUpdatePage.getPageTitle().getText()).to.match(/Create or edit a CompetitiveExam/);
  });

  it('should create and save CompetitiveExams', async () => {
    competitiveExamUpdatePage.setTestNameInput('testName');
    expect(await competitiveExamUpdatePage.getTestNameInput()).to.match(/testName/);
    competitiveExamUpdatePage.setTestScoreInput('5');
    expect(await competitiveExamUpdatePage.getTestScoreInput()).to.eq('5');
    competitiveExamUpdatePage.setEnrollmentNoInput('5');
    expect(await competitiveExamUpdatePage.getEnrollmentNoInput()).to.eq('5');
    competitiveExamUpdatePage.setRankInput('5');
    expect(await competitiveExamUpdatePage.getRankInput()).to.eq('5');
    competitiveExamUpdatePage.studentSelectLastOption();
    await competitiveExamUpdatePage.save();
    expect(await competitiveExamUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
