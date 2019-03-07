import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CompetitiveExam from './competitive-exam';
import CompetitiveExamDetail from './competitive-exam-detail';
import CompetitiveExamUpdate from './competitive-exam-update';
import CompetitiveExamDeleteDialog from './competitive-exam-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CompetitiveExamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CompetitiveExamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CompetitiveExamDetail} />
      <ErrorBoundaryRoute path={match.url} component={CompetitiveExam} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CompetitiveExamDeleteDialog} />
  </>
);

export default Routes;
