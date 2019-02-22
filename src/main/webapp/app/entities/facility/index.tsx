import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Facility from './facility';
import FacilityDetail from './facility-detail';
import FacilityUpdate from './facility-update';
import FacilityDeleteDialog from './facility-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FacilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FacilityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FacilityDetail} />
      <ErrorBoundaryRoute path={match.url} component={Facility} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FacilityDeleteDialog} />
  </>
);

export default Routes;
