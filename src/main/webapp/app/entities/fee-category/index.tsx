import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FeeCategory from './fee-category';
import FeeCategoryDetail from './fee-category-detail';
import FeeCategoryUpdate from './fee-category-update';
import FeeCategoryDeleteDialog from './fee-category-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FeeCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FeeCategoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FeeCategoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={FeeCategory} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FeeCategoryDeleteDialog} />
  </>
);

export default Routes;
