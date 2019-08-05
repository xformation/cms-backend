import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { ITransportRoute } from 'app/shared/model/transport-route.model';
import { getEntities as getTransportRoutes } from 'app/entities/transport-route/transport-route.reducer';
import { IInsurance } from 'app/shared/model/insurance.model';
import { getEntities as getInsurances } from 'app/entities/insurance/insurance.reducer';
import { getEntity, updateEntity, createEntity, reset } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVehicleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IVehicleUpdateState {
  isNew: boolean;
  employeeId: string;
  transportRouteId: string;
  insuranceId: string;
}

export class VehicleUpdate extends React.Component<IVehicleUpdateProps, IVehicleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      employeeId: '0',
      transportRouteId: '0',
      insuranceId: '0',
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

    this.props.getEmployees();
    this.props.getTransportRoutes();
    this.props.getInsurances();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { vehicleEntity } = this.props;
      const entity = {
        ...vehicleEntity,
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
    this.props.history.push('/entity/vehicle');
  };

  render() {
    const { vehicleEntity, employees, transportRoutes, insurances, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.vehicle.home.createOrEditLabel">Create or edit a Vehicle</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : vehicleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="vehicle-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="vehicleNumberLabel" for="vehicleNumber">
                    Vehicle Number
                  </Label>
                  <AvField
                    id="vehicle-vehicleNumber"
                    type="string"
                    className="form-control"
                    name="vehicleNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="vehicleTypeLabel" for="vehicleType">
                    Vehicle Type
                  </Label>
                  <AvField
                    id="vehicle-vehicleType"
                    type="text"
                    name="vehicleType"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="capacityLabel" for="capacity">
                    Capacity
                  </Label>
                  <AvField
                    id="vehicle-capacity"
                    type="string"
                    className="form-control"
                    name="capacity"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ownerShipLabel" for="ownerShip">
                    Owner Ship
                  </Label>
                  <AvField
                    id="vehicle-ownerShip"
                    type="text"
                    name="ownerShip"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfRegistrationLabel" for="dateOfRegistration">
                    Date Of Registration
                  </Label>
                  <AvField id="vehicle-dateOfRegistration" type="date" className="form-control" name="dateOfRegistration" />
                </AvGroup>
                <AvGroup>
                  <Label id="yearOfManufacturingLabel" for="yearOfManufacturing">
                    Year Of Manufacturing
                  </Label>
                  <AvField id="vehicle-yearOfManufacturing" type="text" name="yearOfManufacturing" />
                </AvGroup>
                <AvGroup>
                  <Label id="manufacturingCompanyLabel" for="manufacturingCompany">
                    Manufacturing Company
                  </Label>
                  <AvField id="vehicle-manufacturingCompany" type="text" name="manufacturingCompany" />
                </AvGroup>
                <AvGroup>
                  <Label id="modelLabel" for="model">
                    Model
                  </Label>
                  <AvField id="vehicle-model" type="text" name="model" />
                </AvGroup>
                <AvGroup>
                  <Label id="chasisNoLabel" for="chasisNo">
                    Chasis No
                  </Label>
                  <AvField id="vehicle-chasisNo" type="text" name="chasisNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="rcNoLabel" for="rcNo">
                    Rc No
                  </Label>
                  <AvField
                    id="vehicle-rcNo"
                    type="text"
                    name="rcNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactNumberLabel" for="contactNumber">
                    Contact Number
                  </Label>
                  <AvField
                    id="vehicle-contactNumber"
                    type="text"
                    name="contactNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">Status</Label>
                  <AvInput
                    id="vehicle-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && vehicleEntity.status) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="employee.id">Employee</Label>
                  <AvInput id="vehicle-employee" type="select" className="form-control" name="employeeId">
                    <option value="" key="0" />
                    {employees
                      ? employees.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="transportRoute.id">Transport Route</Label>
                  <AvInput id="vehicle-transportRoute" type="select" className="form-control" name="transportRouteId">
                    <option value="" key="0" />
                    {transportRoutes
                      ? transportRoutes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/vehicle" replace color="info">
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
  employees: storeState.employee.entities,
  transportRoutes: storeState.transportRoute.entities,
  insurances: storeState.insurance.entities,
  vehicleEntity: storeState.vehicle.entity,
  loading: storeState.vehicle.loading,
  updating: storeState.vehicle.updating,
  updateSuccess: storeState.vehicle.updateSuccess
});

const mapDispatchToProps = {
  getEmployees,
  getTransportRoutes,
  getInsurances,
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
)(VehicleUpdate);
