import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { getEntity, updateEntity, createEntity, reset } from './facility.reducer';
import { IFacility } from 'app/shared/model/facility.model';
// tslint:disable-next-line:no-unused-variable

export interface IFacilityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFacilityUpdateState {
  isNew: boolean;
  academicYearId: string;
  branchId: string;
  studentId: string;
}

export class FacilityUpdate extends React.Component<IFacilityUpdateProps, IFacilityUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicYearId: '0',
      branchId: '0',
      studentId: '0',
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

    this.props.getAcademicYears();
    this.props.getBranches();
    this.props.getStudents();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { facilityEntity } = this.props;
      const entity = {
        ...facilityEntity,
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
    this.props.history.push('/entity/facility');
  };

  render() {
    const { facilityEntity, academicYears, branches, students, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.facility.home.createOrEditLabel">Create or edit a Facility</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : facilityEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="facility-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="transportLabel">Transport</Label>
                  <AvInput
                    id="facility-transport"
                    type="select"
                    className="form-control"
                    name="transport"
                    value={(!isNew && facilityEntity.transport) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="messLabel">Mess</Label>
                  <AvInput
                    id="facility-mess"
                    type="select"
                    className="form-control"
                    name="mess"
                    value={(!isNew && facilityEntity.mess) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="gymLabel">Gym</Label>
                  <AvInput
                    id="facility-gym"
                    type="select"
                    className="form-control"
                    name="gym"
                    value={(!isNew && facilityEntity.gym) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="culturalClassLabel">Cultural Class</Label>
                  <AvInput
                    id="facility-culturalClass"
                    type="select"
                    className="form-control"
                    name="culturalClass"
                    value={(!isNew && facilityEntity.culturalClass) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="libraryLabel">Library</Label>
                  <AvInput
                    id="facility-library"
                    type="select"
                    className="form-control"
                    name="library"
                    value={(!isNew && facilityEntity.library) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="sportsLabel">Sports</Label>
                  <AvInput
                    id="facility-sports"
                    type="select"
                    className="form-control"
                    name="sports"
                    value={(!isNew && facilityEntity.sports) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="swimmingLabel">Swimming</Label>
                  <AvInput
                    id="facility-swimming"
                    type="select"
                    className="form-control"
                    name="swimming"
                    value={(!isNew && facilityEntity.swimming) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="extraClassLabel">Extra Class</Label>
                  <AvInput
                    id="facility-extraClass"
                    type="select"
                    className="form-control"
                    name="extraClass"
                    value={(!isNew && facilityEntity.extraClass) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="handicraftsLabel">Handicrafts</Label>
                  <AvInput
                    id="facility-handicrafts"
                    type="select"
                    className="form-control"
                    name="handicrafts"
                    value={(!isNew && facilityEntity.handicrafts) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicYear.id">Academic Year</Label>
                  <AvInput id="facility-academicYear" type="select" className="form-control" name="academicYearId">
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
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="facility-branch" type="select" className="form-control" name="branchId">
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
                  <Label for="student.id">Student</Label>
                  <AvInput id="facility-student" type="select" className="form-control" name="studentId">
                    <option value="" key="0" />
                    {students
                      ? students.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/facility" replace color="info">
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
  academicYears: storeState.academicYear.entities,
  branches: storeState.branch.entities,
  students: storeState.student.entities,
  facilityEntity: storeState.facility.entity,
  loading: storeState.facility.loading,
  updating: storeState.facility.updating,
  updateSuccess: storeState.facility.updateSuccess
});

const mapDispatchToProps = {
  getAcademicYears,
  getBranches,
  getStudents,
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
)(FacilityUpdate);
