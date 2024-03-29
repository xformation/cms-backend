import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionApplicationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAdmissionApplicationState {
  search: string;
}

export class AdmissionApplication extends React.Component<IAdmissionApplicationProps, IAdmissionApplicationState> {
  state: IAdmissionApplicationState = {
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
    const { admissionApplicationList, match } = this.props;
    return (
      <div>
        <h2 id="admission-application-heading">
          Admission Applications
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Admission Application
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
                <th>Admission Status</th>
                <th>Student Name</th>
                <th>Student Middle Name</th>
                <th>Student Last Name</th>
                <th>Father Name</th>
                <th>Father Middle Name</th>
                <th>Father Last Name</th>
                <th>Mother Name</th>
                <th>Mother Middle Name</th>
                <th>Mother Last Name</th>
                <th>Contact Number</th>
                <th>Alternate Mobile Number</th>
                <th>Date Of Birth</th>
                <th>Email</th>
                <th>Sex</th>
                <th>Comments</th>
                <th>Application Id</th>
                <th>Upload Photo</th>
                <th>Course</th>
                <th>Admission Date</th>
                <th>Admission Enquiry</th>
                <th>Academic History</th>
                <th>Documents</th>
                <th>Branch</th>
                <th>Batch</th>
                <th>State</th>
                <th>City</th>
                <th>Country</th>
                <th>Department</th>
                <th>Academicyear</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {admissionApplicationList.map((admissionApplication, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${admissionApplication.id}`} color="link" size="sm">
                      {admissionApplication.id}
                    </Button>
                  </td>
                  <td>{admissionApplication.admissionStatus}</td>
                  <td>{admissionApplication.studentName}</td>
                  <td>{admissionApplication.studentMiddleName}</td>
                  <td>{admissionApplication.studentLastName}</td>
                  <td>{admissionApplication.fatherName}</td>
                  <td>{admissionApplication.fatherMiddleName}</td>
                  <td>{admissionApplication.fatherLastName}</td>
                  <td>{admissionApplication.motherName}</td>
                  <td>{admissionApplication.motherMiddleName}</td>
                  <td>{admissionApplication.motherLastName}</td>
                  <td>{admissionApplication.contactNumber}</td>
                  <td>{admissionApplication.alternateMobileNumber}</td>
                  <td>
                    <TextFormat type="date" value={admissionApplication.dateOfBirth} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{admissionApplication.email}</td>
                  <td>{admissionApplication.sex}</td>
                  <td>{admissionApplication.comments}</td>
                  <td>{admissionApplication.applicationId}</td>
                  <td>{admissionApplication.uploadPhoto}</td>
                  <td>{admissionApplication.course}</td>
                  <td>
                    <TextFormat type="date" value={admissionApplication.admissionDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {admissionApplication.admissionEnquiryId ? (
                      <Link to={`admission-enquiry/${admissionApplication.admissionEnquiryId}`}>
                        {admissionApplication.admissionEnquiryId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.academicHistoryId ? (
                      <Link to={`academic-history/${admissionApplication.academicHistoryId}`}>
                        {admissionApplication.academicHistoryId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.documentsId ? (
                      <Link to={`documents/${admissionApplication.documentsId}`}>{admissionApplication.documentsId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.branchId ? (
                      <Link to={`branch/${admissionApplication.branchId}`}>{admissionApplication.branchId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.batchId ? (
                      <Link to={`batch/${admissionApplication.batchId}`}>{admissionApplication.batchId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.stateId ? (
                      <Link to={`state/${admissionApplication.stateId}`}>{admissionApplication.stateId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.cityId ? (
                      <Link to={`city/${admissionApplication.cityId}`}>{admissionApplication.cityId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.countryId ? (
                      <Link to={`country/${admissionApplication.countryId}`}>{admissionApplication.countryId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.departmentId ? (
                      <Link to={`department/${admissionApplication.departmentId}`}>{admissionApplication.departmentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionApplication.academicyearId ? (
                      <Link to={`academic-year/${admissionApplication.academicyearId}`}>{admissionApplication.academicyearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${admissionApplication.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${admissionApplication.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${admissionApplication.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ admissionApplication }: IRootState) => ({
  admissionApplicationList: admissionApplication.entities
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
)(AdmissionApplication);
