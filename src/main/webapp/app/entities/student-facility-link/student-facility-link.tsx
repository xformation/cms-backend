import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './student-facility-link.reducer';
import { IStudentFacilityLink } from 'app/shared/model/student-facility-link.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentFacilityLinkProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IStudentFacilityLinkState {
  search: string;
}

export class StudentFacilityLink extends React.Component<IStudentFacilityLinkProps, IStudentFacilityLinkState> {
  state: IStudentFacilityLinkState = {
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
    const { studentFacilityLinkList, match } = this.props;
    return (
      <div>
        <h2 id="student-facility-link-heading">
          Student Facility Links
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Student Facility Link
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
                <th>Link Desc</th>
                <th>Student</th>
                <th>Facility</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {studentFacilityLinkList.map((studentFacilityLink, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${studentFacilityLink.id}`} color="link" size="sm">
                      {studentFacilityLink.id}
                    </Button>
                  </td>
                  <td>{studentFacilityLink.linkDesc}</td>
                  <td>
                    {studentFacilityLink.studentId ? (
                      <Link to={`student/${studentFacilityLink.studentId}`}>{studentFacilityLink.studentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {studentFacilityLink.facilityId ? (
                      <Link to={`facility/${studentFacilityLink.facilityId}`}>{studentFacilityLink.facilityId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${studentFacilityLink.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentFacilityLink.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentFacilityLink.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ studentFacilityLink }: IRootState) => ({
  studentFacilityLinkList: studentFacilityLink.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StudentFacilityLink);
