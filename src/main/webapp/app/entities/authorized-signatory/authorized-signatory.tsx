import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './authorized-signatory.reducer';
import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAuthorizedSignatoryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAuthorizedSignatoryState {
  search: string;
}

export class AuthorizedSignatory extends React.Component<IAuthorizedSignatoryProps, IAuthorizedSignatoryState> {
  state: IAuthorizedSignatoryState = {
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
    const { authorizedSignatoryList, match } = this.props;
    return (
      <div>
        <h2 id="authorized-signatory-heading">
          Authorized Signatories
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Authorized Signatory
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
                <th>Signatory Name</th>
                <th>Signatory Father Name</th>
                <th>Signatory Designation</th>
                <th>Address 1</th>
                <th>Address 2</th>
                <th>Address 3</th>
                <th>Address 4</th>
                <th>Address 5</th>
                <th>Email</th>
                <th>Pan Card Number</th>
                <th>Branch</th>
                <th>College</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {authorizedSignatoryList.map((authorizedSignatory, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${authorizedSignatory.id}`} color="link" size="sm">
                      {authorizedSignatory.id}
                    </Button>
                  </td>
                  <td>{authorizedSignatory.signatoryName}</td>
                  <td>{authorizedSignatory.signatoryFatherName}</td>
                  <td>{authorizedSignatory.signatoryDesignation}</td>
                  <td>{authorizedSignatory.address1}</td>
                  <td>{authorizedSignatory.address2}</td>
                  <td>{authorizedSignatory.address3}</td>
                  <td>{authorizedSignatory.address4}</td>
                  <td>{authorizedSignatory.address5}</td>
                  <td>{authorizedSignatory.email}</td>
                  <td>{authorizedSignatory.panCardNumber}</td>
                  <td>
                    {authorizedSignatory.branchId ? (
                      <Link to={`branch/${authorizedSignatory.branchId}`}>{authorizedSignatory.branchId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {authorizedSignatory.collegeId ? (
                      <Link to={`college/${authorizedSignatory.collegeId}`}>{authorizedSignatory.collegeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${authorizedSignatory.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${authorizedSignatory.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${authorizedSignatory.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ authorizedSignatory }: IRootState) => ({
  authorizedSignatoryList: authorizedSignatory.entities
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
)(AuthorizedSignatory);
