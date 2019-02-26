import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentFee from './student-fee';
import StudentFeeDetail from './student-fee-detail';
import StudentFeeUpdate from './student-fee-update';
import StudentFeeDeleteDialog from './student-fee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentFeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentFee} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentFeeDeleteDialog} />
  </>
);

export default Routes;
