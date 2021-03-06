import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './admission-enquiry.reducer';
import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdmissionEnquiryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAdmissionEnquiryState {
  search: string;
}

export class AdmissionEnquiry extends React.Component<IAdmissionEnquiryProps, IAdmissionEnquiryState> {
  state: IAdmissionEnquiryState = {
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
    const { admissionEnquiryList, match } = this.props;
    return (
      <div>
        <h2 id="admission-enquiry-heading">
          Admission Enquiries
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Admission Enquiry
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
                <th>Contact Number</th>
                <th>Alternate Mobile Number</th>
                <th>Date Of Birth</th>
                <th>Email</th>
                <th>Sex</th>
                <th>Comments</th>
                <th>Course Applying For</th>
                <th>Highest Qualification</th>
                <th>Mode Of Enquiry</th>
                <th>Status</th>
                <th>Description</th>
                <th>Enquiry Date</th>
                <th>Updated On</th>
                <th>Updated By</th>
                <th>Branch</th>
                <th>Department</th>
                <th>Batch</th>
                <th>State</th>
                <th>City</th>
                <th>Country</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {admissionEnquiryList.map((admissionEnquiry, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${admissionEnquiry.id}`} color="link" size="sm">
                      {admissionEnquiry.id}
                    </Button>
                  </td>
                  <td>{admissionEnquiry.studentName}</td>
                  <td>{admissionEnquiry.studentMiddleName}</td>
                  <td>{admissionEnquiry.studentLastName}</td>
                  <td>{admissionEnquiry.fatherName}</td>
                  <td>{admissionEnquiry.fatherMiddleName}</td>
                  <td>{admissionEnquiry.fatherLastName}</td>
                  <td>{admissionEnquiry.motherName}</td>
                  <td>{admissionEnquiry.motherMiddleName}</td>
                  <td>{admissionEnquiry.motherLastName}</td>
                  <td>{admissionEnquiry.contactNumber}</td>
                  <td>{admissionEnquiry.alternateMobileNumber}</td>
                  <td>
                    <TextFormat type="date" value={admissionEnquiry.dateOfBirth} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{admissionEnquiry.email}</td>
                  <td>{admissionEnquiry.sex}</td>
                  <td>{admissionEnquiry.comments}</td>
                  <td>{admissionEnquiry.courseApplyingFor}</td>
                  <td>{admissionEnquiry.highestQualification}</td>
                  <td>{admissionEnquiry.modeOfEnquiry}</td>
                  <td>{admissionEnquiry.status}</td>
                  <td>{admissionEnquiry.description}</td>
                  <td>
                    <TextFormat type="date" value={admissionEnquiry.enquiryDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={admissionEnquiry.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{admissionEnquiry.updatedBy}</td>
                  <td>
                    {admissionEnquiry.branchId ? <Link to={`branch/${admissionEnquiry.branchId}`}>{admissionEnquiry.branchId}</Link> : ''}
                  </td>
                  <td>
                    {admissionEnquiry.departmentId ? (
                      <Link to={`department/${admissionEnquiry.departmentId}`}>{admissionEnquiry.departmentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {admissionEnquiry.batchId ? <Link to={`batch/${admissionEnquiry.batchId}`}>{admissionEnquiry.batchId}</Link> : ''}
                  </td>
                  <td>
                    {admissionEnquiry.stateId ? <Link to={`state/${admissionEnquiry.stateId}`}>{admissionEnquiry.stateId}</Link> : ''}
                  </td>
                  <td>{admissionEnquiry.cityId ? <Link to={`city/${admissionEnquiry.cityId}`}>{admissionEnquiry.cityId}</Link> : ''}</td>
                  <td>
                    {admissionEnquiry.countryId ? (
                      <Link to={`country/${admissionEnquiry.countryId}`}>{admissionEnquiry.countryId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${admissionEnquiry.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${admissionEnquiry.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${admissionEnquiry.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ admissionEnquiry }: IRootState) => ({
  admissionEnquiryList: admissionEnquiry.entities
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
)(AdmissionEnquiry);
