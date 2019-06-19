import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './late-fee.reducer';
import { ILateFee } from 'app/shared/model/late-fee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILateFeeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ILateFeeState {
  search: string;
}

export class LateFee extends React.Component<ILateFeeProps, ILateFeeState> {
  state: ILateFeeState = {
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
    const { lateFeeList, match } = this.props;
    return (
      <div>
        <h2 id="late-fee-heading">
          Late Fees
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Late Fee
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
                <th>Is Auto Late Fee</th>
                <th>Late Fee Days</th>
                <th>Charge Type</th>
                <th>Fixed Charges</th>
                <th>Percent Charges</th>
                <th>Late Fee Frequency</th>
                <th>Late Fee Repeat Days</th>
                <th>College</th>
                <th>Branch</th>
                <th>Academic Year</th>
                <th>Term</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lateFeeList.map((lateFee, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${lateFee.id}`} color="link" size="sm">
                      {lateFee.id}
                    </Button>
                  </td>
                  <td>{lateFee.isAutoLateFee}</td>
                  <td>{lateFee.lateFeeDays}</td>
                  <td>{lateFee.chargeType}</td>
                  <td>{lateFee.fixedCharges}</td>
                  <td>{lateFee.percentCharges}</td>
                  <td>{lateFee.lateFeeFrequency}</td>
                  <td>{lateFee.lateFeeRepeatDays}</td>
                  <td>{lateFee.collegeId ? <Link to={`college/${lateFee.collegeId}`}>{lateFee.collegeId}</Link> : ''}</td>
                  <td>{lateFee.branchId ? <Link to={`branch/${lateFee.branchId}`}>{lateFee.branchId}</Link> : ''}</td>
                  <td>
                    {lateFee.academicYearId ? <Link to={`academic-year/${lateFee.academicYearId}`}>{lateFee.academicYearId}</Link> : ''}
                  </td>
                  <td>{lateFee.termId ? <Link to={`term/${lateFee.termId}`}>{lateFee.termId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${lateFee.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lateFee.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lateFee.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ lateFee }: IRootState) => ({
  lateFeeList: lateFee.entities
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
)(LateFee);
