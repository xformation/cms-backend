import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EmployeeDetail extends React.Component<IEmployeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { employeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Employee [<b>{employeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="employeeName">Employee Name</span>
            </dt>
            <dd>{employeeEntity.employeeName}</dd>
            <dt>
              <span id="designation">Designation</span>
            </dt>
            <dd>{employeeEntity.designation}</dd>
            <dt>
              <span id="joiningDate">Joining Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.joiningDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="jobEndDate">Job End Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.jobEndDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="resignationDate">Resignation Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.resignationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="resignationAcceptanceDate">Resignation Acceptance Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.resignationAcceptanceDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="aadharNo">Aadhar No</span>
            </dt>
            <dd>{employeeEntity.aadharNo}</dd>
            <dt>
              <span id="panNo">Pan No</span>
            </dt>
            <dd>{employeeEntity.panNo}</dd>
            <dt>
              <span id="passportNo">Passport No</span>
            </dt>
            <dd>{employeeEntity.passportNo}</dd>
            <dt>
              <span id="primaryContactNo">Primary Contact No</span>
            </dt>
            <dd>{employeeEntity.primaryContactNo}</dd>
            <dt>
              <span id="secondaryContactNo">Secondary Contact No</span>
            </dt>
            <dd>{employeeEntity.secondaryContactNo}</dd>
            <dt>
              <span id="employeeFatherName">Employee Father Name</span>
            </dt>
            <dd>{employeeEntity.employeeFatherName}</dd>
            <dt>
              <span id="employeeMotherName">Employee Mother Name</span>
            </dt>
            <dd>{employeeEntity.employeeMotherName}</dd>
            <dt>
              <span id="primaryAddress">Primary Address</span>
            </dt>
            <dd>{employeeEntity.primaryAddress}</dd>
            <dt>
              <span id="secondaryAddress">Secondary Address</span>
            </dt>
            <dd>{employeeEntity.secondaryAddress}</dd>
            <dt>
              <span id="employeeAddress">Employee Address</span>
            </dt>
            <dd>{employeeEntity.employeeAddress}</dd>
            <dt>
              <span id="personalMailId">Personal Mail Id</span>
            </dt>
            <dd>{employeeEntity.personalMailId}</dd>
            <dt>
              <span id="officialMailId">Official Mail Id</span>
            </dt>
            <dd>{employeeEntity.officialMailId}</dd>
            <dt>
              <span id="disability">Disability</span>
            </dt>
            <dd>{employeeEntity.disability}</dd>
            <dt>
              <span id="drivingLicenceNo">Driving Licence No</span>
            </dt>
            <dd>{employeeEntity.drivingLicenceNo}</dd>
            <dt>
              <span id="drivingLicenceValidity">Driving Licence Validity</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.drivingLicenceValidity} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="gender">Gender</span>
            </dt>
            <dd>{employeeEntity.gender}</dd>
          </dl>
          <Button tag={Link} to="/entity/employee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/employee/${employeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeEntity: employee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmployeeDetail);
