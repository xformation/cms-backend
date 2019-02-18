import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './admin-overview.reducer';
import { IAdminOverview } from 'app/shared/model/admin-overview.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdminOverviewProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAdminOverviewState {
  search: string;
}

export class AdminOverview extends React.Component<IAdminOverviewProps, IAdminOverviewState> {
  state: IAdminOverviewState = {
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
    const { adminOverviewList, match } = this.props;
    return (
      <div>
        <h2 id="admin-overview-heading">
          Admin Overviews
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Admin Overview
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
                <th>Choose Date</th>
                <th>Section</th>
                <th>Lecture One</th>
                <th>Lecture Two</th>
                <th>Lecture Three</th>
                <th>Lecture Four</th>
                <th>Lecture Five</th>
                <th>Lecture Six</th>
                <th>Lecture Seven</th>
                <th>Lecture Eight</th>
                <th>Department</th>
                <th>Batch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adminOverviewList.map((adminOverview, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adminOverview.id}`} color="link" size="sm">
                      {adminOverview.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={adminOverview.chooseDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{adminOverview.section}</td>
                  <td>{adminOverview.lectureOne}</td>
                  <td>{adminOverview.lectureTwo}</td>
                  <td>{adminOverview.lectureThree}</td>
                  <td>{adminOverview.lectureFour}</td>
                  <td>{adminOverview.lectureFive}</td>
                  <td>{adminOverview.lectureSix}</td>
                  <td>{adminOverview.lectureSeven}</td>
                  <td>{adminOverview.lectureEight}</td>
                  <td>
                    {adminOverview.departmentId ? (
                      <Link to={`department/${adminOverview.departmentId}`}>{adminOverview.departmentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{adminOverview.batchId ? <Link to={`batch/${adminOverview.batchId}`}>{adminOverview.batchId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adminOverview.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adminOverview.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adminOverview.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ adminOverview }: IRootState) => ({
  adminOverviewList: adminOverview.entities
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
)(AdminOverview);
