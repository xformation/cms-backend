import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LateFee from './late-fee';
import LateFeeDetail from './late-fee-detail';
import LateFeeUpdate from './late-fee-update';
import LateFeeDeleteDialog from './late-fee-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LateFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LateFeeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LateFeeDetail} />
      <ErrorBoundaryRoute path={match.url} component={LateFee} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LateFeeDeleteDialog} />
  </>
);

export default Routes;
