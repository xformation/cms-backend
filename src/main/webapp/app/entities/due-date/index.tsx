import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DueDate from './due-date';
import DueDateDetail from './due-date-detail';
import DueDateUpdate from './due-date-update';
import DueDateDeleteDialog from './due-date-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DueDateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DueDateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DueDateDetail} />
      <ErrorBoundaryRoute path={match.url} component={DueDate} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DueDateDeleteDialog} />
  </>
);

export default Routes;
