import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Semester from './semester';
import SemesterDetail from './semester-detail';
import SemesterUpdate from './semester-update';
import SemesterDeleteDialog from './semester-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SemesterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SemesterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SemesterDetail} />
      <ErrorBoundaryRoute path={match.url} component={Semester} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SemesterDeleteDialog} />
  </>
);

export default Routes;
