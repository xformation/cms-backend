import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CollegeBranches from './college-branches';
import CollegeBranchesDetail from './college-branches-detail';
import CollegeBranchesUpdate from './college-branches-update';
import CollegeBranchesDeleteDialog from './college-branches-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CollegeBranchesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CollegeBranchesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CollegeBranchesDetail} />
      <ErrorBoundaryRoute path={match.url} component={CollegeBranches} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CollegeBranchesDeleteDialog} />
  </>
);

export default Routes;
