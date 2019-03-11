/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import DocumentsComponentsPage from './documents.page-object';
import DocumentsUpdatePage from './documents-update.page-object';

const expect = chai.expect;

describe('Documents e2e test', () => {
  let navBarPage: NavBarPage;
  let documentsUpdatePage: DocumentsUpdatePage;
  let documentsComponentsPage: DocumentsComponentsPage;

  before(() => {
    browser.get('/');
    navBarPage = new NavBarPage();
    navBarPage.autoSignIn();
  });

  it('should load Documents', async () => {
    navBarPage.getEntityPage('documents');
    documentsComponentsPage = new DocumentsComponentsPage();
    expect(await documentsComponentsPage.getTitle().getText()).to.match(/Documents/);
  });

  it('should load create Documents page', async () => {
    documentsComponentsPage.clickOnCreateButton();
    documentsUpdatePage = new DocumentsUpdatePage();
    expect(await documentsUpdatePage.getPageTitle().getText()).to.match(/Create or edit a Documents/);
  });

  it('should create and save Documents', async () => {
    documentsUpdatePage.setDocumentNameInput('documentName');
    expect(await documentsUpdatePage.getDocumentNameInput()).to.match(/documentName/);
    documentsUpdatePage.setUploadInput('upload');
    expect(await documentsUpdatePage.getUploadInput()).to.match(/upload/);
    documentsUpdatePage.studentSelectLastOption();
    await documentsUpdatePage.save();
    expect(await documentsUpdatePage.getSaveButton().isPresent()).to.be.false;
  });

  after(() => {
    navBarPage.autoSignOut();
  });
});
