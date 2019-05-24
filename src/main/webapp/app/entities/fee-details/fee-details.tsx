import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './fee-details.reducer';
import { IFeeDetails } from 'app/shared/model/fee-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFeeDetailsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IFeeDetailsState {
  search: string;
}

export class FeeDetails extends React.Component<IFeeDetailsProps, IFeeDetailsState> {
  state: IFeeDetailsState = {
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
    this.setState({ search: '' }, () => {
      this.props.getEntities();
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { feeDetailsList, match } = this.props;
    return (
      <div>
        <h2 id="fee-details-heading">
          Fee Details
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Fee Details
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
                <th>Fee Particulars Name</th>
                <th>Fee Particular Desc</th>
                <th>Student Type</th>
                <th>Gender</th>
                <th>Amount</th>
                <th>Fee Category</th>
                <th>Batch</th>
                <th>Facility</th>
                <th>Transport Route</th>
                <th>College</th>
                <th>Department</th>
                <th>Branch</th>
                <th>Academic Year</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {feeDetailsList.map((feeDetails, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${feeDetails.id}`} color="link" size="sm">
                      {feeDetails.id}
                    </Button>
                  </td>
                  <td>{feeDetails.feeParticularsName}</td>
                  <td>{feeDetails.feeParticularDesc}</td>
                  <td>{feeDetails.studentType}</td>
                  <td>{feeDetails.gender}</td>
                  <td>{feeDetails.amount}</td>
                  <td>
                    {feeDetails.feeCategoryId ? (
                      <Link to={`fee-category/${feeDetails.feeCategoryId}`}>{feeDetails.feeCategoryId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{feeDetails.batchId ? <Link to={`batch/${feeDetails.batchId}`}>{feeDetails.batchId}</Link> : ''}</td>
                  <td>{feeDetails.facilityId ? <Link to={`facility/${feeDetails.facilityId}`}>{feeDetails.facilityId}</Link> : ''}</td>
                  <td>
                    {feeDetails.transportRouteId ? (
                      <Link to={`transport-route/${feeDetails.transportRouteId}`}>{feeDetails.transportRouteId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{feeDetails.collegeId ? <Link to={`college/${feeDetails.collegeId}`}>{feeDetails.collegeId}</Link> : ''}</td>
                  <td>
                    {feeDetails.departmentId ? <Link to={`department/${feeDetails.departmentId}`}>{feeDetails.departmentId}</Link> : ''}
                  </td>
                  <td>{feeDetails.branchId ? <Link to={`branch/${feeDetails.branchId}`}>{feeDetails.branchId}</Link> : ''}</td>
                  <td>
                    {feeDetails.academicYearId ? (
                      <Link to={`academic-year/${feeDetails.academicYearId}`}>{feeDetails.academicYearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${feeDetails.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${feeDetails.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${feeDetails.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ feeDetails }: IRootState) => ({
  feeDetailsList: feeDetails.entities
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
)(FeeDetails);
