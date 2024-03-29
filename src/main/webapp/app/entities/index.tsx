import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import College from './college';
import Branch from './branch';
import Department from './department';
import Batch from './batch';
import Subject from './subject';
import Section from './section';
import Term from './term';
import Student from './student';
import Teacher from './teacher';
import AcademicYear from './academic-year';
import Holiday from './holiday';
import Teach from './teach';
import CourseOffer from './course-offer';
import AttendanceMaster from './attendance-master';
import Lecture from './lecture';
import StudentAttendance from './student-attendance';
// import StudentSubject from './student-subject';
import Location from './location';
import LegalEntity from './legal-entity';
import AuthorizedSignatory from './authorized-signatory';
import BankAccounts from './bank-accounts';
// import SignatoryLink from './signatory-link';
import Country from './country';
import Currency from './currency';
import State from './state';
import City from './city';
import AdminOverview from './admin-overview';
import AdminAttendance from './admin-attendance';
import FeeCategory from './fee-category';
import Facility from './facility';
import TransportRoute from './transport-route';
import FeeDetails from './fee-details';
import DueDate from './due-date';
import PaymentRemainder from './payment-remainder';
import LateFee from './late-fee';
import Invoice from './invoice';
import StudentFee from './student-fee';
import Reports from './reports';
import IdCard from './id-card';
import Documents from './documents';
import CompetitiveExam from './competitive-exam';
import AcademicHistory from './academic-history';
import AdmissionApplication from './admission-application';
import AdmissionEnquiry from './admission-enquiry';
import AcademicExamSetting from './academic-exam-setting';
import MetaLecture from './meta-lecture';
import TypeOfGrading from './type-of-grading';
import StudentExamReport from './student-exam-report';
import Vehicle from './vehicle';
import Employee from './employee';
import Library from './library';
import StudentFacilityLink from './student-facility-link';
import Contract from './contract';
import Insurance from './insurance';
import Modules from './modules';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/college`} component={College} />
      <ErrorBoundaryRoute path={`${match.url}/branch`} component={Branch} />
      <ErrorBoundaryRoute path={`${match.url}/department`} component={Department} />
      <ErrorBoundaryRoute path={`${match.url}/batch`} component={Batch} />
      <ErrorBoundaryRoute path={`${match.url}/subject`} component={Subject} />
      <ErrorBoundaryRoute path={`${match.url}/section`} component={Section} />
      <ErrorBoundaryRoute path={`${match.url}/term`} component={Term} />
      <ErrorBoundaryRoute path={`${match.url}/student`} component={Student} />
      <ErrorBoundaryRoute path={`${match.url}/teacher`} component={Teacher} />
      <ErrorBoundaryRoute path={`${match.url}/academic-year`} component={AcademicYear} />
      <ErrorBoundaryRoute path={`${match.url}/holiday`} component={Holiday} />
      <ErrorBoundaryRoute path={`${match.url}/teach`} component={Teach} />
      <ErrorBoundaryRoute path={`${match.url}/course-offer`} component={CourseOffer} />
      <ErrorBoundaryRoute path={`${match.url}/attendance-master`} component={AttendanceMaster} />
      <ErrorBoundaryRoute path={`${match.url}/lecture`} component={Lecture} />
      <ErrorBoundaryRoute path={`${match.url}/student-attendance`} component={StudentAttendance} />
      {/* <ErrorBoundaryRoute path={`${match.url}/student-subject`} component={StudentSubject} /> */}
      <ErrorBoundaryRoute path={`${match.url}/location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}/legal-entity`} component={LegalEntity} />
      <ErrorBoundaryRoute path={`${match.url}/authorized-signatory`} component={AuthorizedSignatory} />
      <ErrorBoundaryRoute path={`${match.url}/bank-accounts`} component={BankAccounts} />
      {/* <ErrorBoundaryRoute path={`${match.url}/signatory-link`} component={SignatoryLink} /> */}
      <ErrorBoundaryRoute path={`${match.url}/country`} component={Country} />
      <ErrorBoundaryRoute path={`${match.url}/currency`} component={Currency} />
      <ErrorBoundaryRoute path={`${match.url}/state`} component={State} />
      <ErrorBoundaryRoute path={`${match.url}/city`} component={City} />
      <ErrorBoundaryRoute path={`${match.url}/admin-overview`} component={AdminOverview} />
      <ErrorBoundaryRoute path={`${match.url}/admin-attendance`} component={AdminAttendance} />
      <ErrorBoundaryRoute path={`${match.url}/fee-category`} component={FeeCategory} />
      <ErrorBoundaryRoute path={`${match.url}/facility`} component={Facility} />
      <ErrorBoundaryRoute path={`${match.url}/transport-route`} component={TransportRoute} />
      <ErrorBoundaryRoute path={`${match.url}/fee-details`} component={FeeDetails} />
      <ErrorBoundaryRoute path={`${match.url}/due-date`} component={DueDate} />
      <ErrorBoundaryRoute path={`${match.url}/payment-remainder`} component={PaymentRemainder} />
      <ErrorBoundaryRoute path={`${match.url}/late-fee`} component={LateFee} />
      <ErrorBoundaryRoute path={`${match.url}/invoice`} component={Invoice} />
      <ErrorBoundaryRoute path={`${match.url}/student-fee`} component={StudentFee} />
      <ErrorBoundaryRoute path={`${match.url}/reports`} component={Reports} />
      <ErrorBoundaryRoute path={`${match.url}/id-card`} component={IdCard} />
      <ErrorBoundaryRoute path={`${match.url}/documents`} component={Documents} />
      <ErrorBoundaryRoute path={`${match.url}/competitive-exam`} component={CompetitiveExam} />
      <ErrorBoundaryRoute path={`${match.url}/academic-history`} component={AcademicHistory} />
      <ErrorBoundaryRoute path={`${match.url}/admission-application`} component={AdmissionApplication} />
      <ErrorBoundaryRoute path={`${match.url}/admission-enquiry`} component={AdmissionEnquiry} />
      <ErrorBoundaryRoute path={`${match.url}/academic-exam-setting`} component={AcademicExamSetting} />
      <ErrorBoundaryRoute path={`${match.url}/meta-lecture`} component={MetaLecture} />
      <ErrorBoundaryRoute path={`${match.url}/type-of-grading`} component={TypeOfGrading} />
      <ErrorBoundaryRoute path={`${match.url}/student-exam-report`} component={StudentExamReport} />
      <ErrorBoundaryRoute path={`${match.url}/vehicle`} component={Vehicle} />
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      <ErrorBoundaryRoute path={`${match.url}/library`} component={Library} />
      <ErrorBoundaryRoute path={`${match.url}/student-facility-link`} component={StudentFacilityLink} />
      <ErrorBoundaryRoute path={`${match.url}/contract`} component={Contract} />
      <ErrorBoundaryRoute path={`${match.url}/insurance`} component={Insurance} />
      <ErrorBoundaryRoute path={`${match.url}/modules`} component={Modules} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
