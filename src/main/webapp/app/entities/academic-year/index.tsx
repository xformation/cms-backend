import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AcademicYear from './academic-year';
import AcademicYearDetail from './academic-year-detail';
import AcademicYearUpdate from './academic-year-update';
import AcademicYearDeleteDialog from './academic-year-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AcademicYearUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AcademicYearUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AcademicYearDetail} />
      <ErrorBoundaryRoute path={match.url} component={AcademicYear} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AcademicYearDeleteDialog} />
  </>
);

export default Routes;
