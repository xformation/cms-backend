import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FeeDetails from './fee-details';
import FeeDetailsDetail from './fee-details-detail';
import FeeDetailsUpdate from './fee-details-update';
import FeeDetailsDeleteDialog from './fee-details-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FeeDetailsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FeeDetailsDetail} />
      <ErrorBoundaryRoute path={match.url} component={FeeDetails} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FeeDetailsDeleteDialog} />
  </>
);

export default Routes;
