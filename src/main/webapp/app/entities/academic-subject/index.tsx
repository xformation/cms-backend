import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AcademicSubject from './academic-subject';
import AcademicSubjectDetail from './academic-subject-detail';
import AcademicSubjectUpdate from './academic-subject-update';
import AcademicSubjectDeleteDialog from './academic-subject-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AcademicSubjectUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AcademicSubjectUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AcademicSubjectDetail} />
      <ErrorBoundaryRoute path={match.url} component={AcademicSubject} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AcademicSubjectDeleteDialog} />
  </>
);

export default Routes;
