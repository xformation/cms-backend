import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CourseOffer from './course-offer';
import CourseOfferDetail from './course-offer-detail';
import CourseOfferUpdate from './course-offer-update';
import CourseOfferDeleteDialog from './course-offer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CourseOfferUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CourseOfferUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CourseOfferDetail} />
      <ErrorBoundaryRoute path={match.url} component={CourseOffer} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CourseOfferDeleteDialog} />
  </>
);

export default Routes;
