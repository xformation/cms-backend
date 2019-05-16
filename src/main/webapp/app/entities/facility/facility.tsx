import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './facility.reducer';
import { IFacility } from 'app/shared/model/facility.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFacilityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IFacilityState {
  search: string;
}

export class Facility extends React.Component<IFacilityProps, IFacilityState> {
  state: IFacilityState = {
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
    const { facilityList, match } = this.props;
    return (
      <div>
        <h2 id="facility-heading">
          Facilities
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Facility
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
                <th>Transport</th>
                <th>Mess</th>
                <th>Gym</th>
                <th>Cultural Class</th>
                <th>Library</th>
                <th>Sports</th>
                <th>Swimming</th>
                <th>Extra Class</th>
                <th>Handicrafts</th>
                <th>Academic Year</th>
                <th>Branch</th>
                <th>Student</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {facilityList.map((facility, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${facility.id}`} color="link" size="sm">
                      {facility.id}
                    </Button>
                  </td>
                  <td>{facility.transport}</td>
                  <td>{facility.mess}</td>
                  <td>{facility.gym}</td>
                  <td>{facility.culturalClass}</td>
                  <td>{facility.library}</td>
                  <td>{facility.sports}</td>
                  <td>{facility.swimming}</td>
                  <td>{facility.extraClass}</td>
                  <td>{facility.handicrafts}</td>
                  <td>
                    {facility.academicYearId ? <Link to={`academic-year/${facility.academicYearId}`}>{facility.academicYearId}</Link> : ''}
                  </td>
                  <td>{facility.branchId ? <Link to={`branch/${facility.branchId}`}>{facility.branchId}</Link> : ''}</td>
                  <td>{facility.studentId ? <Link to={`student/${facility.studentId}`}>{facility.studentId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${facility.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facility.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facility.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ facility }: IRootState) => ({
  facilityList: facility.entities
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
)(Facility);
