import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdminOverview from './admin-overview';
import AdminOverviewDetail from './admin-overview-detail';
import AdminOverviewUpdate from './admin-overview-update';
import AdminOverviewDeleteDialog from './admin-overview-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdminOverviewUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdminOverviewUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdminOverviewDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdminOverview} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AdminOverviewDeleteDialog} />
  </>
);

export default Routes;
