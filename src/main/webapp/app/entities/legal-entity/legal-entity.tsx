import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
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
          <Translate contentKey="cmsApp.legalEntity.home.title">Legal Entities</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.legalEntity.home.createLabel">Create new Legal Entity</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('cmsApp.legalEntity.home.search')}
                  />
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
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.logo">Logo</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.legalNameOfTheCollege">Legal Name Of The College</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.typeOfCollege">Type Of College</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.dateOfIncorporation">Date Of Incorporation</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.registeredOfficeAddress">Registered Office Address</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.collegeIdentificationNumber">College Identification Number</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.pan">Pan</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.tan">Tan</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.tanCircleNumber">Tan Circle Number</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.citTdsLocation">Cit Tds Location</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.formSignatory">Form Signatory</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.pfNumber">Pf Number</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.registrationDate">Registration Date</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.esiNumber">Esi Number</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.ptRegistrationDate">Pt Registration Date</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.ptSignatory">Pt Signatory</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.legalEntity.ptNumber">Pt Number</Translate>
                </th>
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
                  <td>
                    <Translate contentKey={`cmsApp.TypeOfCollege.${legalEntity.typeOfCollege}`} />
                  </td>
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
                    <TextFormat type="date" value={legalEntity.registrationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.esiNumber}</td>
                  <td>
                    <TextFormat type="date" value={legalEntity.ptRegistrationDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{legalEntity.ptSignatory}</td>
                  <td>{legalEntity.ptNumber}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legalEntity.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
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
