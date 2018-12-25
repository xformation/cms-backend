import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankAccounts from './bank-accounts';
import BankAccountsDetail from './bank-accounts-detail';
import BankAccountsUpdate from './bank-accounts-update';
import BankAccountsDeleteDialog from './bank-accounts-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BankAccountsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BankAccountsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BankAccountsDetail} />
      <ErrorBoundaryRoute path={match.url} component={BankAccounts} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BankAccountsDeleteDialog} />
  </>
);

export default Routes;
