import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdmissionEnquiry from './admission-enquiry';
import AdmissionEnquiryDetail from './admission-enquiry-detail';
import AdmissionEnquiryUpdate from './admission-enquiry-update';
import AdmissionEnquiryDeleteDialog from './admission-enquiry-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdmissionEnquiryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdmissionEnquiryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdmissionEnquiryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdmissionEnquiry} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AdmissionEnquiryDeleteDialog} />
  </>
);

export default Routes;
