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
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { IState } from 'app/shared/model/state.model';
import { getEntities as getStates } from 'app/entities/state/state.reducer';
import { ICity } from 'app/shared/model/city.model';
import { getEntities as getCities } from 'app/entities/city/city.reducer';
import { ICountry } from 'app/shared/model/country.model';
import { getEntities as getCountries } from 'app/entities/country/country.reducer';
import { getEntity, updateEntity, createEntity, reset } from './admission-enquiry.reducer';
import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdmissionEnquiryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdmissionEnquiryUpdateState {
  isNew: boolean;
  branchId: string;
  departmentId: string;
  batchId: string;
  stateId: string;
  cityId: string;
  countryId: string;
}

export class AdmissionEnquiryUpdate extends React.Component<IAdmissionEnquiryUpdateProps, IAdmissionEnquiryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: '0',
      departmentId: '0',
      batchId: '0',
      stateId: '0',
      cityId: '0',
      countryId: '0',
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
    this.props.getDepartments();
    this.props.getBatches();
    this.props.getStates();
    this.props.getCities();
    this.props.getCountries();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { admissionEnquiryEntity } = this.props;
      const entity = {
        ...admissionEnquiryEntity,
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
    this.props.history.push('/entity/admission-enquiry');
  };

  render() {
    const { admissionEnquiryEntity, branches, departments, batches, states, cities, countries, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.admissionEnquiry.home.createOrEditLabel">Create or edit a AdmissionEnquiry</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : admissionEnquiryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="admission-enquiry-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentNameLabel" for="studentName">
                    Student Name
                  </Label>
                  <AvField
                    id="admission-enquiry-studentName"
                    type="text"
                    name="studentName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentMiddleNameLabel" for="studentMiddleName">
                    Student Middle Name
                  </Label>
                  <AvField id="admission-enquiry-studentMiddleName" type="text" name="studentMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentLastNameLabel" for="studentLastName">
                    Student Last Name
                  </Label>
                  <AvField id="admission-enquiry-studentLastName" type="text" name="studentLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherNameLabel" for="fatherName">
                    Father Name
                  </Label>
                  <AvField id="admission-enquiry-fatherName" type="text" name="fatherName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherMiddleNameLabel" for="fatherMiddleName">
                    Father Middle Name
                  </Label>
                  <AvField id="admission-enquiry-fatherMiddleName" type="text" name="fatherMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherLastNameLabel" for="fatherLastName">
                    Father Last Name
                  </Label>
                  <AvField id="admission-enquiry-fatherLastName" type="text" name="fatherLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherNameLabel" for="motherName">
                    Mother Name
                  </Label>
                  <AvField id="admission-enquiry-motherName" type="text" name="motherName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherMiddleNameLabel" for="motherMiddleName">
                    Mother Middle Name
                  </Label>
                  <AvField id="admission-enquiry-motherMiddleName" type="text" name="motherMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherLastNameLabel" for="motherLastName">
                    Mother Last Name
                  </Label>
                  <AvField id="admission-enquiry-motherLastName" type="text" name="motherLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactNumberLabel" for="contactNumber">
                    Contact Number
                  </Label>
                  <AvField
                    id="admission-enquiry-contactNumber"
                    type="text"
                    name="contactNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateMobileNumberLabel" for="alternateMobileNumber">
                    Alternate Mobile Number
                  </Label>
                  <AvField id="admission-enquiry-alternateMobileNumber" type="text" name="alternateMobileNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfBirthLabel" for="dateOfBirth">
                    Date Of Birth
                  </Label>
                  <AvField
                    id="admission-enquiry-dateOfBirth"
                    type="date"
                    className="form-control"
                    name="dateOfBirth"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    Email
                  </Label>
                  <AvField id="admission-enquiry-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel">Sex</Label>
                  <AvInput
                    id="admission-enquiry-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && admissionEnquiryEntity.sex) || 'MALE'}
                  >
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="admission-enquiry-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="courseApplyingForLabel">Course Applying For</Label>
                  <AvInput
                    id="admission-enquiry-courseApplyingFor"
                    type="select"
                    className="form-control"
                    name="courseApplyingFor"
                    value={(!isNew && admissionEnquiryEntity.courseApplyingFor) || 'BTECH'}
                  >
                    <option value="BTECH">BTECH</option>
                    <option value="MTECH">MTECH</option>
                    <option value="BBA">BBA</option>
                    <option value="MBA">MBA</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="highestQualificationLabel" for="highestQualification">
                    Highest Qualification
                  </Label>
                  <AvField
                    id="admission-enquiry-highestQualification"
                    type="text"
                    name="highestQualification"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modeOfEnquiryLabel">Mode Of Enquiry</Label>
                  <AvInput
                    id="admission-enquiry-modeOfEnquiry"
                    type="select"
                    className="form-control"
                    name="modeOfEnquiry"
                    value={(!isNew && admissionEnquiryEntity.modeOfEnquiry) || 'INPERSON'}
                  >
                    <option value="INPERSON">INPERSON</option>
                    <option value="TELEPHONE">TELEPHONE</option>
                    <option value="EMAIL">EMAIL</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">Status</Label>
                  <AvInput
                    id="admission-enquiry-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && admissionEnquiryEntity.status) || 'FOLLOWUP'}
                  >
                    <option value="FOLLOWUP">FOLLOWUP</option>
                    <option value="DECLINED">DECLINED</option>
                    <option value="CONVERTED">CONVERTED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="description">
                    Description
                  </Label>
                  <AvField
                    id="admission-enquiry-description"
                    type="text"
                    name="description"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="enquiryDateLabel" for="enquiryDate">
                    Enquiry Date
                  </Label>
                  <AvField
                    id="admission-enquiry-enquiryDate"
                    type="date"
                    className="form-control"
                    name="enquiryDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="updatedOn">
                    Updated On
                  </Label>
                  <AvField id="admission-enquiry-updatedOn" type="date" className="form-control" name="updatedOn" />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField id="admission-enquiry-updatedBy" type="text" name="updatedBy" />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="admission-enquiry-branch" type="select" className="form-control" name="branchId">
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
                  <Label for="department.id">Department</Label>
                  <AvInput id="admission-enquiry-department" type="select" className="form-control" name="departmentId">
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="admission-enquiry-batch" type="select" className="form-control" name="batchId">
                    <option value="" key="0" />
                    {batches
                      ? batches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="state.id">State</Label>
                  <AvInput id="admission-enquiry-state" type="select" className="form-control" name="stateId">
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
                  <AvInput id="admission-enquiry-city" type="select" className="form-control" name="cityId">
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
                <AvGroup>
                  <Label for="country.id">Country</Label>
                  <AvInput id="admission-enquiry-country" type="select" className="form-control" name="countryId">
                    <option value="" key="0" />
                    {countries
                      ? countries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/admission-enquiry" replace color="info">
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
  departments: storeState.department.entities,
  batches: storeState.batch.entities,
  states: storeState.state.entities,
  cities: storeState.city.entities,
  countries: storeState.country.entities,
  admissionEnquiryEntity: storeState.admissionEnquiry.entity,
  loading: storeState.admissionEnquiry.loading,
  updating: storeState.admissionEnquiry.updating,
  updateSuccess: storeState.admissionEnquiry.updateSuccess
});

const mapDispatchToProps = {
  getBranches,
  getDepartments,
  getBatches,
  getStates,
  getCities,
  getCountries,
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
)(AdmissionEnquiryUpdate);
