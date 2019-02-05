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
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
