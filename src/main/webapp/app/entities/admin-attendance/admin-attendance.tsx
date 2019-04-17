import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './admin-attendance.reducer';
import { IAdminAttendance } from 'app/shared/model/admin-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdminAttendanceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAdminAttendanceState {
  search: string;
}

export class AdminAttendance extends React.Component<IAdminAttendanceProps, IAdminAttendanceState> {
  state: IAdminAttendanceState = {
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
    const { adminAttendanceList, match } = this.props;
    return (
      <div>
        <h2 id="admin-attendance-heading">
          Admin Attendances
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Admin Attendance
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
                <th>Updated On</th>
                <th>Updated By</th>
                <th>Lecture</th>
                <th>Branch</th>
                <th>College</th>
                <th>Department</th>
                <th>Academicyear</th>
                <th>Section</th>
                <th>Student</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adminAttendanceList.map((adminAttendance, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adminAttendance.id}`} color="link" size="sm">
                      {adminAttendance.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={adminAttendance.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{adminAttendance.updatedBy}</td>
                  <td>
                    {adminAttendance.lectureId ? <Link to={`lecture/${adminAttendance.lectureId}`}>{adminAttendance.lectureId}</Link> : ''}
                  </td>
                  <td>
                    {adminAttendance.branchId ? <Link to={`branch/${adminAttendance.branchId}`}>{adminAttendance.branchId}</Link> : ''}
                  </td>
                  <td>
                    {adminAttendance.collegeId ? <Link to={`college/${adminAttendance.collegeId}`}>{adminAttendance.collegeId}</Link> : ''}
                  </td>
                  <td>
                    {adminAttendance.departmentId ? (
                      <Link to={`department/${adminAttendance.departmentId}`}>{adminAttendance.departmentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {adminAttendance.academicyearId ? (
                      <Link to={`academic-year/${adminAttendance.academicyearId}`}>{adminAttendance.academicyearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {adminAttendance.sectionId ? <Link to={`section/${adminAttendance.sectionId}`}>{adminAttendance.sectionId}</Link> : ''}
                  </td>
                  <td>
                    {adminAttendance.studentId ? <Link to={`student/${adminAttendance.studentId}`}>{adminAttendance.studentId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adminAttendance.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adminAttendance.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adminAttendance.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ adminAttendance }: IRootState) => ({
  adminAttendanceList: adminAttendance.entities
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
)(AdminAttendance);
