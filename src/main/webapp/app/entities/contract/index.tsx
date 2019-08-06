import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Contract from './contract';
import ContractDetail from './contract-detail';
import ContractUpdate from './contract-update';
import ContractDeleteDialog from './contract-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractDetail} />
      <ErrorBoundaryRoute path={match.url} component={Contract} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ContractDeleteDialog} />
  </>
);

export default Routes;
