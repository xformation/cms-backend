import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PaymentRemainder from './payment-remainder';
import PaymentRemainderDetail from './payment-remainder-detail';
import PaymentRemainderUpdate from './payment-remainder-update';
import PaymentRemainderDeleteDialog from './payment-remainder-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PaymentRemainderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PaymentRemainderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PaymentRemainderDetail} />
      <ErrorBoundaryRoute path={match.url} component={PaymentRemainder} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PaymentRemainderDeleteDialog} />
  </>
);

export default Routes;
