import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Branch from './branch';
import BranchDetail from './branch-detail';
import BranchUpdate from './branch-update';
import BranchDeleteDialog from './branch-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BranchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BranchUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BranchDetail} />
      <ErrorBoundaryRoute path={match.url} component={Branch} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BranchDeleteDialog} />
  </>
);

export default Routes;
