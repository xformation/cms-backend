import * as React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MetaLecture from './meta-lecture';
import MetaLectureDetail from './meta-lecture-detail';
import MetaLectureUpdate from './meta-lecture-update';
import MetaLectureDeleteDialog from './meta-lecture-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MetaLectureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MetaLectureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MetaLectureDetail} />
      <ErrorBoundaryRoute path={match.url} component={MetaLecture} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MetaLectureDeleteDialog} />
  </>
);

export default Routes;
