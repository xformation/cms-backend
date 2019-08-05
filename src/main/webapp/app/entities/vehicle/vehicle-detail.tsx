import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVehicleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class VehicleDetail extends React.Component<IVehicleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { vehicleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Vehicle [<b>{vehicleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="vehicleNumber">Vehicle Number</span>
            </dt>
            <dd>{vehicleEntity.vehicleNumber}</dd>
            <dt>
              <span id="vehicleType">Vehicle Type</span>
            </dt>
            <dd>{vehicleEntity.vehicleType}</dd>
            <dt>
              <span id="capacity">Capacity</span>
            </dt>
            <dd>{vehicleEntity.capacity}</dd>
            <dt>
              <span id="ownerShip">Owner Ship</span>
            </dt>
            <dd>{vehicleEntity.ownerShip}</dd>
            <dt>
              <span id="dateOfRegistration">Date Of Registration</span>
            </dt>
            <dd>
              <TextFormat value={vehicleEntity.dateOfRegistration} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="yearOfManufacturing">Year Of Manufacturing</span>
            </dt>
            <dd>{vehicleEntity.yearOfManufacturing}</dd>
            <dt>
              <span id="manufacturingCompany">Manufacturing Company</span>
            </dt>
            <dd>{vehicleEntity.manufacturingCompany}</dd>
            <dt>
              <span id="model">Model</span>
            </dt>
            <dd>{vehicleEntity.model}</dd>
            <dt>
              <span id="chasisNo">Chasis No</span>
            </dt>
            <dd>{vehicleEntity.chasisNo}</dd>
            <dt>
              <span id="rcNo">Rc No</span>
            </dt>
            <dd>{vehicleEntity.rcNo}</dd>
            <dt>
              <span id="contactNumber">Contact Number</span>
            </dt>
            <dd>{vehicleEntity.contactNumber}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{vehicleEntity.status}</dd>
            <dt>Employee</dt>
            <dd>{vehicleEntity.employeeId ? vehicleEntity.employeeId : ''}</dd>
            <dt>Transport Route</dt>
            <dd>{vehicleEntity.transportRouteId ? vehicleEntity.transportRouteId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/vehicle" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/vehicle/${vehicleEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ vehicle }: IRootState) => ({
  vehicleEntity: vehicle.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VehicleDetail);
