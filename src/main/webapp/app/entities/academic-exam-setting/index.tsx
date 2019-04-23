import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AcademicExamSetting from './academic-exam-setting';
import AcademicExamSettingDetail from './academic-exam-setting-detail';
import AcademicExamSettingUpdate from './academic-exam-setting-update';
import AcademicExamSettingDeleteDialog from './academic-exam-setting-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AcademicExamSettingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AcademicExamSettingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AcademicExamSettingDetail} />
      <ErrorBoundaryRoute path={match.url} component={AcademicExamSetting} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AcademicExamSettingDeleteDialog} />
  </>
);

export default Routes;
