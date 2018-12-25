import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Section from './section';
import SectionDetail from './section-detail';
import SectionUpdate from './section-update';
import SectionDeleteDialog from './section-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SectionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SectionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Section} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SectionDeleteDialog} />
  </>
);

export default Routes;
