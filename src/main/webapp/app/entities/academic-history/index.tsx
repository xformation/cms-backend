import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AcademicHistory from './academic-history';
import AcademicHistoryDetail from './academic-history-detail';
import AcademicHistoryUpdate from './academic-history-update';
import AcademicHistoryDeleteDialog from './academic-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AcademicHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AcademicHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AcademicHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AcademicHistory} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AcademicHistoryDeleteDialog} />
  </>
);

export default Routes;
