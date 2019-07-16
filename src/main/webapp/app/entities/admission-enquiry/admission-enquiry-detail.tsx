import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admission-enquiry.reducer';
import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionEnquiryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdmissionEnquiryDetail extends React.Component<IAdmissionEnquiryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { admissionEnquiryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdmissionEnquiry [<b>{admissionEnquiryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentName">Student Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentName}</dd>
            <dt>
              <span id="studentMiddleName">Student Middle Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentMiddleName}</dd>
            <dt>
              <span id="studentLastName">Student Last Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.studentLastName}</dd>
            <dt>
              <span id="fatherName">Father Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.fatherName}</dd>
            <dt>
              <span id="fatherMiddleName">Father Middle Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.fatherMiddleName}</dd>
            <dt>
              <span id="fatherLastName">Father Last Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.fatherLastName}</dd>
            <dt>
              <span id="motherName">Mother Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.motherName}</dd>
            <dt>
              <span id="motherMiddleName">Mother Middle Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.motherMiddleName}</dd>
            <dt>
              <span id="motherLastName">Mother Last Name</span>
            </dt>
            <dd>{admissionEnquiryEntity.motherLastName}</dd>
            <dt>
              <span id="contactNumber">Contact Number</span>
            </dt>
            <dd>{admissionEnquiryEntity.contactNumber}</dd>
            <dt>
              <span id="alternateMobileNumber">Alternate Mobile Number</span>
            </dt>
            <dd>{admissionEnquiryEntity.alternateMobileNumber}</dd>
            <dt>
              <span id="dateOfBirth">Date Of Birth</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{admissionEnquiryEntity.email}</dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{admissionEnquiryEntity.sex}</dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{admissionEnquiryEntity.comments}</dd>
            <dt>
              <span id="courseApplyingFor">Course Applying For</span>
            </dt>
            <dd>{admissionEnquiryEntity.courseApplyingFor}</dd>
            <dt>
              <span id="highestQualification">Highest Qualification</span>
            </dt>
            <dd>{admissionEnquiryEntity.highestQualification}</dd>
            <dt>
              <span id="modeOfEnquiry">Mode Of Enquiry</span>
            </dt>
            <dd>{admissionEnquiryEntity.modeOfEnquiry}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{admissionEnquiryEntity.status}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{admissionEnquiryEntity.description}</dd>
            <dt>
              <span id="enquiryDate">Enquiry Date</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.enquiryDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedOn">Updated On</span>
            </dt>
            <dd>
              <TextFormat value={admissionEnquiryEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{admissionEnquiryEntity.updatedBy}</dd>
            <dt>Branch</dt>
            <dd>{admissionEnquiryEntity.branchId ? admissionEnquiryEntity.branchId : ''}</dd>
            <dt>Department</dt>
            <dd>{admissionEnquiryEntity.departmentId ? admissionEnquiryEntity.departmentId : ''}</dd>
            <dt>Batch</dt>
            <dd>{admissionEnquiryEntity.batchId ? admissionEnquiryEntity.batchId : ''}</dd>
            <dt>State</dt>
            <dd>{admissionEnquiryEntity.stateId ? admissionEnquiryEntity.stateId : ''}</dd>
            <dt>City</dt>
            <dd>{admissionEnquiryEntity.cityId ? admissionEnquiryEntity.cityId : ''}</dd>
            <dt>Country</dt>
            <dd>{admissionEnquiryEntity.countryId ? admissionEnquiryEntity.countryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admission-enquiry" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/admission-enquiry/${admissionEnquiryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ admissionEnquiry }: IRootState) => ({
  admissionEnquiryEntity: admissionEnquiry.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionEnquiryDetail);
