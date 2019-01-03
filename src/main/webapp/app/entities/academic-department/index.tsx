import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AcademicDepartment from './academic-department';
import AcademicDepartmentDetail from './academic-department-detail';
import AcademicDepartmentUpdate from './academic-department-update';
import AcademicDepartmentDeleteDialog from './academic-department-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AcademicDepartmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AcademicDepartmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AcademicDepartmentDetail} />
      <ErrorBoundaryRoute path={match.url} component={AcademicDepartment} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AcademicDepartmentDeleteDialog} />
  </>
);

export default Routes;
