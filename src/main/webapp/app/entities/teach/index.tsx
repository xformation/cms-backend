import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Teach from './teach';
import TeachDetail from './teach-detail';
import TeachUpdate from './teach-update';
import TeachDeleteDialog from './teach-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TeachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TeachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TeachDetail} />
      <ErrorBoundaryRoute path={match.url} component={Teach} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TeachDeleteDialog} />
  </>
);

export default Routes;
