import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentAttendance from './student-attendance';
import StudentAttendanceDetail from './student-attendance-detail';
import StudentAttendanceUpdate from './student-attendance-update';
import StudentAttendanceDeleteDialog from './student-attendance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentAttendanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentAttendanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentAttendanceDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentAttendance} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentAttendanceDeleteDialog} />
  </>
);

export default Routes;
