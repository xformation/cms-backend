import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './teacher.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITeacherProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITeacherState {
  search: string;
}

export class Teacher extends React.Component<ITeacherProps, ITeacherState> {
  state: ITeacherState = {
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
    const { teacherList, match } = this.props;
    return (
      <div>
        <h2 id="teacher-heading">
          Teachers
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Teacher
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
                <th>Teacher Name</th>
                <th>Teacher Middle Name</th>
                <th>Teacher Last Name</th>
                <th>Father Name</th>
                <th>Father Middle Name</th>
                <th>Father Last Name</th>
                <th>Mother Name</th>
                <th>Mother Middle Name</th>
                <th>Mother Last Name</th>
                <th>Aadhar No</th>
                <th>Date Of Birth</th>
                <th>Place Of Birth</th>
                <th>Religion</th>
                <th>Caste</th>
                <th>Sub Caste</th>
                <th>Age</th>
                <th>Sex</th>
                <th>Blood Group</th>
                <th>Address Line One</th>
                <th>Address Line Two</th>
                <th>Address Line Three</th>
                <th>Town</th>
                <th>State</th>
                <th>Country</th>
                <th>Pincode</th>
                <th>Teacher Contact Number</th>
                <th>Alternate Contact Number</th>
                <th>Teacher Email Address</th>
                <th>Alternate Email Address</th>
                <th>Relation With Staff</th>
                <th>Name</th>
                <th>Middle Name</th>
                <th>Last Name</th>
                <th>Contact No</th>
                <th>Email Address</th>
                <th>Upload Photo</th>
                <th>Employee Id</th>
                <th>Designation</th>
                <th>Staff Type</th>
                <th>Department</th>
                <th>Branch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {teacherList.map((teacher, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${teacher.id}`} color="link" size="sm">
                      {teacher.id}
                    </Button>
                  </td>
                  <td>{teacher.teacherName}</td>
                  <td>{teacher.teacherMiddleName}</td>
                  <td>{teacher.teacherLastName}</td>
                  <td>{teacher.fatherName}</td>
                  <td>{teacher.fatherMiddleName}</td>
                  <td>{teacher.fatherLastName}</td>
                  <td>{teacher.motherName}</td>
                  <td>{teacher.motherMiddleName}</td>
                  <td>{teacher.motherLastName}</td>
                  <td>{teacher.aadharNo}</td>
                  <td>
                    <TextFormat type="date" value={teacher.dateOfBirth} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{teacher.placeOfBirth}</td>
                  <td>{teacher.religion}</td>
                  <td>{teacher.caste}</td>
                  <td>{teacher.subCaste}</td>
                  <td>{teacher.age}</td>
                  <td>{teacher.sex}</td>
                  <td>{teacher.bloodGroup}</td>
                  <td>{teacher.addressLineOne}</td>
                  <td>{teacher.addressLineTwo}</td>
                  <td>{teacher.addressLineThree}</td>
                  <td>{teacher.town}</td>
                  <td>{teacher.state}</td>
                  <td>{teacher.country}</td>
                  <td>{teacher.pincode}</td>
                  <td>{teacher.teacherContactNumber}</td>
                  <td>{teacher.alternateContactNumber}</td>
                  <td>{teacher.teacherEmailAddress}</td>
                  <td>{teacher.alternateEmailAddress}</td>
                  <td>{teacher.relationWithStaff}</td>
                  <td>{teacher.name}</td>
                  <td>{teacher.middleName}</td>
                  <td>{teacher.lastName}</td>
                  <td>{teacher.contactNo}</td>
                  <td>{teacher.emailAddress}</td>
                  <td>{teacher.uploadPhoto}</td>
                  <td>{teacher.employeeId}</td>
                  <td>{teacher.designation}</td>
                  <td>{teacher.staffType}</td>
                  <td>{teacher.departmentId ? <Link to={`department/${teacher.departmentId}`}>{teacher.departmentId}</Link> : ''}</td>
                  <td>{teacher.branchId ? <Link to={`branch/${teacher.branchId}`}>{teacher.branchId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${teacher.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${teacher.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${teacher.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ teacher }: IRootState) => ({
  teacherList: teacher.entities
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
)(Teacher);
