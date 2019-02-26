import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Documents from './documents';
import DocumentsDetail from './documents-detail';
import DocumentsUpdate from './documents-update';
import DocumentsDeleteDialog from './documents-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DocumentsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DocumentsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DocumentsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Documents} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DocumentsDeleteDialog} />
  </>
);

export default Routes;
