import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TransportRoute from './transport-route';
import TransportRouteDetail from './transport-route-detail';
import TransportRouteUpdate from './transport-route-update';
import TransportRouteDeleteDialog from './transport-route-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TransportRouteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TransportRouteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TransportRouteDetail} />
      <ErrorBoundaryRoute path={match.url} component={TransportRoute} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TransportRouteDeleteDialog} />
  </>
);

export default Routes;
