import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import College from './college';
import CollegeDetail from './college-detail';
import CollegeUpdate from './college-update';
import CollegeDeleteDialog from './college-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CollegeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CollegeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CollegeDetail} />
      <ErrorBoundaryRoute path={match.url} component={College} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CollegeDeleteDialog} />
  </>
);

export default Routes;
