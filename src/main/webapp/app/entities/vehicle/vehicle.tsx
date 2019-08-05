import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVehicleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IVehicleState {
  search: string;
}

export class Vehicle extends React.Component<IVehicleProps, IVehicleState> {
  state: IVehicleState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.props.getEntities();
    this.setState({
      search: ''
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { vehicleList, match } = this.props;
    return (
      <div>
        <h2 id="vehicle-heading">
          Vehicles
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Vehicle
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput type="text" name="search" value={this.state.search} onChange={this.handleSearch} placeholder="Search" />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Vehicle Number</th>
                <th>Vehicle Type</th>
                <th>Capacity</th>
                <th>Owner Ship</th>
                <th>Date Of Registration</th>
                <th>Year Of Manufacturing</th>
                <th>Manufacturing Company</th>
                <th>Model</th>
                <th>Chasis No</th>
                <th>Rc No</th>
                <th>Contact Number</th>
                <th>Status</th>
                <th>Employee</th>
                <th>Transport Route</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {vehicleList.map((vehicle, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${vehicle.id}`} color="link" size="sm">
                      {vehicle.id}
                    </Button>
                  </td>
                  <td>{vehicle.vehicleNumber}</td>
                  <td>{vehicle.vehicleType}</td>
                  <td>{vehicle.capacity}</td>
                  <td>{vehicle.ownerShip}</td>
                  <td>
                    <TextFormat type="date" value={vehicle.dateOfRegistration} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{vehicle.yearOfManufacturing}</td>
                  <td>{vehicle.manufacturingCompany}</td>
                  <td>{vehicle.model}</td>
                  <td>{vehicle.chasisNo}</td>
                  <td>{vehicle.rcNo}</td>
                  <td>{vehicle.contactNumber}</td>
                  <td>{vehicle.status}</td>
                  <td>{vehicle.employeeId ? <Link to={`employee/${vehicle.employeeId}`}>{vehicle.employeeId}</Link> : ''}</td>
                  <td>
                    {vehicle.transportRouteId ? (
                      <Link to={`transport-route/${vehicle.transportRouteId}`}>{vehicle.transportRouteId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${vehicle.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vehicle.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vehicle.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ vehicle }: IRootState) => ({
  vehicleList: vehicle.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Vehicle);
