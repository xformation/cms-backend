import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IVehicle } from 'app/shared/model/vehicle.model';
import { getEntities as getVehicles } from 'app/entities/vehicle/vehicle.reducer';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEmployeeUpdateState {
  isNew: boolean;
  vehicleId: string;
}

export class EmployeeUpdate extends React.Component<IEmployeeUpdateProps, IEmployeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      vehicleId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getVehicles();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { employeeEntity } = this.props;
      const entity = {
        ...employeeEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/employee');
  };

  render() {
    const { employeeEntity, vehicles, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.employee.home.createOrEditLabel">Create or edit a Employee</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : employeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="employee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="employeeNameLabel" for="employeeName">
                    Employee Name
                  </Label>
                  <AvField
                    id="employee-employeeName"
                    type="text"
                    name="employeeName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="designationLabel" for="designation">
                    Designation
                  </Label>
                  <AvField id="employee-designation" type="text" name="designation" />
                </AvGroup>
                <AvGroup>
                  <Label id="joiningDateLabel" for="joiningDate">
                    Joining Date
                  </Label>
                  <AvField
                    id="employee-joiningDate"
                    type="date"
                    className="form-control"
                    name="joiningDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="jobEndDateLabel" for="jobEndDate">
                    Job End Date
                  </Label>
                  <AvField id="employee-jobEndDate" type="date" className="form-control" name="jobEndDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="resignationDateLabel" for="resignationDate">
                    Resignation Date
                  </Label>
                  <AvField id="employee-resignationDate" type="date" className="form-control" name="resignationDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="resignationAcceptanceDateLabel" for="resignationAcceptanceDate">
                    Resignation Acceptance Date
                  </Label>
                  <AvField id="employee-resignationAcceptanceDate" type="date" className="form-control" name="resignationAcceptanceDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="aadharNoLabel" for="aadharNo">
                    Aadhar No
                  </Label>
                  <AvField id="employee-aadharNo" type="text" name="aadharNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="panNoLabel" for="panNo">
                    Pan No
                  </Label>
                  <AvField id="employee-panNo" type="text" name="panNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="passportNoLabel" for="passportNo">
                    Passport No
                  </Label>
                  <AvField id="employee-passportNo" type="text" name="passportNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="primaryContactNoLabel" for="primaryContactNo">
                    Primary Contact No
                  </Label>
                  <AvField id="employee-primaryContactNo" type="text" name="primaryContactNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="secondaryContactNoLabel" for="secondaryContactNo">
                    Secondary Contact No
                  </Label>
                  <AvField id="employee-secondaryContactNo" type="text" name="secondaryContactNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeFatherNameLabel" for="employeeFatherName">
                    Employee Father Name
                  </Label>
                  <AvField
                    id="employee-employeeFatherName"
                    type="text"
                    name="employeeFatherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeMotherNameLabel" for="employeeMotherName">
                    Employee Mother Name
                  </Label>
                  <AvField
                    id="employee-employeeMotherName"
                    type="text"
                    name="employeeMotherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="primaryAddressLabel" for="primaryAddress">
                    Primary Address
                  </Label>
                  <AvField
                    id="employee-primaryAddress"
                    type="text"
                    name="primaryAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="secondaryAddressLabel" for="secondaryAddress">
                    Secondary Address
                  </Label>
                  <AvField
                    id="employee-secondaryAddress"
                    type="text"
                    name="secondaryAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeAddressLabel" for="employeeAddress">
                    Employee Address
                  </Label>
                  <AvField id="employee-employeeAddress" type="text" name="employeeAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="personalMailIdLabel" for="personalMailId">
                    Personal Mail Id
                  </Label>
                  <AvField id="employee-personalMailId" type="text" name="personalMailId" />
                </AvGroup>
                <AvGroup>
                  <Label id="officialMailIdLabel" for="officialMailId">
                    Official Mail Id
                  </Label>
                  <AvField id="employee-officialMailId" type="text" name="officialMailId" />
                </AvGroup>
                <AvGroup>
                  <Label id="disabilityLabel">Disability</Label>
                  <AvInput
                    id="employee-disability"
                    type="select"
                    className="form-control"
                    name="disability"
                    value={(!isNew && employeeEntity.disability) || 'YES'}
                  >
                    <option value="YES">YES</option>
                    <option value="NO">NO</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="drivingLicenceNoLabel" for="drivingLicenceNo">
                    Driving Licence No
                  </Label>
                  <AvField id="employee-drivingLicenceNo" type="text" name="drivingLicenceNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="drivingLicenceValidityLabel" for="drivingLicenceValidity">
                    Driving Licence Validity
                  </Label>
                  <AvField id="employee-drivingLicenceValidity" type="date" className="form-control" name="drivingLicenceValidity" />
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel" for="gender">
                    Gender
                  </Label>
                  <AvField id="employee-gender" type="text" name="gender" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/employee" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  vehicles: storeState.vehicle.entities,
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating,
  updateSuccess: storeState.employee.updateSuccess
});

const mapDispatchToProps = {
  getVehicles,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmployeeUpdate);
