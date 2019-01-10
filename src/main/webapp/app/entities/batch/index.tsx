import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Batch from './batch';
import BatchDetail from './batch-detail';
import BatchUpdate from './batch-update';
import BatchDeleteDialog from './batch-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BatchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BatchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BatchDetail} />
      <ErrorBoundaryRoute path={match.url} component={Batch} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BatchDeleteDialog} />
  </>
);

export default Routes;
