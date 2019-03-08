import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <DropdownItem tag={Link} to="/entity/college">
      <FontAwesomeIcon icon="asterisk" />&nbsp;College
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/department">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Department
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/batch">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Batch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/section">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Section
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/term">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Term
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/academic-year">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Academic Year
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/holiday">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Holiday
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/course-offer">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Course Offer
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-attendance">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-subject">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/location">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Location
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/bank-accounts">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Bank Accounts
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-attendance">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/signatory-link">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Signatory Link
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/signatory-link">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Signatory Link
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/college">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;College
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/admin-overview">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Admin Overview
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/admin-attendance">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Admin Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transport-route">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Transport Route
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-details">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Details
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/due-date">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Due Date
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment-remainder">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Payment Remainder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/late-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Late Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transport-route">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Transport Route
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-details">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Details
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/due-date">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Due Date
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment-remainder">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Payment Remainder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/late-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Late Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/academic-year">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Academic Year
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/academic-year">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Academic Year
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transport-route">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Transport Route
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-details">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Details
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/due-date">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Due Date
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment-remainder">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Payment Remainder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/late-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Late Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/reports">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Reports
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/id-card">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Id Card
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/documents">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Documents
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/department">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Department
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/batch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Batch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/section">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Section
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/term">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Term
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/holiday">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Holiday
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-attendance">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/bank-accounts">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Bank Accounts
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/department">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Department
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/batch">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Batch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/section">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Section
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/term">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Term
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/holiday">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Holiday
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-attendance">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/bank-accounts">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Bank Accounts
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transport-route">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Transport Route
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-details">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Details
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/due-date">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Due Date
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment-remainder">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Payment Remainder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/late-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Late Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/branch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Branch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/department">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Department
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/batch">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Batch
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/subject">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Subject
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/section">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Section
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/term">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Term
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teacher
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/holiday">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Holiday
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teach">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teach
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/attendance-master">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Attendance Master
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/lecture">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Lecture
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/student-attendance">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Student Attendance
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/legal-entity">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Legal Entity
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/authorized-signatory">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Authorized Signatory
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/bank-accounts">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Bank Accounts
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/country">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Country
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/currency">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Currency
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/state">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;State
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/city">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;City
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/facility">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Facility
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/transport-route">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Transport Route
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/fee-details">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Fee Details
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/due-date">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Due Date
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/payment-remainder">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Payment Remainder
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/late-fee">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Late Fee
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/invoice">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Invoice
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/competitive-exam">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Competitive Exam
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/documents">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Documents
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/teacher">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;Teacher
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
