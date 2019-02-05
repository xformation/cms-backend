import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentUpdateState {
  isNew: boolean;
  departmentId: number;
  batchId: number;
  sectionId: number;
  branchId: number;
}

export class StudentUpdate extends React.Component<IStudentUpdateProps, IStudentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: 0,
      batchId: 0,
      sectionId: 0,
      branchId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getDepartments();
    this.props.getBatches();
    this.props.getSections();
    this.props.getBranches();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentEntity } = this.props;
      const entity = {
        ...studentEntity,
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
    this.props.history.push('/entity/student');
  };

  render() {
    const { studentEntity, departments, batches, sections, branches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.student.home.createOrEditLabel">Create or edit a Student</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="student-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentNameLabel" for="studentName">
                    Student Name
                  </Label>
                  <AvField
                    id="student-studentName"
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
                  <AvField
                    id="student-studentMiddleName"
                    type="text"
                    name="studentMiddleName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentLastNameLabel" for="studentLastName">
                    Student Last Name
                  </Label>
                  <AvField
                    id="student-studentLastName"
                    type="text"
                    name="studentLastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherNameLabel" for="fatherName">
                    Father Name
                  </Label>
                  <AvField
                    id="student-fatherName"
                    type="text"
                    name="fatherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherMiddleNameLabel" for="fatherMiddleName">
                    Father Middle Name
                  </Label>
                  <AvField
                    id="student-fatherMiddleName"
                    type="text"
                    name="fatherMiddleName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fatherLastNameLabel" for="fatherLastName">
                    Father Last Name
                  </Label>
                  <AvField
                    id="student-fatherLastName"
                    type="text"
                    name="fatherLastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motherNameLabel" for="motherName">
                    Mother Name
                  </Label>
                  <AvField
                    id="student-motherName"
                    type="text"
                    name="motherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motherMiddleNameLabel" for="motherMiddleName">
                    Mother Middle Name
                  </Label>
                  <AvField
                    id="student-motherMiddleName"
                    type="text"
                    name="motherMiddleName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motherLastNameLabel" for="motherLastName">
                    Mother Last Name
                  </Label>
                  <AvField
                    id="student-motherLastName"
                    type="text"
                    name="motherLastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="aadharNoLabel" for="aadharNo">
                    Aadhar No
                  </Label>
                  <AvField
                    id="student-aadharNo"
                    type="number"
                    className="form-control"
                    name="aadharNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfBirthLabel" for="dateOfBirth">
                    Date Of Birth
                  </Label>
                  <AvField
                    id="student-dateOfBirth"
                    type="date"
                    className="form-control"
                    name="dateOfBirth"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="placeOfBirthLabel" for="placeOfBirth">
                    Place Of Birth
                  </Label>
                  <AvField
                    id="student-placeOfBirth"
                    type="text"
                    name="placeOfBirth"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="religionLabel">Religion</Label>
                  <AvInput
                    id="student-religion"
                    type="select"
                    className="form-control"
                    name="religion"
                    value={(!isNew && studentEntity.religion) || 'HINDU'}
                  >
                    <option value="HINDU">HINDU</option>
                    <option value="MUSLIM">MUSLIM</option>
                    <option value="CHRISTIAN">CHRISTIAN</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="casteLabel">Caste</Label>
                  <AvInput
                    id="student-caste"
                    type="select"
                    className="form-control"
                    name="caste"
                    value={(!isNew && studentEntity.caste) || 'OC'}
                  >
                    <option value="OC">OC</option>
                    <option value="BC">BC</option>
                    <option value="SC">SC</option>
                    <option value="ST">ST</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="subCasteLabel" for="subCaste">
                    Sub Caste
                  </Label>
                  <AvField
                    id="student-subCaste"
                    type="text"
                    name="subCaste"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ageLabel" for="age">
                    Age
                  </Label>
                  <AvField
                    id="student-age"
                    type="number"
                    className="form-control"
                    name="age"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel">Sex</Label>
                  <AvInput
                    id="student-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && studentEntity.sex) || 'MALE'}
                  >
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="bloodGroupLabel">Blood Group</Label>
                  <AvInput
                    id="student-bloodGroup"
                    type="select"
                    className="form-control"
                    name="bloodGroup"
                    value={(!isNew && studentEntity.bloodGroup) || 'ABPOSITIVE'}
                  >
                    <option value="ABPOSITIVE">ABPOSITIVE</option>
                    <option value="ABNEGATIVE">ABNEGATIVE</option>
                    <option value="OPOSITIVE">OPOSITIVE</option>
                    <option value="BPOSITIVE">BPOSITIVE</option>
                    <option value="BNEGATIVE">BNEGATIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="addressLineOneLabel" for="addressLineOne">
                    Address Line One
                  </Label>
                  <AvField
                    id="student-addressLineOne"
                    type="text"
                    name="addressLineOne"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLineTwoLabel" for="addressLineTwo">
                    Address Line Two
                  </Label>
                  <AvField
                    id="student-addressLineTwo"
                    type="text"
                    name="addressLineTwo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLineThreeLabel" for="addressLineThree">
                    Address Line Three
                  </Label>
                  <AvField
                    id="student-addressLineThree"
                    type="text"
                    name="addressLineThree"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="townLabel" for="town">
                    Town
                  </Label>
                  <AvField
                    id="student-town"
                    type="text"
                    name="town"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="stateLabel" for="state">
                    State
                  </Label>
                  <AvField
                    id="student-state"
                    type="text"
                    name="state"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="countryLabel" for="country">
                    Country
                  </Label>
                  <AvField
                    id="student-country"
                    type="text"
                    name="country"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pincodeLabel" for="pincode">
                    Pincode
                  </Label>
                  <AvField
                    id="student-pincode"
                    type="number"
                    className="form-control"
                    name="pincode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentContactNumberLabel" for="studentContactNumber">
                    Student Contact Number
                  </Label>
                  <AvField
                    id="student-studentContactNumber"
                    type="number"
                    className="form-control"
                    name="studentContactNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateContactNumberLabel" for="alternateContactNumber">
                    Alternate Contact Number
                  </Label>
                  <AvField
                    id="student-alternateContactNumber"
                    type="number"
                    className="form-control"
                    name="alternateContactNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentEmailAddressLabel" for="studentEmailAddress">
                    Student Email Address
                  </Label>
                  <AvField
                    id="student-studentEmailAddress"
                    type="text"
                    name="studentEmailAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateEmailAddressLabel" for="alternateEmailAddress">
                    Alternate Email Address
                  </Label>
                  <AvField
                    id="student-alternateEmailAddress"
                    type="text"
                    name="alternateEmailAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="relationWithStudentLabel">Relation With Student</Label>
                  <AvInput
                    id="student-relationWithStudent"
                    type="select"
                    className="form-control"
                    name="relationWithStudent"
                    value={(!isNew && studentEntity.relationWithStudent) || 'FATHER'}
                  >
                    <option value="FATHER">FATHER</option>
                    <option value="MOTHER">MOTHER</option>
                    <option value="GUARDIAN">GUARDIAN</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    Name
                  </Label>
                  <AvField
                    id="student-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="middleNameLabel" for="middleName">
                    Middle Name
                  </Label>
                  <AvField
                    id="student-middleName"
                    type="text"
                    name="middleName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastNameLabel" for="lastName">
                    Last Name
                  </Label>
                  <AvField
                    id="student-lastName"
                    type="text"
                    name="lastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="contactNoLabel" for="contactNo">
                    Contact No
                  </Label>
                  <AvField
                    id="student-contactNo"
                    type="number"
                    className="form-control"
                    name="contactNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailAddressLabel" for="emailAddress">
                    Email Address
                  </Label>
                  <AvField
                    id="student-emailAddress"
                    type="text"
                    name="emailAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="transportLabel">Transport</Label>
                  <AvInput
                    id="student-transport"
                    type="select"
                    className="form-control"
                    name="transport"
                    value={(!isNew && studentEntity.transport) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="messLabel">Mess</Label>
                  <AvInput
                    id="student-mess"
                    type="select"
                    className="form-control"
                    name="mess"
                    value={(!isNew && studentEntity.mess) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="gymLabel">Gym</Label>
                  <AvInput
                    id="student-gym"
                    type="select"
                    className="form-control"
                    name="gym"
                    value={(!isNew && studentEntity.gym) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="culturalClassLabel">Cultural Class</Label>
                  <AvInput
                    id="student-culturalClass"
                    type="select"
                    className="form-control"
                    name="culturalClass"
                    value={(!isNew && studentEntity.culturalClass) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="libraryLabel">Library</Label>
                  <AvInput
                    id="student-library"
                    type="select"
                    className="form-control"
                    name="library"
                    value={(!isNew && studentEntity.library) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="sportsLabel">Sports</Label>
                  <AvInput
                    id="student-sports"
                    type="select"
                    className="form-control"
                    name="sports"
                    value={(!isNew && studentEntity.sports) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="swimmingLabel">Swimming</Label>
                  <AvInput
                    id="student-swimming"
                    type="select"
                    className="form-control"
                    name="swimming"
                    value={(!isNew && studentEntity.swimming) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="extraClassLabel">Extra Class</Label>
                  <AvInput
                    id="student-extraClass"
                    type="select"
                    className="form-control"
                    name="extraClass"
                    value={(!isNew && studentEntity.extraClass) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="handicraftsLabel">Handicrafts</Label>
                  <AvInput
                    id="student-handicrafts"
                    type="select"
                    className="form-control"
                    name="handicrafts"
                    value={(!isNew && studentEntity.handicrafts) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="addLabel">Add</Label>
                  <AvInput
                    id="student-add"
                    type="select"
                    className="form-control"
                    name="add"
                    value={(!isNew && studentEntity.add) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="uploadPhotoLabel" for="uploadPhoto">
                    Upload Photo
                  </Label>
                  <AvField
                    id="student-uploadPhoto"
                    type="number"
                    className="form-control"
                    name="uploadPhoto"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="admissionNoLabel" for="admissionNo">
                    Admission No
                  </Label>
                  <AvField
                    id="student-admissionNo"
                    type="number"
                    className="form-control"
                    name="admissionNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="rollNoLabel" for="rollNo">
                    Roll No
                  </Label>
                  <AvField
                    id="student-rollNo"
                    type="text"
                    name="rollNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentTypeLabel">Student Type</Label>
                  <AvInput
                    id="student-studentType"
                    type="select"
                    className="form-control"
                    name="studentType"
                    value={(!isNew && studentEntity.studentType) || 'REGULAR'}
                  >
                    <option value="REGULAR">REGULAR</option>
                    <option value="STAFF_CONCESSION">STAFF_CONCESSION</option>
                    <option value="BENEFITS">BENEFITS</option>
                    <option value="SCHOLARSHIP">SCHOLARSHIP</option>
                    <option value="OTHER_BENEFITS">OTHER_BENEFITS</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="student-department" type="select" className="form-control" name="departmentId">
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
                  <AvInput id="student-batch" type="select" className="form-control" name="batchId">
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
                  <Label for="section.id">Section</Label>
                  <AvInput id="student-section" type="select" className="form-control" name="sectionId">
                    {sections
                      ? sections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="student-branch" type="select" className="form-control" name="branchId">
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student" replace color="info">
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
  departments: storeState.department.entities,
  batches: storeState.batch.entities,
  sections: storeState.section.entities,
  branches: storeState.branch.entities,
  studentEntity: storeState.student.entity,
  loading: storeState.student.loading,
  updating: storeState.student.updating
});

const mapDispatchToProps = {
  getDepartments,
  getBatches,
  getSections,
  getBranches,
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
)(StudentUpdate);
