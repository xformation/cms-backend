import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Lecture from './lecture';
import LectureDetail from './lecture-detail';
import LectureUpdate from './lecture-update';
import LectureDeleteDialog from './lecture-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LectureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LectureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LectureDetail} />
      <ErrorBoundaryRoute path={match.url} component={Lecture} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LectureDeleteDialog} />
  </>
);

export default Routes;
