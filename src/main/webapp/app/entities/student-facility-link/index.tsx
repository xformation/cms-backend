import * as React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentFacilityLink from './student-facility-link';
import StudentFacilityLinkDetail from './student-facility-link-detail';
import StudentFacilityLinkUpdate from './student-facility-link-update';
import StudentFacilityLinkDeleteDialog from './student-facility-link-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentFacilityLinkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentFacilityLinkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentFacilityLinkDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentFacilityLink} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentFacilityLinkDeleteDialog} />
  </>
);

export default Routes;
