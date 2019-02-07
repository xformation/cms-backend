import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SignatoryLink from './signatory-link';
import SignatoryLinkDetail from './signatory-link-detail';
import SignatoryLinkUpdate from './signatory-link-update';
import SignatoryLinkDeleteDialog from './signatory-link-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SignatoryLinkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SignatoryLinkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SignatoryLinkDetail} />
      <ErrorBoundaryRoute path={match.url} component={SignatoryLink} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SignatoryLinkDeleteDialog} />
  </>
);

export default Routes;
