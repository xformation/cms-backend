import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Periods from './periods';
import PeriodsDetail from './periods-detail';
import PeriodsUpdate from './periods-update';
import PeriodsDeleteDialog from './periods-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PeriodsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PeriodsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PeriodsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Periods} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PeriodsDeleteDialog} />
  </>
);

export default Routes;
