import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IStudentState {
  search: string;
}

export class Student extends React.Component<IStudentProps, IStudentState> {
  state: IStudentState = {
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
    const { studentList, match } = this.props;
    return (
      <div>
        <h2 id="student-heading">
          Students
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Student
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
                <th>Student Name</th>
                <th>Student Middle Name</th>
                <th>Student Last Name</th>
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
                <th>Student Contact Number</th>
                <th>Alternate Contact Number</th>
                <th>Student Email Address</th>
                <th>Alternate Email Address</th>
                <th>Relation With Student</th>
                <th>Emergency Contact Name</th>
                <th>Emergency Contact Middle Name</th>
                <th>Emergency Contact Last Name</th>
                <th>Emergency Contact No</th>
                <th>Emergency Contact Email Address</th>
                <th>Upload Photo</th>
                <th>Admission No</th>
                <th>Roll No</th>
                <th>Student Type</th>
                <th>Department</th>
                <th>Batch</th>
                <th>Section</th>
                <th>Branch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {studentList.map((student, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${student.id}`} color="link" size="sm">
                      {student.id}
                    </Button>
                  </td>
                  <td>{student.studentName}</td>
                  <td>{student.studentMiddleName}</td>
                  <td>{student.studentLastName}</td>
                  <td>{student.fatherName}</td>
                  <td>{student.fatherMiddleName}</td>
                  <td>{student.fatherLastName}</td>
                  <td>{student.motherName}</td>
                  <td>{student.motherMiddleName}</td>
                  <td>{student.motherLastName}</td>
                  <td>{student.aadharNo}</td>
                  <td>
                    <TextFormat type="date" value={student.dateOfBirth} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{student.placeOfBirth}</td>
                  <td>{student.religion}</td>
                  <td>{student.caste}</td>
                  <td>{student.subCaste}</td>
                  <td>{student.age}</td>
                  <td>{student.sex}</td>
                  <td>{student.bloodGroup}</td>
                  <td>{student.addressLineOne}</td>
                  <td>{student.addressLineTwo}</td>
                  <td>{student.addressLineThree}</td>
                  <td>{student.town}</td>
                  <td>{student.state}</td>
                  <td>{student.country}</td>
                  <td>{student.pincode}</td>
                  <td>{student.studentContactNumber}</td>
                  <td>{student.alternateContactNumber}</td>
                  <td>{student.studentEmailAddress}</td>
                  <td>{student.alternateEmailAddress}</td>
                  <td>{student.relationWithStudent}</td>
                  <td>{student.emergencyContactName}</td>
                  <td>{student.emergencyContactMiddleName}</td>
                  <td>{student.emergencyContactLastName}</td>
                  <td>{student.emergencyContactNo}</td>
                  <td>{student.emergencyContactEmailAddress}</td>
                  <td>{student.uploadPhoto}</td>
                  <td>{student.admissionNo}</td>
                  <td>{student.rollNo}</td>
                  <td>{student.studentType}</td>
                  <td>{student.departmentId ? <Link to={`department/${student.departmentId}`}>{student.departmentId}</Link> : ''}</td>
                  <td>{student.batchId ? <Link to={`batch/${student.batchId}`}>{student.batchId}</Link> : ''}</td>
                  <td>{student.sectionId ? <Link to={`section/${student.sectionId}`}>{student.sectionId}</Link> : ''}</td>
                  <td>{student.branchId ? <Link to={`branch/${student.branchId}`}>{student.branchId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${student.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${student.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${student.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ student }: IRootState) => ({
  studentList: student.entities
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
)(Student);
