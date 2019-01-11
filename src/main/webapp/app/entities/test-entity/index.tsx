import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TestEntity from './test-entity';
import TestEntityDetail from './test-entity-detail';
import TestEntityUpdate from './test-entity-update';
import TestEntityDeleteDialog from './test-entity-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TestEntityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TestEntityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TestEntityDetail} />
      <ErrorBoundaryRoute path={match.url} component={TestEntity} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TestEntityDeleteDialog} />
  </>
);

export default Routes;
