import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFeeCategory } from 'app/shared/model/fee-category.model';
import { getEntities as getFeeCategories } from 'app/entities/fee-category/fee-category.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { IFacility } from 'app/shared/model/facility.model';
import { getEntities as getFacilities } from 'app/entities/facility/facility.reducer';
import { ITransportRoute } from 'app/shared/model/transport-route.model';
import { getEntities as getTransportRoutes } from 'app/entities/transport-route/transport-route.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './fee-details.reducer';
import { IFeeDetails } from 'app/shared/model/fee-details.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IFeeDetailsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFeeDetailsUpdateState {
  isNew: boolean;
  feeCategoryId: number;
  batchId: number;
  facilityId: number;
  transportRouteId: number;
  collegeId: number;
  departmentId: number;
  branchId: number;
  academicYearId: number;
}

export class FeeDetailsUpdate extends React.Component<IFeeDetailsUpdateProps, IFeeDetailsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      feeCategoryId: 0,
      batchId: 0,
      facilityId: 0,
      transportRouteId: 0,
      collegeId: 0,
      departmentId: 0,
      branchId: 0,
      academicYearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getFeeCategories();
    this.props.getBatches();
    this.props.getFacilities();
    this.props.getTransportRoutes();
    this.props.getColleges();
    this.props.getDepartments();
    this.props.getBranches();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { feeDetailsEntity } = this.props;
      const entity = {
        ...feeDetailsEntity,
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
    this.props.history.push('/entity/fee-details');
  };

  feeCategoryUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        feeCategoryId: -1
      });
    } else {
      for (const i in this.props.feeCategories) {
        if (id === this.props.feeCategories[i].id.toString()) {
          this.setState({
            feeCategoryId: this.props.feeCategories[i].id
          });
        }
      }
    }
  };

  batchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        batchId: -1
      });
    } else {
      for (const i in this.props.batches) {
        if (id === this.props.batches[i].id.toString()) {
          this.setState({
            batchId: this.props.batches[i].id
          });
        }
      }
    }
  };

  facilityUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        facilityId: -1
      });
    } else {
      for (const i in this.props.facilities) {
        if (id === this.props.facilities[i].id.toString()) {
          this.setState({
            facilityId: this.props.facilities[i].id
          });
        }
      }
    }
  };

  transportRouteUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        transportRouteId: -1
      });
    } else {
      for (const i in this.props.transportRoutes) {
        if (id === this.props.transportRoutes[i].id.toString()) {
          this.setState({
            transportRouteId: this.props.transportRoutes[i].id
          });
        }
      }
    }
  };

  collegeUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        collegeId: -1
      });
    } else {
      for (const i in this.props.colleges) {
        if (id === this.props.colleges[i].id.toString()) {
          this.setState({
            collegeId: this.props.colleges[i].id
          });
        }
      }
    }
  };

  departmentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        departmentId: -1
      });
    } else {
      for (const i in this.props.departments) {
        if (id === this.props.departments[i].id.toString()) {
          this.setState({
            departmentId: this.props.departments[i].id
          });
        }
      }
    }
  };

  branchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        branchId: -1
      });
    } else {
      for (const i in this.props.branches) {
        if (id === this.props.branches[i].id.toString()) {
          this.setState({
            branchId: this.props.branches[i].id
          });
        }
      }
    }
  };

  academicYearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        academicYearId: -1
      });
    } else {
      for (const i in this.props.academicYears) {
        if (id === this.props.academicYears[i].id.toString()) {
          this.setState({
            academicYearId: this.props.academicYears[i].id
          });
        }
      }
    }
  };

  render() {
    const {
      feeDetailsEntity,
      feeCategories,
      batches,
      facilities,
      transportRoutes,
      colleges,
      departments,
      branches,
      academicYears,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.feeDetails.home.createOrEditLabel">Create or edit a FeeDetails</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : feeDetailsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="fee-details-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="feeParticularsNameLabel" for="feeParticularsName">
                    Fee Particulars Name
                  </Label>
                  <AvField
                    id="fee-details-feeParticularsName"
                    type="text"
                    name="feeParticularsName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="feeParticularDescLabel" for="feeParticularDesc">
                    Fee Particular Desc
                  </Label>
                  <AvField
                    id="fee-details-feeParticularDesc"
                    type="text"
                    name="feeParticularDesc"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="studentTypeLabel">Student Type</Label>
                  <AvInput
                    id="fee-details-studentType"
                    type="select"
                    className="form-control"
                    name="studentType"
                    value={(!isNew && feeDetailsEntity.studentType) || 'REGULAR'}
                  >
                    <option value="REGULAR">REGULAR</option>
                    <option value="STAFF_CONCESSION">STAFF_CONCESSION</option>
                    <option value="BENEFITS">BENEFITS</option>
                    <option value="SCHOLARSHIP">SCHOLARSHIP</option>
                    <option value="OTHER_BENEFITS">OTHER_BENEFITS</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel">Gender</Label>
                  <AvInput
                    id="fee-details-gender"
                    type="select"
                    className="form-control"
                    name="gender"
                    value={(!isNew && feeDetailsEntity.gender) || 'MALE'}
                  >
                    <option value="MALE">MALE</option>
                    <option value="FEMALE">FEMALE</option>
                    <option value="OTHER">OTHER</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="amountLabel" for="amount">
                    Amount
                  </Label>
                  <AvField
                    id="fee-details-amount"
                    type="number"
                    className="form-control"
                    name="amount"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="feeCategory.id">Fee Category</Label>
                  <AvInput
                    id="fee-details-feeCategory"
                    type="select"
                    className="form-control"
                    name="feeCategoryId"
                    onChange={this.feeCategoryUpdate}
                  >
                    <option value="" key="0" />
                    {feeCategories
                      ? feeCategories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="fee-details-batch" type="select" className="form-control" name="batchId" onChange={this.batchUpdate}>
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
                  <Label for="facility.id">Facility</Label>
                  <AvInput
                    id="fee-details-facility"
                    type="select"
                    className="form-control"
                    name="facilityId"
                    onChange={this.facilityUpdate}
                  >
                    <option value="" key="0" />
                    {facilities
                      ? facilities.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="transportRoute.id">Transport Route</Label>
                  <AvInput
                    id="fee-details-transportRoute"
                    type="select"
                    className="form-control"
                    name="transportRouteId"
                    onChange={this.transportRouteUpdate}
                  >
                    <option value="" key="0" />
                    {transportRoutes
                      ? transportRoutes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="fee-details-college" type="select" className="form-control" name="collegeId" onChange={this.collegeUpdate}>
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
                  <Label for="department.id">Department</Label>
                  <AvInput
                    id="fee-details-department"
                    type="select"
                    className="form-control"
                    name="departmentId"
                    onChange={this.departmentUpdate}
                  >
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
                  <AvInput id="fee-details-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
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
                  <Label for="academicYear.id">Academic Year</Label>
                  <AvInput
                    id="fee-details-academicYear"
                    type="select"
                    className="form-control"
                    name="academicYearId"
                    onChange={this.academicYearUpdate}
                  >
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
                <Button tag={Link} id="cancel-save" to="/entity/fee-details" replace color="info">
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
  feeCategories: storeState.feeCategory.entities,
  batches: storeState.batch.entities,
  facilities: storeState.facility.entities,
  transportRoutes: storeState.transportRoute.entities,
  colleges: storeState.college.entities,
  departments: storeState.department.entities,
  branches: storeState.branch.entities,
  academicYears: storeState.academicYear.entities,
  feeDetailsEntity: storeState.feeDetails.entity,
  loading: storeState.feeDetails.loading,
  updating: storeState.feeDetails.updating
});

const mapDispatchToProps = {
  getFeeCategories,
  getBatches,
  getFacilities,
  getTransportRoutes,
  getColleges,
  getDepartments,
  getBranches,
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
)(FeeDetailsUpdate);
