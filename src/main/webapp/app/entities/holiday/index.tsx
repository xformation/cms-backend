import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Holiday from './holiday';
import HolidayDetail from './holiday-detail';
import HolidayUpdate from './holiday-update';
import HolidayDeleteDialog from './holiday-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HolidayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HolidayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HolidayDetail} />
      <ErrorBoundaryRoute path={match.url} component={Holiday} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HolidayDeleteDialog} />
  </>
);

export default Routes;
