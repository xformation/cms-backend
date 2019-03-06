import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './academic-history.reducer';
import { IAcademicHistory } from 'app/shared/model/academic-history.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicHistoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAcademicHistoryState {
  search: string;
}

export class AcademicHistory extends React.Component<IAcademicHistoryProps, IAcademicHistoryState> {
  state: IAcademicHistoryState = {
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
    const { academicHistoryList, match } = this.props;
    return (
      <div>
        <h2 id="academic-history-heading">
          Academic Histories
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Academic History
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
                <th>Qualification</th>
                <th>Year Of Passing</th>
                <th>Institution</th>
                <th>University</th>
                <th>Enrollment No</th>
                <th>Score</th>
                <th>Percentage</th>
                <th>Student</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {academicHistoryList.map((academicHistory, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${academicHistory.id}`} color="link" size="sm">
                      {academicHistory.id}
                    </Button>
                  </td>
                  <td>{academicHistory.qualification}</td>
                  <td>{academicHistory.yearOfPassing}</td>
                  <td>{academicHistory.institution}</td>
                  <td>{academicHistory.university}</td>
                  <td>{academicHistory.enrollmentNo}</td>
                  <td>{academicHistory.score}</td>
                  <td>{academicHistory.percentage}</td>
                  <td>
                    {academicHistory.studentId ? <Link to={`student/${academicHistory.studentId}`}>{academicHistory.studentId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${academicHistory.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicHistory.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicHistory.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ academicHistory }: IRootState) => ({
  academicHistoryList: academicHistory.entities
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
)(AcademicHistory);
