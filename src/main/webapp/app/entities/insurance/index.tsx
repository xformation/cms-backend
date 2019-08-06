import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Insurance from './insurance';
import InsuranceDetail from './insurance-detail';
import InsuranceUpdate from './insurance-update';
import InsuranceDeleteDialog from './insurance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InsuranceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InsuranceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InsuranceDetail} />
      <ErrorBoundaryRoute path={match.url} component={Insurance} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InsuranceDeleteDialog} />
  </>
);

export default Routes;
