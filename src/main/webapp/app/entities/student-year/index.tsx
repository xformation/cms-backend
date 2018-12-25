import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentYear from './student-year';
import StudentYearDetail from './student-year-detail';
import StudentYearUpdate from './student-year-update';
import StudentYearDeleteDialog from './student-year-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentYearUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentYearUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentYearDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentYear} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentYearDeleteDialog} />
  </>
);

export default Routes;
