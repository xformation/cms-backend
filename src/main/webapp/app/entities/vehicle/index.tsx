import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Vehicle from './vehicle';
import VehicleDetail from './vehicle-detail';
import VehicleUpdate from './vehicle-update';
import VehicleDeleteDialog from './vehicle-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VehicleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VehicleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VehicleDetail} />
      <ErrorBoundaryRoute path={match.url} component={Vehicle} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={VehicleDeleteDialog} />
  </>
);

export default Routes;
