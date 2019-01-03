import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StudentYear from './student-year';
import Section from './section';
import Semester from './semester';
import StudentAttendance from './student-attendance';
import Periods from './periods';
import Subject from './subject';
import Teacher from './teacher';
import Student from './student';
import CollegeBranches from './college-branches';
import Departments from './departments';
import Location from './location';
import College from './college';
import LegalEntity from './legal-entity';
import AuthorizedSignatory from './authorized-signatory';
import BankAccounts from './bank-accounts';
import AcademicDepartment from './academic-department';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/student-year`} component={StudentYear} />
      <ErrorBoundaryRoute path={`${match.url}/section`} component={Section} />
      <ErrorBoundaryRoute path={`${match.url}/semester`} component={Semester} />
      <ErrorBoundaryRoute path={`${match.url}/student-attendance`} component={StudentAttendance} />
      <ErrorBoundaryRoute path={`${match.url}/periods`} component={Periods} />
      <ErrorBoundaryRoute path={`${match.url}/subject`} component={Subject} />
      <ErrorBoundaryRoute path={`${match.url}/teacher`} component={Teacher} />
      <ErrorBoundaryRoute path={`${match.url}/student`} component={Student} />
      <ErrorBoundaryRoute path={`${match.url}/college-branches`} component={CollegeBranches} />
      <ErrorBoundaryRoute path={`${match.url}/departments`} component={Departments} />
      <ErrorBoundaryRoute path={`${match.url}/location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}/college`} component={College} />
      <ErrorBoundaryRoute path={`${match.url}/legal-entity`} component={LegalEntity} />
      <ErrorBoundaryRoute path={`${match.url}/authorized-signatory`} component={AuthorizedSignatory} />
      <ErrorBoundaryRoute path={`${match.url}/bank-accounts`} component={BankAccounts} />
      <ErrorBoundaryRoute path={`${match.url}/academic-department`} component={AcademicDepartment} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
