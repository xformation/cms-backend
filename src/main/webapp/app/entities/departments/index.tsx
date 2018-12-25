import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Departments from './departments';
import DepartmentsDetail from './departments-detail';
import DepartmentsUpdate from './departments-update';
import DepartmentsDeleteDialog from './departments-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DepartmentsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DepartmentsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DepartmentsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Departments} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DepartmentsDeleteDialog} />
  </>
);

export default Routes;
