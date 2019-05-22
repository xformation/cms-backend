import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeOfGrading from './type-of-grading';
import TypeOfGradingDetail from './type-of-grading-detail';
import TypeOfGradingUpdate from './type-of-grading-update';
import TypeOfGradingDeleteDialog from './type-of-grading-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeOfGradingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeOfGradingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeOfGradingDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeOfGrading} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TypeOfGradingDeleteDialog} />
  </>
);

export default Routes;
