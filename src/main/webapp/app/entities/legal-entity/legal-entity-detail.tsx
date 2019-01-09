import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './legal-entity.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILegalEntityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class LegalEntityDetail extends React.Component<ILegalEntityDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { legalEntityEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.legalEntity.detail.title">LegalEntity</Translate> [<b>{legalEntityEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="logo">
                <Translate contentKey="cmsApp.legalEntity.logo">Logo</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.logo}</dd>
            <dt>
              <span id="legalNameOfTheCollege">
                <Translate contentKey="cmsApp.legalEntity.legalNameOfTheCollege">Legal Name Of The College</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.legalNameOfTheCollege}</dd>
            <dt>
              <span id="typeOfCollege">
                <Translate contentKey="cmsApp.legalEntity.typeOfCollege">Type Of College</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.typeOfCollege}</dd>
            <dt>
              <span id="dateOfIncorporation">
                <Translate contentKey="cmsApp.legalEntity.dateOfIncorporation">Date Of Incorporation</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.dateOfIncorporation} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="registeredOfficeAddress">
                <Translate contentKey="cmsApp.legalEntity.registeredOfficeAddress">Registered Office Address</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress}</dd>
            <dt>
              <span id="collegeIdentificationNumber">
                <Translate contentKey="cmsApp.legalEntity.collegeIdentificationNumber">College Identification Number</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.collegeIdentificationNumber}</dd>
            <dt>
              <span id="pan">
                <Translate contentKey="cmsApp.legalEntity.pan">Pan</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.pan}</dd>
            <dt>
              <span id="tan">
                <Translate contentKey="cmsApp.legalEntity.tan">Tan</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.tan}</dd>
            <dt>
              <span id="tanCircleNumber">
                <Translate contentKey="cmsApp.legalEntity.tanCircleNumber">Tan Circle Number</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.tanCircleNumber}</dd>
            <dt>
              <span id="citTdsLocation">
                <Translate contentKey="cmsApp.legalEntity.citTdsLocation">Cit Tds Location</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.citTdsLocation}</dd>
            <dt>
              <span id="formSignatory">
                <Translate contentKey="cmsApp.legalEntity.formSignatory">Form Signatory</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.formSignatory}</dd>
            <dt>
              <span id="pfNumber">
                <Translate contentKey="cmsApp.legalEntity.pfNumber">Pf Number</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.pfNumber}</dd>
            <dt>
              <span id="registrationDate">
                <Translate contentKey="cmsApp.legalEntity.registrationDate">Registration Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.registrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="esiNumber">
                <Translate contentKey="cmsApp.legalEntity.esiNumber">Esi Number</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.esiNumber}</dd>
            <dt>
              <span id="ptRegistrationDate">
                <Translate contentKey="cmsApp.legalEntity.ptRegistrationDate">Pt Registration Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.ptRegistrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ptSignatory">
                <Translate contentKey="cmsApp.legalEntity.ptSignatory">Pt Signatory</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.ptSignatory}</dd>
            <dt>
              <span id="ptNumber">
                <Translate contentKey="cmsApp.legalEntity.ptNumber">Pt Number</Translate>
              </span>
            </dt>
            <dd>{legalEntityEntity.ptNumber}</dd>
          </dl>
          <Button tag={Link} to="/entity/legal-entity" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/legal-entity/${legalEntityEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ legalEntity }: IRootState) => ({
  legalEntityEntity: legalEntity.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LegalEntityDetail);
