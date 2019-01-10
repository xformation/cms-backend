import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './legal-entity.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ILegalEntityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ILegalEntityUpdateState {
  isNew: boolean;
}

export class LegalEntityUpdate extends React.Component<ILegalEntityUpdateProps, ILegalEntityUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { legalEntityEntity } = this.props;
      const entity = {
        ...legalEntityEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/legal-entity');
  };

  render() {
    const { legalEntityEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.legalEntity.home.createOrEditLabel">
              <Translate contentKey="cmsApp.legalEntity.home.createOrEditLabel">Create or edit a LegalEntity</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : legalEntityEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="legal-entity-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="logoLabel" for="logo">
                    <Translate contentKey="cmsApp.legalEntity.logo">Logo</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-logo"
                    type="number"
                    className="form-control"
                    name="logo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="legalNameOfTheCollegeLabel" for="legalNameOfTheCollege">
                    <Translate contentKey="cmsApp.legalEntity.legalNameOfTheCollege">Legal Name Of The College</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-legalNameOfTheCollege"
                    type="text"
                    name="legalNameOfTheCollege"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfCollegeLabel">
                    <Translate contentKey="cmsApp.legalEntity.typeOfCollege">Type Of College</Translate>
                  </Label>
                  <AvInput
                    id="legal-entity-typeOfCollege"
                    type="select"
                    className="form-control"
                    name="typeOfCollege"
                    value={(!isNew && legalEntityEntity.typeOfCollege) || 'PRIVATE'}
                  >
                    <option value="PRIVATE">
                      <Translate contentKey="cmsApp.TypeOfCollege.PRIVATE" />
                    </option>
                    <option value="PUBLIC">
                      <Translate contentKey="cmsApp.TypeOfCollege.PUBLIC" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfIncorporationLabel" for="dateOfIncorporation">
                    <Translate contentKey="cmsApp.legalEntity.dateOfIncorporation">Date Of Incorporation</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-dateOfIncorporation"
                    type="date"
                    className="form-control"
                    name="dateOfIncorporation"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddressLabel" for="registeredOfficeAddress">
                    <Translate contentKey="cmsApp.legalEntity.registeredOfficeAddress">Registered Office Address</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-registeredOfficeAddress"
                    type="text"
                    name="registeredOfficeAddress"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="collegeIdentificationNumberLabel" for="collegeIdentificationNumber">
                    <Translate contentKey="cmsApp.legalEntity.collegeIdentificationNumber">College Identification Number</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-collegeIdentificationNumber"
                    type="text"
                    name="collegeIdentificationNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="panLabel" for="pan">
                    <Translate contentKey="cmsApp.legalEntity.pan">Pan</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-pan"
                    type="text"
                    name="pan"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanLabel" for="tan">
                    <Translate contentKey="cmsApp.legalEntity.tan">Tan</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-tan"
                    type="text"
                    name="tan"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanCircleNumberLabel" for="tanCircleNumber">
                    <Translate contentKey="cmsApp.legalEntity.tanCircleNumber">Tan Circle Number</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-tanCircleNumber"
                    type="text"
                    name="tanCircleNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="citTdsLocationLabel" for="citTdsLocation">
                    <Translate contentKey="cmsApp.legalEntity.citTdsLocation">Cit Tds Location</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-citTdsLocation"
                    type="text"
                    name="citTdsLocation"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="formSignatoryLabel" for="formSignatory">
                    <Translate contentKey="cmsApp.legalEntity.formSignatory">Form Signatory</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-formSignatory"
                    type="text"
                    name="formSignatory"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pfNumberLabel" for="pfNumber">
                    <Translate contentKey="cmsApp.legalEntity.pfNumber">Pf Number</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-pfNumber"
                    type="text"
                    name="pfNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registrationDateLabel" for="registrationDate">
                    <Translate contentKey="cmsApp.legalEntity.registrationDate">Registration Date</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-registrationDate"
                    type="date"
                    className="form-control"
                    name="registrationDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="esiNumberLabel" for="esiNumber">
                    <Translate contentKey="cmsApp.legalEntity.esiNumber">Esi Number</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-esiNumber"
                    type="number"
                    className="form-control"
                    name="esiNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptRegistrationDateLabel" for="ptRegistrationDate">
                    <Translate contentKey="cmsApp.legalEntity.ptRegistrationDate">Pt Registration Date</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-ptRegistrationDate"
                    type="date"
                    className="form-control"
                    name="ptRegistrationDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptSignatoryLabel" for="ptSignatory">
                    <Translate contentKey="cmsApp.legalEntity.ptSignatory">Pt Signatory</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-ptSignatory"
                    type="text"
                    name="ptSignatory"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptNumberLabel" for="ptNumber">
                    <Translate contentKey="cmsApp.legalEntity.ptNumber">Pt Number</Translate>
                  </Label>
                  <AvField
                    id="legal-entity-ptNumber"
                    type="number"
                    className="form-control"
                    name="ptNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/legal-entity" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  legalEntityEntity: storeState.legalEntity.entity,
  loading: storeState.legalEntity.loading,
  updating: storeState.legalEntity.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LegalEntityUpdate);
