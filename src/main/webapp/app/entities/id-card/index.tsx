import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IdCard from './id-card';
import IdCardDetail from './id-card-detail';
import IdCardUpdate from './id-card-update';
import IdCardDeleteDialog from './id-card-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IdCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IdCardUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IdCardDetail} />
      <ErrorBoundaryRoute path={match.url} component={IdCard} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IdCardDeleteDialog} />
  </>
);

export default Routes;
