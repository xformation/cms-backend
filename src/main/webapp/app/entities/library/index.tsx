import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Library from './library';
import LibraryDetail from './library-detail';
import LibraryUpdate from './library-update';
import LibraryDeleteDialog from './library-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LibraryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LibraryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LibraryDetail} />
      <ErrorBoundaryRoute path={match.url} component={Library} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LibraryDeleteDialog} />
  </>
);

export default Routes;
