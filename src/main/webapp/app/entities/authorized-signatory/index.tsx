import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AuthorizedSignatory from './authorized-signatory';
import AuthorizedSignatoryDetail from './authorized-signatory-detail';
import AuthorizedSignatoryUpdate from './authorized-signatory-update';
import AuthorizedSignatoryDeleteDialog from './authorized-signatory-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AuthorizedSignatoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AuthorizedSignatoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AuthorizedSignatoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AuthorizedSignatory} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AuthorizedSignatoryDeleteDialog} />
  </>
);

export default Routes;
