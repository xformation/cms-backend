import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IEmployeeState {
  search: string;
}

export class Employee extends React.Component<IEmployeeProps, IEmployeeState> {
  state: IEmployeeState = {
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
    const { employeeList, match } = this.props;
    return (
      <div>
        <h2 id="employee-heading">
          Employees
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Employee
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
                <th>Employee Name</th>
                <th>Designation</th>
                <th>Joining Date</th>
                <th>Job End Date</th>
                <th>Resignation Date</th>
                <th>Resignation Acceptance Date</th>
                <th>Aadhar No</th>
                <th>Pan No</th>
                <th>Passport No</th>
                <th>Primary Contact No</th>
                <th>Secondary Contact No</th>
                <th>Employee Father Name</th>
                <th>Employee Mother Name</th>
                <th>Primary Address</th>
                <th>Secondary Address</th>
                <th>Employee Address</th>
                <th>Personal Mail Id</th>
                <th>Official Mail Id</th>
                <th>Disability</th>
                <th>Driving Licence No</th>
                <th>Driving Licence Validity</th>
                <th>Gender</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {employeeList.map((employee, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${employee.id}`} color="link" size="sm">
                      {employee.id}
                    </Button>
                  </td>
                  <td>{employee.employeeName}</td>
                  <td>{employee.designation}</td>
                  <td>
                    <TextFormat type="date" value={employee.joiningDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={employee.jobEndDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={employee.resignationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={employee.resignationAcceptanceDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{employee.aadharNo}</td>
                  <td>{employee.panNo}</td>
                  <td>{employee.passportNo}</td>
                  <td>{employee.primaryContactNo}</td>
                  <td>{employee.secondaryContactNo}</td>
                  <td>{employee.employeeFatherName}</td>
                  <td>{employee.employeeMotherName}</td>
                  <td>{employee.primaryAddress}</td>
                  <td>{employee.secondaryAddress}</td>
                  <td>{employee.employeeAddress}</td>
                  <td>{employee.personalMailId}</td>
                  <td>{employee.officialMailId}</td>
                  <td>{employee.disability}</td>
                  <td>{employee.drivingLicenceNo}</td>
                  <td>
                    <TextFormat type="date" value={employee.drivingLicenceValidity} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{employee.gender}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${employee.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${employee.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${employee.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeList: employee.entities
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
)(Employee);
