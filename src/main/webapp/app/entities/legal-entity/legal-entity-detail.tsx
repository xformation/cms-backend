import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
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
            LegalEntity [<b>{legalEntityEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="logoPath">Logo Path</span>
            </dt>
            <dd>{legalEntityEntity.logoPath}</dd>
            <dt>
              <span id="logoFileName">Logo File Name</span>
            </dt>
            <dd>{legalEntityEntity.logoFileName}</dd>
            <dt>
              <span id="logoFile">Logo File</span>
            </dt>
            <dd>{legalEntityEntity.logoFile}</dd>
            <dt>
              <span id="legalNameOfTheCollege">Legal Name Of The College</span>
            </dt>
            <dd>{legalEntityEntity.legalNameOfTheCollege}</dd>
            <dt>
              <span id="typeOfCollege">Type Of College</span>
            </dt>
            <dd>{legalEntityEntity.typeOfCollege}</dd>
            <dt>
              <span id="dateOfIncorporation">Date Of Incorporation</span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.dateOfIncorporation} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="registeredOfficeAddress1">Registered Office Address 1</span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress1}</dd>
            <dt>
              <span id="registeredOfficeAddress2">Registered Office Address 2</span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress2}</dd>
            <dt>
              <span id="registeredOfficeAddress3">Registered Office Address 3</span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress3}</dd>
            <dt>
              <span id="registeredOfficeAddress4">Registered Office Address 4</span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress4}</dd>
            <dt>
              <span id="registeredOfficeAddress5">Registered Office Address 5</span>
            </dt>
            <dd>{legalEntityEntity.registeredOfficeAddress5}</dd>
            <dt>
              <span id="collegeIdentificationNumber">College Identification Number</span>
            </dt>
            <dd>{legalEntityEntity.collegeIdentificationNumber}</dd>
            <dt>
              <span id="pan">Pan</span>
            </dt>
            <dd>{legalEntityEntity.pan}</dd>
            <dt>
              <span id="tan">Tan</span>
            </dt>
            <dd>{legalEntityEntity.tan}</dd>
            <dt>
              <span id="tanCircleNumber">Tan Circle Number</span>
            </dt>
            <dd>{legalEntityEntity.tanCircleNumber}</dd>
            <dt>
              <span id="citTdsLocation">Cit Tds Location</span>
            </dt>
            <dd>{legalEntityEntity.citTdsLocation}</dd>
            <dt>
              <span id="formSignatory">Form Signatory</span>
            </dt>
            <dd>{legalEntityEntity.formSignatory}</dd>
            <dt>
              <span id="pfNumber">Pf Number</span>
            </dt>
            <dd>{legalEntityEntity.pfNumber}</dd>
            <dt>
              <span id="pfRegistrationDate">Pf Registration Date</span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.pfRegistrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="pfSignatory">Pf Signatory</span>
            </dt>
            <dd>{legalEntityEntity.pfSignatory}</dd>
            <dt>
              <span id="esiNumber">Esi Number</span>
            </dt>
            <dd>{legalEntityEntity.esiNumber}</dd>
            <dt>
              <span id="esiRegistrationDate">Esi Registration Date</span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.esiRegistrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="esiSignatory">Esi Signatory</span>
            </dt>
            <dd>{legalEntityEntity.esiSignatory}</dd>
            <dt>
              <span id="ptNumber">Pt Number</span>
            </dt>
            <dd>{legalEntityEntity.ptNumber}</dd>
            <dt>
              <span id="ptRegistrationDate">Pt Registration Date</span>
            </dt>
            <dd>
              <TextFormat value={legalEntityEntity.ptRegistrationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ptSignatory">Pt Signatory</span>
            </dt>
            <dd>{legalEntityEntity.ptSignatory}</dd>
            <dt>Branch</dt>
            <dd>{legalEntityEntity.branchId ? legalEntityEntity.branchId : ''}</dd>
            <dt>College</dt>
            <dd>{legalEntityEntity.collegeId ? legalEntityEntity.collegeId : ''}</dd>
            <dt>State</dt>
            <dd>{legalEntityEntity.stateId ? legalEntityEntity.stateId : ''}</dd>
            <dt>City</dt>
            <dd>{legalEntityEntity.cityId ? legalEntityEntity.cityId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/legal-entity" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/legal-entity/${legalEntityEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
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
