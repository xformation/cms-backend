import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Student from './student';
import StudentDetail from './student-detail';
import StudentUpdate from './student-update';
import StudentDeleteDialog from './student-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Student} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentDeleteDialog} />
  </>
);

export default Routes;
