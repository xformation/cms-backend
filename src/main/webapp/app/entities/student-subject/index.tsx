import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentSubject from './student-subject';
import StudentSubjectDetail from './student-subject-detail';
import StudentSubjectUpdate from './student-subject-update';
import StudentSubjectDeleteDialog from './student-subject-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentSubjectUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentSubjectUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentSubjectDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentSubject} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentSubjectDeleteDialog} />
  </>
);

export default Routes;
