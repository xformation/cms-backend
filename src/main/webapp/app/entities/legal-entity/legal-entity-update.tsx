import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IState } from 'app/shared/model/state.model';
import { getEntities as getStates } from 'app/entities/state/state.reducer';
import { ICity } from 'app/shared/model/city.model';
import { getEntities as getCities } from 'app/entities/city/city.reducer';
import { getEntity, updateEntity, createEntity, reset } from './legal-entity.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILegalEntityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILegalEntityUpdateState {
  isNew: boolean;
  branchId: string;
  collegeId: string;
  stateId: string;
  cityId: string;
}

export class LegalEntityUpdate extends React.Component<ILegalEntityUpdateProps, ILegalEntityUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: '0',
      collegeId: '0',
      stateId: '0',
      cityId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getBranches();
    this.props.getColleges();
    this.props.getStates();
    this.props.getCities();
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
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/legal-entity');
  };

  render() {
    const { legalEntityEntity, branches, colleges, states, cities, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.legalEntity.home.createOrEditLabel">Create or edit a LegalEntity</h2>
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
                    <Label for="id">ID</Label>
                    <AvInput id="legal-entity-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="logoPathLabel" for="logoPath">
                    Logo Path
                  </Label>
                  <AvField id="legal-entity-logoPath" type="text" name="logoPath" />
                </AvGroup>
                <AvGroup>
                  <Label id="logoFileNameLabel" for="logoFileName">
                    Logo File Name
                  </Label>
                  <AvField id="legal-entity-logoFileName" type="text" name="logoFileName" />
                </AvGroup>
                <AvGroup>
                  <Label id="logoFileLabel" for="logoFile">
                    Logo File
                  </Label>
                  <AvField id="legal-entity-logoFile" type="text" name="logoFile" />
                </AvGroup>
                <AvGroup>
                  <Label id="legalNameOfTheCollegeLabel" for="legalNameOfTheCollege">
                    Legal Name Of The College
                  </Label>
                  <AvField
                    id="legal-entity-legalNameOfTheCollege"
                    type="text"
                    name="legalNameOfTheCollege"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfCollegeLabel">Type Of College</Label>
                  <AvInput
                    id="legal-entity-typeOfCollege"
                    type="select"
                    className="form-control"
                    name="typeOfCollege"
                    value={(!isNew && legalEntityEntity.typeOfCollege) || 'PRIVATE'}
                  >
                    <option value="PRIVATE">PRIVATE</option>
                    <option value="PUBLIC">PUBLIC</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfIncorporationLabel" for="dateOfIncorporation">
                    Date Of Incorporation
                  </Label>
                  <AvField
                    id="legal-entity-dateOfIncorporation"
                    type="date"
                    className="form-control"
                    name="dateOfIncorporation"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddress1Label" for="registeredOfficeAddress1">
                    Registered Office Address 1
                  </Label>
                  <AvField
                    id="legal-entity-registeredOfficeAddress1"
                    type="text"
                    name="registeredOfficeAddress1"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddress2Label" for="registeredOfficeAddress2">
                    Registered Office Address 2
                  </Label>
                  <AvField id="legal-entity-registeredOfficeAddress2" type="text" name="registeredOfficeAddress2" />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddress3Label" for="registeredOfficeAddress3">
                    Registered Office Address 3
                  </Label>
                  <AvField id="legal-entity-registeredOfficeAddress3" type="text" name="registeredOfficeAddress3" />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddress4Label" for="registeredOfficeAddress4">
                    Registered Office Address 4
                  </Label>
                  <AvField id="legal-entity-registeredOfficeAddress4" type="text" name="registeredOfficeAddress4" />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddress5Label" for="registeredOfficeAddress5">
                    Registered Office Address 5
                  </Label>
                  <AvField id="legal-entity-registeredOfficeAddress5" type="text" name="registeredOfficeAddress5" />
                </AvGroup>
                <AvGroup>
                  <Label id="collegeIdentificationNumberLabel" for="collegeIdentificationNumber">
                    College Identification Number
                  </Label>
                  <AvField
                    id="legal-entity-collegeIdentificationNumber"
                    type="text"
                    name="collegeIdentificationNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="panLabel" for="pan">
                    Pan
                  </Label>
                  <AvField
                    id="legal-entity-pan"
                    type="text"
                    name="pan"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanLabel" for="tan">
                    Tan
                  </Label>
                  <AvField
                    id="legal-entity-tan"
                    type="text"
                    name="tan"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanCircleNumberLabel" for="tanCircleNumber">
                    Tan Circle Number
                  </Label>
                  <AvField
                    id="legal-entity-tanCircleNumber"
                    type="text"
                    name="tanCircleNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="citTdsLocationLabel" for="citTdsLocation">
                    Cit Tds Location
                  </Label>
                  <AvField
                    id="legal-entity-citTdsLocation"
                    type="text"
                    name="citTdsLocation"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="formSignatoryLabel" for="formSignatory">
                    Form Signatory
                  </Label>
                  <AvField
                    id="legal-entity-formSignatory"
                    type="string"
                    className="form-control"
                    name="formSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pfNumberLabel" for="pfNumber">
                    Pf Number
                  </Label>
                  <AvField
                    id="legal-entity-pfNumber"
                    type="text"
                    name="pfNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pfRegistrationDateLabel" for="pfRegistrationDate">
                    Pf Registration Date
                  </Label>
                  <AvField
                    id="legal-entity-pfRegistrationDate"
                    type="date"
                    className="form-control"
                    name="pfRegistrationDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pfSignatoryLabel" for="pfSignatory">
                    Pf Signatory
                  </Label>
                  <AvField
                    id="legal-entity-pfSignatory"
                    type="string"
                    className="form-control"
                    name="pfSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="esiNumberLabel" for="esiNumber">
                    Esi Number
                  </Label>
                  <AvField
                    id="legal-entity-esiNumber"
                    type="text"
                    name="esiNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="esiRegistrationDateLabel" for="esiRegistrationDate">
                    Esi Registration Date
                  </Label>
                  <AvField
                    id="legal-entity-esiRegistrationDate"
                    type="date"
                    className="form-control"
                    name="esiRegistrationDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="esiSignatoryLabel" for="esiSignatory">
                    Esi Signatory
                  </Label>
                  <AvField
                    id="legal-entity-esiSignatory"
                    type="string"
                    className="form-control"
                    name="esiSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptNumberLabel" for="ptNumber">
                    Pt Number
                  </Label>
                  <AvField
                    id="legal-entity-ptNumber"
                    type="text"
                    name="ptNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptRegistrationDateLabel" for="ptRegistrationDate">
                    Pt Registration Date
                  </Label>
                  <AvField
                    id="legal-entity-ptRegistrationDate"
                    type="date"
                    className="form-control"
                    name="ptRegistrationDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptSignatoryLabel" for="ptSignatory">
                    Pt Signatory
                  </Label>
                  <AvField
                    id="legal-entity-ptSignatory"
                    type="string"
                    className="form-control"
                    name="ptSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="legal-entity-branch" type="select" className="form-control" name="branchId">
                    <option value="" key="0" />
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="legal-entity-college" type="select" className="form-control" name="collegeId">
                    <option value="" key="0" />
                    {colleges
                      ? colleges.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="state.id">State</Label>
                  <AvInput id="legal-entity-state" type="select" className="form-control" name="stateId">
                    <option value="" key="0" />
                    {states
                      ? states.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="city.id">City</Label>
                  <AvInput id="legal-entity-city" type="select" className="form-control" name="cityId">
                    <option value="" key="0" />
                    {cities
                      ? cities.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/legal-entity" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
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
  branches: storeState.branch.entities,
  colleges: storeState.college.entities,
  states: storeState.state.entities,
  cities: storeState.city.entities,
  legalEntityEntity: storeState.legalEntity.entity,
  loading: storeState.legalEntity.loading,
  updating: storeState.legalEntity.updating,
  updateSuccess: storeState.legalEntity.updateSuccess
});

const mapDispatchToProps = {
  getBranches,
  getColleges,
  getStates,
  getCities,
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
