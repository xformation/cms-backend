import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Teacher from './teacher';
import TeacherDetail from './teacher-detail';
import TeacherUpdate from './teacher-update';
import TeacherDeleteDialog from './teacher-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TeacherUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TeacherUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TeacherDetail} />
      <ErrorBoundaryRoute path={match.url} component={Teacher} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TeacherDeleteDialog} />
  </>
);

export default Routes;
