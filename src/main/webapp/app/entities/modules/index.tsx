import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Modules from './modules';
import ModulesDetail from './modules-detail';
import ModulesUpdate from './modules-update';
import ModulesDeleteDialog from './modules-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ModulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ModulesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ModulesDetail} />
      <ErrorBoundaryRoute path={match.url} component={Modules} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ModulesDeleteDialog} />
  </>
);

export default Routes;
