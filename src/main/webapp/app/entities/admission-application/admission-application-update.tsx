import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
import { getEntities as getAdmissionEnquiries } from 'app/entities/admission-enquiry/admission-enquiry.reducer';
import { IAcademicHistory } from 'app/shared/model/academic-history.model';
import { getEntities as getAcademicHistories } from 'app/entities/academic-history/academic-history.reducer';
import { IDocuments } from 'app/shared/model/documents.model';
import { getEntities as getDocuments } from 'app/entities/documents/documents.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { IState } from 'app/shared/model/state.model';
import { getEntities as getStates } from 'app/entities/state/state.reducer';
import { ICity } from 'app/shared/model/city.model';
import { getEntities as getCities } from 'app/entities/city/city.reducer';
import { ICountry } from 'app/shared/model/country.model';
import { getEntities as getCountries } from 'app/entities/country/country.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdmissionApplicationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdmissionApplicationUpdateState {
  isNew: boolean;
  admissionEnquiryId: string;
  academicHistoryId: string;
  documentsId: string;
  branchId: string;
  batchId: string;
  stateId: string;
  cityId: string;
  countryId: string;
  departmentId: string;
  academicyearId: string;
}

export class AdmissionApplicationUpdate extends React.Component<IAdmissionApplicationUpdateProps, IAdmissionApplicationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      admissionEnquiryId: '0',
      academicHistoryId: '0',
      documentsId: '0',
      branchId: '0',
      batchId: '0',
      stateId: '0',
      cityId: '0',
      countryId: '0',
      departmentId: '0',
      academicyearId: '0',
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

    this.props.getAdmissionEnquiries();
    this.props.getAcademicHistories();
    this.props.getDocuments();
    this.props.getBranches();
    this.props.getBatches();
    this.props.getStates();
    this.props.getCities();
    this.props.getCountries();
    this.props.getDepartments();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { admissionApplicationEntity } = this.props;
      const entity = {
        ...admissionApplicationEntity,
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
    this.props.history.push('/entity/admission-application');
  };

  render() {
    const {
      admissionApplicationEntity,
      admissionEnquiries,
      academicHistories,
      documents,
      branches,
      batches,
      states,
      cities,
      countries,
      departments,
      academicYears,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.admissionApplication.home.createOrEditLabel">Create or edit a AdmissionApplication</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : admissionApplicationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="admission-application-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="admissionStatusLabel">Admission Status</Label>
                  <AvInput
                    id="admission-application-admissionStatus"
                    type="select"
                    className="form-control"
                    name="admissionStatus"
                    value={(!isNew && admissionApplicationEntity.admissionStatus) || 'INPROCESS'}
                  >
                    <option value="INPROCESS">INPROCESS</option>
                    <option value="DECLINED">DECLINED</option>
                    <option value="ACCEPTED">ACCEPTED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="studentNameLabel" for="studentName">
                    Student Name
                  </Label>
                  <AvField id="admission-application-studentName" type="text" name="studentName" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentMiddleNameLabel" for="studentMiddleName">
                    Student Middle Name
                  </Label>
                  <AvField id="admission-application-studentMiddleName" type="text" name="studentMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentLastNameLabel" for="studentLastName">
                    Student Last Name
                  </Label>
                  <AvField id="admission-application-studentLastName" type="text" name="studentLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherNameLabel" for="fatherName">
                    Father Name
                  </Label>
                  <AvField id="admission-application-fatherName" type="text" name="fatherName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherMiddleNameLabel" for="fatherMiddleName">
                    Father Middle Name
                  </Label>
                  <AvField id="admission-application-fatherMiddleName" type="text" name="fatherMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherLastNameLabel" for="fatherLastName">
                    Father Last Name
                  </Label>
                  <AvField id="admission-application-fatherLastName" type="text" name="fatherLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherNameLabel" for="motherName">
                    Mother Name
                  </Label>
                  <AvField id="admission-application-motherName" type="text" name="motherName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherMiddleNameLabel" for="motherMiddleName">
                    Mother Middle Name
                  </Label>
                  <AvField id="admission-application-motherMiddleName" type="text" name="motherMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherLastNameLabel" for="motherLastName">
                    Mother Last Name
                  </Label>
                  <AvField id="admission-application-motherLastName" type="text" name="motherLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="contactNumberLabel" for="contactNumber">
                    Contact Number
                  </Label>
                  <AvField id="admission-application-contactNumber" type="text" name="contactNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateMobileNumberLabel" for="alternateMobileNumber">
                    Alternate Mobile Number
                  </Label>
                  <AvField id="admission-application-alternateMobileNumber" type="text" name="alternateMobileNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfBirthLabel" for="dateOfBirth">
                    Date Of Birth
                  </Label>
                  <AvField id="admission-application-dateOfBirth" type="date" className="form-control" name="dateOfBirth" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    Email
                  </Label>
                  <AvField id="admission-application-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel">Sex</Label>
                  <AvInput
                    id="admission-application-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && admissionApplicationEntity.sex) || 'MALE'}
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
                  <AvField id="admission-application-comments" type="text" name="comments" />
                </AvGroup>
                <AvGroup>
                  <Label id="applicationIdLabel" for="applicationId">
                    Application Id
                  </Label>
                  <AvField id="admission-application-applicationId" type="string" className="form-control" name="applicationId" />
                </AvGroup>
                <AvGroup>
                  <Label id="uploadPhotoLabel" for="uploadPhoto">
                    Upload Photo
                  </Label>
                  <AvField id="admission-application-uploadPhoto" type="text" name="uploadPhoto" />
                </AvGroup>
                <AvGroup>
                  <Label id="courseLabel">Course</Label>
                  <AvInput
                    id="admission-application-course"
                    type="select"
                    className="form-control"
                    name="course"
                    value={(!isNew && admissionApplicationEntity.course) || 'BTECH'}
                  >
                    <option value="BTECH">BTECH</option>
                    <option value="MTECH">MTECH</option>
                    <option value="BBA">BBA</option>
                    <option value="MBA">MBA</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="admissionDateLabel" for="admissionDate">
                    Admission Date
                  </Label>
                  <AvField id="admission-application-admissionDate" type="date" className="form-control" name="admissionDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="admissionEnquiry.id">Admission Enquiry</Label>
                  <AvInput id="admission-application-admissionEnquiry" type="select" className="form-control" name="admissionEnquiryId">
                    <option value="" key="0" />
                    {admissionEnquiries
                      ? admissionEnquiries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicHistory.id">Academic History</Label>
                  <AvInput id="admission-application-academicHistory" type="select" className="form-control" name="academicHistoryId">
                    <option value="" key="0" />
                    {academicHistories
                      ? academicHistories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="documents.id">Documents</Label>
                  <AvInput id="admission-application-documents" type="select" className="form-control" name="documentsId">
                    <option value="" key="0" />
                    {documents
                      ? documents.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="admission-application-branch" type="select" className="form-control" name="branchId">
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
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="admission-application-batch" type="select" className="form-control" name="batchId">
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
                  <AvInput id="admission-application-state" type="select" className="form-control" name="stateId">
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
                  <AvInput id="admission-application-city" type="select" className="form-control" name="cityId">
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
                  <AvInput id="admission-application-country" type="select" className="form-control" name="countryId">
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
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="admission-application-department" type="select" className="form-control" name="departmentId">
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
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput id="admission-application-academicyear" type="select" className="form-control" name="academicyearId">
                    <option value="" key="0" />
                    {academicYears
                      ? academicYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/admission-application" replace color="info">
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
  admissionEnquiries: storeState.admissionEnquiry.entities,
  academicHistories: storeState.academicHistory.entities,
  documents: storeState.documents.entities,
  branches: storeState.branch.entities,
  batches: storeState.batch.entities,
  states: storeState.state.entities,
  cities: storeState.city.entities,
  countries: storeState.country.entities,
  departments: storeState.department.entities,
  academicYears: storeState.academicYear.entities,
  admissionApplicationEntity: storeState.admissionApplication.entity,
  loading: storeState.admissionApplication.loading,
  updating: storeState.admissionApplication.updating,
  updateSuccess: storeState.admissionApplication.updateSuccess
});

const mapDispatchToProps = {
  getAdmissionEnquiries,
  getAcademicHistories,
  getDocuments,
  getBranches,
  getBatches,
  getStates,
  getCities,
  getCountries,
  getDepartments,
  getAcademicYears,
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
)(AdmissionApplicationUpdate);
