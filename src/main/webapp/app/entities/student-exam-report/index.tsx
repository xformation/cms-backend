import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentExamReport from './student-exam-report';
import StudentExamReportDetail from './student-exam-report-detail';
import StudentExamReportUpdate from './student-exam-report-update';
import StudentExamReportDeleteDialog from './student-exam-report-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StudentExamReportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StudentExamReportUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentExamReportDetail} />
      <ErrorBoundaryRoute path={match.url} component={StudentExamReport} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={StudentExamReportDeleteDialog} />
  </>
);

export default Routes;
