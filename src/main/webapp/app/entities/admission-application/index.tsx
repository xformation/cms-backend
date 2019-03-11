import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdmissionApplication from './admission-application';
import AdmissionApplicationDetail from './admission-application-detail';
import AdmissionApplicationUpdate from './admission-application-update';
import AdmissionApplicationDeleteDialog from './admission-application-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdmissionApplicationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdmissionApplicationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdmissionApplicationDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdmissionApplication} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AdmissionApplicationDeleteDialog} />
  </>
);

export default Routes;
