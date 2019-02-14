import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Currency from './currency';
import CurrencyDetail from './currency-detail';
import CurrencyUpdate from './currency-update';
import CurrencyDeleteDialog from './currency-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CurrencyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CurrencyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CurrencyDetail} />
      <ErrorBoundaryRoute path={match.url} component={Currency} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CurrencyDeleteDialog} />
  </>
);

export default Routes;
