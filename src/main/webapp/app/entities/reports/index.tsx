import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Reports from './reports';
import ReportsDetail from './reports-detail';
import ReportsUpdate from './reports-update';
import ReportsDeleteDialog from './reports-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ReportsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ReportsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ReportsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Reports} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ReportsDeleteDialog} />
  </>
);

export default Routes;
