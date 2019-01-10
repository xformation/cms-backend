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
import AcademicSubject from './academic-subject';
import AcademicYear from './academic-year';
import Holiday from './holiday';
import Term from './term';
import Branch from './branch';
import Department from './department';
import Batch from './batch';
import Teach from './teach';
import CourseOffer from './course-offer';
import AttendanceMaster from './attendance-master';
import Lecture from './lecture';
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
      <ErrorBoundaryRoute path={`${match.url}/academic-subject`} component={AcademicSubject} />
      <ErrorBoundaryRoute path={`${match.url}/academic-year`} component={AcademicYear} />
      <ErrorBoundaryRoute path={`${match.url}/holiday`} component={Holiday} />
      <ErrorBoundaryRoute path={`${match.url}/term`} component={Term} />
      <ErrorBoundaryRoute path={`${match.url}/branch`} component={Branch} />
      <ErrorBoundaryRoute path={`${match.url}/department`} component={Department} />
      <ErrorBoundaryRoute path={`${match.url}/batch`} component={Batch} />
      <ErrorBoundaryRoute path={`${match.url}/teach`} component={Teach} />
      <ErrorBoundaryRoute path={`${match.url}/course-offer`} component={CourseOffer} />
      <ErrorBoundaryRoute path={`${match.url}/attendance-master`} component={AttendanceMaster} />
      <ErrorBoundaryRoute path={`${match.url}/lecture`} component={Lecture} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
