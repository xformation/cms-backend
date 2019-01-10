import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AttendanceMaster from './attendance-master';
import AttendanceMasterDetail from './attendance-master-detail';
import AttendanceMasterUpdate from './attendance-master-update';
import AttendanceMasterDeleteDialog from './attendance-master-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AttendanceMasterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AttendanceMasterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AttendanceMasterDetail} />
      <ErrorBoundaryRoute path={match.url} component={AttendanceMaster} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AttendanceMasterDeleteDialog} />
  </>
);

export default Routes;
