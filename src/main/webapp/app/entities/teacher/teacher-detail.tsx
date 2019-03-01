import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './teacher.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeacherDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class TeacherDetail extends React.Component<ITeacherDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { teacherEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Teacher [<b>{teacherEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="teacherName">Teacher Name</span>
            </dt>
            <dd>{teacherEntity.teacherName}</dd>
            <dt>
              <span id="teacherMiddleName">Teacher Middle Name</span>
            </dt>
            <dd>{teacherEntity.teacherMiddleName}</dd>
            <dt>
              <span id="teacherLastName">Teacher Last Name</span>
            </dt>
            <dd>{teacherEntity.teacherLastName}</dd>
            <dt>
              <span id="fatherName">Father Name</span>
            </dt>
            <dd>{teacherEntity.fatherName}</dd>
            <dt>
              <span id="fatherMiddleName">Father Middle Name</span>
            </dt>
            <dd>{teacherEntity.fatherMiddleName}</dd>
            <dt>
              <span id="fatherLastName">Father Last Name</span>
            </dt>
            <dd>{teacherEntity.fatherLastName}</dd>
            <dt>
              <span id="motherName">Mother Name</span>
            </dt>
            <dd>{teacherEntity.motherName}</dd>
            <dt>
              <span id="motherMiddleName">Mother Middle Name</span>
            </dt>
            <dd>{teacherEntity.motherMiddleName}</dd>
            <dt>
              <span id="motherLastName">Mother Last Name</span>
            </dt>
            <dd>{teacherEntity.motherLastName}</dd>
            <dt>
              <span id="aadharNo">Aadhar No</span>
            </dt>
            <dd>{teacherEntity.aadharNo}</dd>
            <dt>
              <span id="dateOfBirth">Date Of Birth</span>
            </dt>
            <dd>
              <TextFormat value={teacherEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="placeOfBirth">Place Of Birth</span>
            </dt>
            <dd>{teacherEntity.placeOfBirth}</dd>
            <dt>
              <span id="religion">Religion</span>
            </dt>
            <dd>{teacherEntity.religion}</dd>
            <dt>
              <span id="caste">Caste</span>
            </dt>
            <dd>{teacherEntity.caste}</dd>
            <dt>
              <span id="subCaste">Sub Caste</span>
            </dt>
            <dd>{teacherEntity.subCaste}</dd>
            <dt>
              <span id="age">Age</span>
            </dt>
            <dd>{teacherEntity.age}</dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{teacherEntity.sex}</dd>
            <dt>
              <span id="bloodGroup">Blood Group</span>
            </dt>
            <dd>{teacherEntity.bloodGroup}</dd>
            <dt>
              <span id="addressLineOne">Address Line One</span>
            </dt>
            <dd>{teacherEntity.addressLineOne}</dd>
            <dt>
              <span id="addressLineTwo">Address Line Two</span>
            </dt>
            <dd>{teacherEntity.addressLineTwo}</dd>
            <dt>
              <span id="addressLineThree">Address Line Three</span>
            </dt>
            <dd>{teacherEntity.addressLineThree}</dd>
            <dt>
              <span id="town">Town</span>
            </dt>
            <dd>{teacherEntity.town}</dd>
            <dt>
              <span id="state">State</span>
            </dt>
            <dd>{teacherEntity.state}</dd>
            <dt>
              <span id="country">Country</span>
            </dt>
            <dd>{teacherEntity.country}</dd>
            <dt>
              <span id="pincode">Pincode</span>
            </dt>
            <dd>{teacherEntity.pincode}</dd>
            <dt>
              <span id="teacherContactNumber">Teacher Contact Number</span>
            </dt>
            <dd>{teacherEntity.teacherContactNumber}</dd>
            <dt>
              <span id="alternateContactNumber">Alternate Contact Number</span>
            </dt>
            <dd>{teacherEntity.alternateContactNumber}</dd>
            <dt>
              <span id="teacherEmailAddress">Teacher Email Address</span>
            </dt>
            <dd>{teacherEntity.teacherEmailAddress}</dd>
            <dt>
              <span id="alternateEmailAddress">Alternate Email Address</span>
            </dt>
            <dd>{teacherEntity.alternateEmailAddress}</dd>
            <dt>
              <span id="relationWithStaff">Relation With Staff</span>
            </dt>
            <dd>{teacherEntity.relationWithStaff}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{teacherEntity.name}</dd>
            <dt>
              <span id="middleName">Middle Name</span>
            </dt>
            <dd>{teacherEntity.middleName}</dd>
            <dt>
              <span id="lastName">Last Name</span>
            </dt>
            <dd>{teacherEntity.lastName}</dd>
            <dt>
              <span id="contactNo">Contact No</span>
            </dt>
            <dd>{teacherEntity.contactNo}</dd>
            <dt>
              <span id="emailAddress">Email Address</span>
            </dt>
            <dd>{teacherEntity.emailAddress}</dd>
            <dt>
              <span id="uploadPhoto">Upload Photo</span>
            </dt>
            <dd>{teacherEntity.uploadPhoto}</dd>
            <dt>
              <span id="employeeId">Employee Id</span>
            </dt>
            <dd>{teacherEntity.employeeId}</dd>
            <dt>
              <span id="designation">Designation</span>
            </dt>
            <dd>{teacherEntity.designation}</dd>
            <dt>
              <span id="staffType">Staff Type</span>
            </dt>
            <dd>{teacherEntity.staffType}</dd>
            <dt>Department</dt>
            <dd>{teacherEntity.departmentId ? teacherEntity.departmentId : ''}</dd>
            <dt>Branch</dt>
            <dd>{teacherEntity.branchId ? teacherEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/teacher" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/teacher/${teacherEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ teacher }: IRootState) => ({
  teacherEntity: teacher.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TeacherDetail);
