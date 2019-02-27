import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class StudentDetail extends React.Component<IStudentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Student [<b>{studentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentName">Student Name</span>
            </dt>
            <dd>{studentEntity.studentName}</dd>
            <dt>
              <span id="studentMiddleName">Student Middle Name</span>
            </dt>
            <dd>{studentEntity.studentMiddleName}</dd>
            <dt>
              <span id="studentLastName">Student Last Name</span>
            </dt>
            <dd>{studentEntity.studentLastName}</dd>
            <dt>
              <span id="fatherName">Father Name</span>
            </dt>
            <dd>{studentEntity.fatherName}</dd>
            <dt>
              <span id="fatherMiddleName">Father Middle Name</span>
            </dt>
            <dd>{studentEntity.fatherMiddleName}</dd>
            <dt>
              <span id="fatherLastName">Father Last Name</span>
            </dt>
            <dd>{studentEntity.fatherLastName}</dd>
            <dt>
              <span id="motherName">Mother Name</span>
            </dt>
            <dd>{studentEntity.motherName}</dd>
            <dt>
              <span id="motherMiddleName">Mother Middle Name</span>
            </dt>
            <dd>{studentEntity.motherMiddleName}</dd>
            <dt>
              <span id="motherLastName">Mother Last Name</span>
            </dt>
            <dd>{studentEntity.motherLastName}</dd>
            <dt>
              <span id="aadharNo">Aadhar No</span>
            </dt>
            <dd>{studentEntity.aadharNo}</dd>
            <dt>
              <span id="dateOfBirth">Date Of Birth</span>
            </dt>
            <dd>
              <TextFormat value={studentEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="placeOfBirth">Place Of Birth</span>
            </dt>
            <dd>{studentEntity.placeOfBirth}</dd>
            <dt>
              <span id="religion">Religion</span>
            </dt>
            <dd>{studentEntity.religion}</dd>
            <dt>
              <span id="caste">Caste</span>
            </dt>
            <dd>{studentEntity.caste}</dd>
            <dt>
              <span id="subCaste">Sub Caste</span>
            </dt>
            <dd>{studentEntity.subCaste}</dd>
            <dt>
              <span id="age">Age</span>
            </dt>
            <dd>{studentEntity.age}</dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{studentEntity.sex}</dd>
            <dt>
              <span id="bloodGroup">Blood Group</span>
            </dt>
            <dd>{studentEntity.bloodGroup}</dd>
            <dt>
              <span id="addressLineOne">Address Line One</span>
            </dt>
            <dd>{studentEntity.addressLineOne}</dd>
            <dt>
              <span id="addressLineTwo">Address Line Two</span>
            </dt>
            <dd>{studentEntity.addressLineTwo}</dd>
            <dt>
              <span id="addressLineThree">Address Line Three</span>
            </dt>
            <dd>{studentEntity.addressLineThree}</dd>
            <dt>
              <span id="town">Town</span>
            </dt>
            <dd>{studentEntity.town}</dd>
            <dt>
              <span id="state">State</span>
            </dt>
            <dd>{studentEntity.state}</dd>
            <dt>
              <span id="country">Country</span>
            </dt>
            <dd>{studentEntity.country}</dd>
            <dt>
              <span id="pincode">Pincode</span>
            </dt>
            <dd>{studentEntity.pincode}</dd>
            <dt>
              <span id="studentContactNumber">Student Contact Number</span>
            </dt>
            <dd>{studentEntity.studentContactNumber}</dd>
            <dt>
              <span id="alternateContactNumber">Alternate Contact Number</span>
            </dt>
            <dd>{studentEntity.alternateContactNumber}</dd>
            <dt>
              <span id="studentEmailAddress">Student Email Address</span>
            </dt>
            <dd>{studentEntity.studentEmailAddress}</dd>
            <dt>
              <span id="alternateEmailAddress">Alternate Email Address</span>
            </dt>
            <dd>{studentEntity.alternateEmailAddress}</dd>
            <dt>
              <span id="relationWithStudent">Relation With Student</span>
            </dt>
            <dd>{studentEntity.relationWithStudent}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{studentEntity.name}</dd>
            <dt>
              <span id="middleName">Middle Name</span>
            </dt>
            <dd>{studentEntity.middleName}</dd>
            <dt>
              <span id="lastName">Last Name</span>
            </dt>
            <dd>{studentEntity.lastName}</dd>
            <dt>
              <span id="contactNo">Contact No</span>
            </dt>
            <dd>{studentEntity.contactNo}</dd>
            <dt>
              <span id="emailAddress">Email Address</span>
            </dt>
            <dd>{studentEntity.emailAddress}</dd>
            <dt>
              <span id="transport">Transport</span>
            </dt>
            <dd>{studentEntity.transport}</dd>
            <dt>
              <span id="mess">Mess</span>
            </dt>
            <dd>{studentEntity.mess}</dd>
            <dt>
              <span id="gym">Gym</span>
            </dt>
            <dd>{studentEntity.gym}</dd>
            <dt>
              <span id="culturalClass">Cultural Class</span>
            </dt>
            <dd>{studentEntity.culturalClass}</dd>
            <dt>
              <span id="library">Library</span>
            </dt>
            <dd>{studentEntity.library}</dd>
            <dt>
              <span id="sports">Sports</span>
            </dt>
            <dd>{studentEntity.sports}</dd>
            <dt>
              <span id="swimming">Swimming</span>
            </dt>
            <dd>{studentEntity.swimming}</dd>
            <dt>
              <span id="extraClass">Extra Class</span>
            </dt>
            <dd>{studentEntity.extraClass}</dd>
            <dt>
              <span id="handicrafts">Handicrafts</span>
            </dt>
            <dd>{studentEntity.handicrafts}</dd>
            <dt>
              <span id="add">Add</span>
            </dt>
            <dd>{studentEntity.add}</dd>
            <dt>
              <span id="uploadPhoto">Upload Photo</span>
            </dt>
            <dd>{studentEntity.uploadPhoto}</dd>
            <dt>
              <span id="admissionNo">Admission No</span>
            </dt>
            <dd>{studentEntity.admissionNo}</dd>
            <dt>
              <span id="rollNo">Roll No</span>
            </dt>
            <dd>{studentEntity.rollNo}</dd>
            <dt>
              <span id="studentType">Student Type</span>
            </dt>
            <dd>{studentEntity.studentType}</dd>
            <dt>Department</dt>
            <dd>{studentEntity.departmentId ? studentEntity.departmentId : ''}</dd>
            <dt>Batch</dt>
            <dd>{studentEntity.batchId ? studentEntity.batchId : ''}</dd>
            <dt>Section</dt>
            <dd>{studentEntity.sectionId ? studentEntity.sectionId : ''}</dd>
            <dt>Branch</dt>
            <dd>{studentEntity.branchId ? studentEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student/${studentEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ student }: IRootState) => ({
  studentEntity: student.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentDetail);
