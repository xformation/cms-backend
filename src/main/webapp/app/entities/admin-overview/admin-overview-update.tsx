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
import { getEntity, updateEntity, createEntity, reset } from './admin-overview.reducer';
import { IAdminOverview } from 'app/shared/model/admin-overview.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IAdminOverviewUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAdminOverviewUpdateState {
  isNew: boolean;
  departmentId: number;
  batchId: number;
}

export class AdminOverviewUpdate extends React.Component<IAdminOverviewUpdateProps, IAdminOverviewUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: 0,
      batchId: 0,
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { adminOverviewEntity } = this.props;
      const entity = {
        ...adminOverviewEntity,
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
    this.props.history.push('/entity/admin-overview');
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

  render() {
    const { adminOverviewEntity, departments, batches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.adminOverview.home.createOrEditLabel">Create or edit a AdminOverview</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : adminOverviewEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="admin-overview-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="chooseDateLabel" for="chooseDate">
                    Choose Date
                  </Label>
                  <AvField
                    id="admin-overview-chooseDate"
                    type="date"
                    className="form-control"
                    name="chooseDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sectionLabel">Section</Label>
                  <AvInput
                    id="admin-overview-section"
                    type="select"
                    className="form-control"
                    name="section"
                    value={(!isNew && adminOverviewEntity.section) || 'A'}
                  >
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureOneLabel">Lecture One</Label>
                  <AvInput
                    id="admin-overview-lectureOne"
                    type="select"
                    className="form-control"
                    name="lectureOne"
                    value={(!isNew && adminOverviewEntity.lectureOne) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureTwoLabel">Lecture Two</Label>
                  <AvInput
                    id="admin-overview-lectureTwo"
                    type="select"
                    className="form-control"
                    name="lectureTwo"
                    value={(!isNew && adminOverviewEntity.lectureTwo) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureThreeLabel">Lecture Three</Label>
                  <AvInput
                    id="admin-overview-lectureThree"
                    type="select"
                    className="form-control"
                    name="lectureThree"
                    value={(!isNew && adminOverviewEntity.lectureThree) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureFourLabel">Lecture Four</Label>
                  <AvInput
                    id="admin-overview-lectureFour"
                    type="select"
                    className="form-control"
                    name="lectureFour"
                    value={(!isNew && adminOverviewEntity.lectureFour) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureFiveLabel">Lecture Five</Label>
                  <AvInput
                    id="admin-overview-lectureFive"
                    type="select"
                    className="form-control"
                    name="lectureFive"
                    value={(!isNew && adminOverviewEntity.lectureFive) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureSixLabel">Lecture Six</Label>
                  <AvInput
                    id="admin-overview-lectureSix"
                    type="select"
                    className="form-control"
                    name="lectureSix"
                    value={(!isNew && adminOverviewEntity.lectureSix) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureSevenLabel">Lecture Seven</Label>
                  <AvInput
                    id="admin-overview-lectureSeven"
                    type="select"
                    className="form-control"
                    name="lectureSeven"
                    value={(!isNew && adminOverviewEntity.lectureSeven) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lectureEightLabel">Lecture Eight</Label>
                  <AvInput
                    id="admin-overview-lectureEight"
                    type="select"
                    className="form-control"
                    name="lectureEight"
                    value={(!isNew && adminOverviewEntity.lectureEight) || 'MARKED'}
                  >
                    <option value="MARKED">MARKED</option>
                    <option value="UNMARKED">UNMARKED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput
                    id="admin-overview-department"
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
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="admin-overview-batch" type="select" className="form-control" name="batchId" onChange={this.batchUpdate}>
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
                <Button tag={Link} id="cancel-save" to="/entity/admin-overview" replace color="info">
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
  adminOverviewEntity: storeState.adminOverview.entity,
  loading: storeState.adminOverview.loading,
  updating: storeState.adminOverview.updating
});

const mapDispatchToProps = {
  getDepartments,
  getBatches,
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
)(AdminOverviewUpdate);
