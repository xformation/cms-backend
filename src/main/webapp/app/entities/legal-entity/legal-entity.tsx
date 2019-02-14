import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './legal-entity.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILegalEntityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ILegalEntityState {
  search: string;
}

export class LegalEntity extends React.Component<ILegalEntityProps, ILegalEntityState> {
  state: ILegalEntityState = {
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
    const { legalEntityList, match } = this.props;
    return (
      <div>
        <h2 id="legal-entity-heading">
          Legal Entities
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Legal Entity
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
                <th>Logo</th>
                <th>Legal Name Of The College</th>
                <th>Type Of College</th>
                <th>Date Of Incorporation</th>
                <th>Registered Office Address</th>
                <th>College Identification Number</th>
                <th>Pan</th>
                <th>Tan</th>
                <th>Tan Circle Number</th>
                <th>Cit Tds Location</th>
                <th>Form Signatory</th>
                <th>Pf Number</th>
                <th>Pf Registration Date</th>
                <th>Pf Signatory</th>
                <th>Esi Number</th>
                <th>Esi Registration Date</th>
                <th>Esi Signatory</th>
                <th>Pt Number</th>
                <th>Pt Registration Date</th>
                <th>Pt Signatory</th>
                <th>Branch</th>
                <th>College</th>
                <th>State</th>
                <th>City</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {legalEntityList.map((legalEntity, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${legalEntity.id}`} color="link" size="sm">
                      {legalEntity.id}
                    </Button>
                  </td>
                  <td>{legalEntity.logo}</td>
                  <td>{legalEntity.legalNameOfTheCollege}</td>
                  <td>{legalEntity.typeOfCollege}</td>
                  <td>
                    <TextFormat type="date" value={legalEntity.dateOfIncorporation} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.registeredOfficeAddress}</td>
                  <td>{legalEntity.collegeIdentificationNumber}</td>
                  <td>{legalEntity.pan}</td>
                  <td>{legalEntity.tan}</td>
                  <td>{legalEntity.tanCircleNumber}</td>
                  <td>{legalEntity.citTdsLocation}</td>
                  <td>{legalEntity.formSignatory}</td>
                  <td>{legalEntity.pfNumber}</td>
                  <td>
                    <TextFormat type="date" value={legalEntity.pfRegistrationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.pfSignatory}</td>
                  <td>{legalEntity.esiNumber}</td>
                  <td>
                    <TextFormat type="date" value={legalEntity.esiRegistrationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.esiSignatory}</td>
                  <td>{legalEntity.ptNumber}</td>
                  <td>
                    <TextFormat type="date" value={legalEntity.ptRegistrationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.ptSignatory}</td>
                  <td>{legalEntity.branchId ? <Link to={`branch/${legalEntity.branchId}`}>{legalEntity.branchId}</Link> : ''}</td>
                  <td>{legalEntity.collegeId ? <Link to={`college/${legalEntity.collegeId}`}>{legalEntity.collegeId}</Link> : ''}</td>
                  <td>{legalEntity.stateId ? <Link to={`state/${legalEntity.stateId}`}>{legalEntity.stateId}</Link> : ''}</td>
                  <td>{legalEntity.cityId ? <Link to={`city/${legalEntity.cityId}`}>{legalEntity.cityId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ legalEntity }: IRootState) => ({
  legalEntityList: legalEntity.entities
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
)(LegalEntity);
