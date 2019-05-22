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
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './teacher.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITeacherUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITeacherUpdateState {
  isNew: boolean;
  departmentId: string;
  branchId: string;
}

export class TeacherUpdate extends React.Component<ITeacherUpdateProps, ITeacherUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: '0',
      branchId: '0',
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

    this.props.getDepartments();
    this.props.getBranches();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { teacherEntity } = this.props;
      const entity = {
        ...teacherEntity,
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
    this.props.history.push('/entity/teacher');
  };

  render() {
    const { teacherEntity, departments, branches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.teacher.home.createOrEditLabel">Create or edit a Teacher</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : teacherEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="teacher-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="teacherNameLabel" for="teacherName">
                    Teacher Name
                  </Label>
                  <AvField
                    id="teacher-teacherName"
                    type="text"
                    name="teacherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="teacherMiddleNameLabel" for="teacherMiddleName">
                    Teacher Middle Name
                  </Label>
                  <AvField
                    id="teacher-teacherMiddleName"
                    type="text"
                    name="teacherMiddleName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="teacherLastNameLabel" for="teacherLastName">
                    Teacher Last Name
                  </Label>
                  <AvField
                    id="teacher-teacherLastName"
                    type="text"
                    name="teacherLastName"
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
                    id="teacher-fatherName"
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
                    id="teacher-fatherMiddleName"
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
                    id="teacher-fatherLastName"
                    type="text"
                    name="fatherLastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="spouseNameLabel" for="spouseName">
                    Spouse Name
                  </Label>
                  <AvField id="teacher-spouseName" type="text" name="spouseName" />
                </AvGroup>
                <AvGroup>
                  <Label id="spouseMiddleNameLabel" for="spouseMiddleName">
                    Spouse Middle Name
                  </Label>
                  <AvField id="teacher-spouseMiddleName" type="text" name="spouseMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="spouseLastNameLabel" for="spouseLastName">
                    Spouse Last Name
                  </Label>
                  <AvField id="teacher-spouseLastName" type="text" name="spouseLastName" />
                </AvGroup>
                <AvGroup>
                  <Label id="motherNameLabel" for="motherName">
                    Mother Name
                  </Label>
                  <AvField
                    id="teacher-motherName"
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
                    id="teacher-motherMiddleName"
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
                    id="teacher-motherLastName"
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
                    id="teacher-aadharNo"
                    type="string"
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
                    id="teacher-dateOfBirth"
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
                    id="teacher-placeOfBirth"
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
                    id="teacher-religion"
                    type="select"
                    className="form-control"
                    name="religion"
                    value={(!isNew && teacherEntity.religion) || 'HINDU'}
                  >
                    <option value="HINDU">HINDU</option>
                    <option value="MUSLIM">MUSLIM</option>
                    <option value="CHRISTIAN">CHRISTIAN</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="casteLabel">Caste</Label>
                  <AvInput
                    id="teacher-caste"
                    type="select"
                    className="form-control"
                    name="caste"
                    value={(!isNew && teacherEntity.caste) || 'OC'}
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
                    id="teacher-subCaste"
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
                    id="teacher-age"
                    type="string"
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
                    id="teacher-sex"
                    type="select"
                    className="form-control"
                    name="sex"
                    value={(!isNew && teacherEntity.sex) || 'MALE'}
                  >
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="bloodGroupLabel">Blood Group</Label>
                  <AvInput
                    id="teacher-bloodGroup"
                    type="select"
                    className="form-control"
                    name="bloodGroup"
                    value={(!isNew && teacherEntity.bloodGroup) || 'ABPOSITIVE'}
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
                    id="teacher-addressLineOne"
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
                    id="teacher-addressLineTwo"
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
                    id="teacher-addressLineThree"
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
                    id="teacher-town"
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
                    id="teacher-state"
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
                    id="teacher-country"
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
                    id="teacher-pincode"
                    type="string"
                    className="form-control"
                    name="pincode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="teacherContactNumberLabel" for="teacherContactNumber">
                    Teacher Contact Number
                  </Label>
                  <AvField
                    id="teacher-teacherContactNumber"
                    type="text"
                    name="teacherContactNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateContactNumberLabel" for="alternateContactNumber">
                    Alternate Contact Number
                  </Label>
                  <AvField id="teacher-alternateContactNumber" type="text" name="alternateContactNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="teacherEmailAddressLabel" for="teacherEmailAddress">
                    Teacher Email Address
                  </Label>
                  <AvField
                    id="teacher-teacherEmailAddress"
                    type="text"
                    name="teacherEmailAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alternateEmailAddressLabel" for="alternateEmailAddress">
                    Alternate Email Address
                  </Label>
                  <AvField id="teacher-alternateEmailAddress" type="text" name="alternateEmailAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="relationWithStaffLabel">Relation With Staff</Label>
                  <AvInput
                    id="teacher-relationWithStaff"
                    type="select"
                    className="form-control"
                    name="relationWithStaff"
                    value={(!isNew && teacherEntity.relationWithStaff) || 'FATHER'}
                  >
                    <option value="FATHER">FATHER</option>
                    <option value="MOTHER">MOTHER</option>
                    <option value="GUARDIAN">GUARDIAN</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactNameLabel" for="emergencyContactName">
                    Emergency Contact Name
                  </Label>
                  <AvField
                    id="teacher-emergencyContactName"
                    type="text"
                    name="emergencyContactName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactMiddleNameLabel" for="emergencyContactMiddleName">
                    Emergency Contact Middle Name
                  </Label>
                  <AvField id="teacher-emergencyContactMiddleName" type="text" name="emergencyContactMiddleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactLastNameLabel" for="emergencyContactLastName">
                    Emergency Contact Last Name
                  </Label>
                  <AvField
                    id="teacher-emergencyContactLastName"
                    type="text"
                    name="emergencyContactLastName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactNoLabel" for="emergencyContactNo">
                    Emergency Contact No
                  </Label>
                  <AvField
                    id="teacher-emergencyContactNo"
                    type="text"
                    name="emergencyContactNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactEmailAddressLabel" for="emergencyContactEmailAddress">
                    Emergency Contact Email Address
                  </Label>
                  <AvField
                    id="teacher-emergencyContactEmailAddress"
                    type="text"
                    name="emergencyContactEmailAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="uploadPhotoLabel" for="uploadPhoto">
                    Upload Photo
                  </Label>
                  <AvField
                    id="teacher-uploadPhoto"
                    type="text"
                    name="uploadPhoto"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">Status</Label>
                  <AvInput
                    id="teacher-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && teacherEntity.status) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="employeeIdLabel" for="employeeId">
                    Employee Id
                  </Label>
                  <AvField id="teacher-employeeId" type="string" className="form-control" name="employeeId" />
                </AvGroup>
                <AvGroup>
                  <Label id="designationLabel" for="designation">
                    Designation
                  </Label>
                  <AvField
                    id="teacher-designation"
                    type="text"
                    name="designation"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="staffTypeLabel">Staff Type</Label>
                  <AvInput
                    id="teacher-staffType"
                    type="select"
                    className="form-control"
                    name="staffType"
                    value={(!isNew && teacherEntity.staffType) || 'TEACHING'}
                  >
                    <option value="TEACHING">TEACHING</option>
                    <option value="NONTEACHING">NONTEACHING</option>
                    <option value="GUEST">GUEST</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="teacher-department" type="select" className="form-control" name="departmentId">
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
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="teacher-branch" type="select" className="form-control" name="branchId">
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
                <Button tag={Link} id="cancel-save" to="/entity/teacher" replace color="info">
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
  branches: storeState.branch.entities,
  teacherEntity: storeState.teacher.entity,
  loading: storeState.teacher.loading,
  updating: storeState.teacher.updating,
  updateSuccess: storeState.teacher.updateSuccess
});

const mapDispatchToProps = {
  getDepartments,
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
)(TeacherUpdate);
