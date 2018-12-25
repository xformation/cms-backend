import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LegalEntity from './legal-entity';
import LegalEntityDetail from './legal-entity-detail';
import LegalEntityUpdate from './legal-entity-update';
import LegalEntityDeleteDialog from './legal-entity-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LegalEntityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LegalEntityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LegalEntityDetail} />
      <ErrorBoundaryRoute path={match.url} component={LegalEntity} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LegalEntityDeleteDialog} />
  </>
);

export default Routes;
