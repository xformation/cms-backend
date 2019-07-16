import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionApplicationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdmissionApplicationDetail extends React.Component<IAdmissionApplicationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { admissionApplicationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdmissionApplication [<b>{admissionApplicationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="admissionStatus">Admission Status</span>
            </dt>
            <dd>{admissionApplicationEntity.admissionStatus}</dd>
            <dt>
              <span id="studentName">Student Name</span>
            </dt>
            <dd>{admissionApplicationEntity.studentName}</dd>
            <dt>
              <span id="studentMiddleName">Student Middle Name</span>
            </dt>
            <dd>{admissionApplicationEntity.studentMiddleName}</dd>
            <dt>
              <span id="studentLastName">Student Last Name</span>
            </dt>
            <dd>{admissionApplicationEntity.studentLastName}</dd>
            <dt>
              <span id="fatherName">Father Name</span>
            </dt>
            <dd>{admissionApplicationEntity.fatherName}</dd>
            <dt>
              <span id="fatherMiddleName">Father Middle Name</span>
            </dt>
            <dd>{admissionApplicationEntity.fatherMiddleName}</dd>
            <dt>
              <span id="fatherLastName">Father Last Name</span>
            </dt>
            <dd>{admissionApplicationEntity.fatherLastName}</dd>
            <dt>
              <span id="motherName">Mother Name</span>
            </dt>
            <dd>{admissionApplicationEntity.motherName}</dd>
            <dt>
              <span id="motherMiddleName">Mother Middle Name</span>
            </dt>
            <dd>{admissionApplicationEntity.motherMiddleName}</dd>
            <dt>
              <span id="motherLastName">Mother Last Name</span>
            </dt>
            <dd>{admissionApplicationEntity.motherLastName}</dd>
            <dt>
              <span id="contactNumber">Contact Number</span>
            </dt>
            <dd>{admissionApplicationEntity.contactNumber}</dd>
            <dt>
              <span id="alternateMobileNumber">Alternate Mobile Number</span>
            </dt>
            <dd>{admissionApplicationEntity.alternateMobileNumber}</dd>
            <dt>
              <span id="dateOfBirth">Date Of Birth</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{admissionApplicationEntity.email}</dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{admissionApplicationEntity.sex}</dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{admissionApplicationEntity.comments}</dd>
            <dt>
              <span id="applicationId">Application Id</span>
            </dt>
            <dd>{admissionApplicationEntity.applicationId}</dd>
            <dt>
              <span id="uploadPhoto">Upload Photo</span>
            </dt>
            <dd>{admissionApplicationEntity.uploadPhoto}</dd>
            <dt>
              <span id="course">Course</span>
            </dt>
            <dd>{admissionApplicationEntity.course}</dd>
            <dt>
              <span id="admissionDate">Admission Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionApplicationEntity.admissionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Admission Enquiry</dt>
            <dd>{admissionApplicationEntity.admissionEnquiryId ? admissionApplicationEntity.admissionEnquiryId : ''}</dd>
            <dt>Academic History</dt>
            <dd>{admissionApplicationEntity.academicHistoryId ? admissionApplicationEntity.academicHistoryId : ''}</dd>
            <dt>Documents</dt>
            <dd>{admissionApplicationEntity.documentsId ? admissionApplicationEntity.documentsId : ''}</dd>
            <dt>Branch</dt>
            <dd>{admissionApplicationEntity.branchId ? admissionApplicationEntity.branchId : ''}</dd>
            <dt>Batch</dt>
            <dd>{admissionApplicationEntity.batchId ? admissionApplicationEntity.batchId : ''}</dd>
            <dt>State</dt>
            <dd>{admissionApplicationEntity.stateId ? admissionApplicationEntity.stateId : ''}</dd>
            <dt>City</dt>
            <dd>{admissionApplicationEntity.cityId ? admissionApplicationEntity.cityId : ''}</dd>
            <dt>Country</dt>
            <dd>{admissionApplicationEntity.countryId ? admissionApplicationEntity.countryId : ''}</dd>
            <dt>Department</dt>
            <dd>{admissionApplicationEntity.departmentId ? admissionApplicationEntity.departmentId : ''}</dd>
            <dt>Academicyear</dt>
            <dd>{admissionApplicationEntity.academicyearId ? admissionApplicationEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admission-application" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/admission-application/${admissionApplicationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ admissionApplication }: IRootState) => ({
  admissionApplicationEntity: admissionApplication.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionApplicationDetail);
