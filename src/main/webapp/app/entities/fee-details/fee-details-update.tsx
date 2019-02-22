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
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFeeDetailsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFeeDetailsUpdateState {
  isNew: boolean;
  feeCategoryId: string;
  batchId: string;
  facilityId: string;
  transportRouteId: string;
  collegeId: string;
  departmentId: string;
  branchId: string;
  academicYearId: string;
}

export class FeeDetailsUpdate extends React.Component<IFeeDetailsUpdateProps, IFeeDetailsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      feeCategoryId: '0',
      batchId: '0',
      facilityId: '0',
      transportRouteId: '0',
      collegeId: '0',
      departmentId: '0',
      branchId: '0',
      academicYearId: '0',
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
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/fee-details');
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
                    type="string"
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
                  <AvInput id="fee-details-feeCategory" type="select" className="form-control" name="feeCategoryId">
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
                  <AvInput id="fee-details-batch" type="select" className="form-control" name="batchId">
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
                  <AvInput id="fee-details-facility" type="select" className="form-control" name="facilityId">
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
                  <AvInput id="fee-details-transportRoute" type="select" className="form-control" name="transportRouteId">
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
                  <AvInput id="fee-details-college" type="select" className="form-control" name="collegeId">
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
                  <AvInput id="fee-details-department" type="select" className="form-control" name="departmentId">
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
                  <AvInput id="fee-details-branch" type="select" className="form-control" name="branchId">
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
                  <AvInput id="fee-details-academicYear" type="select" className="form-control" name="academicYearId">
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
  updating: storeState.feeDetails.updating,
  updateSuccess: storeState.feeDetails.updateSuccess
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
