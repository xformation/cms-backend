import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Term from './term';
import TermDetail from './term-detail';
import TermUpdate from './term-update';
import TermDeleteDialog from './term-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TermUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TermUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TermDetail} />
      <ErrorBoundaryRoute path={match.url} component={Term} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TermDeleteDialog} />
  </>
);

export default Routes;
