import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdminAttendance from './admin-attendance';
import AdminAttendanceDetail from './admin-attendance-detail';
import AdminAttendanceUpdate from './admin-attendance-update';
import AdminAttendanceDeleteDialog from './admin-attendance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdminAttendanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdminAttendanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdminAttendanceDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdminAttendance} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AdminAttendanceDeleteDialog} />
  </>
);

export default Routes;
