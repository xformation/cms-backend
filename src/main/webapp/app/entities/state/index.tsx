import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import State from './state';
import StateDetail from './state-detail';
import StateUpdate from './state-update';
import StateDeleteDialog from './state-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StateDetail} />
      <ErrorBoundaryRoute path={match.url} component={State} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StateDeleteDialog} />
  </>
);

export default Routes;
