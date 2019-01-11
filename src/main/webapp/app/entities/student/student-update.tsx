import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IStudentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentUpdateState {
  isNew: boolean;
  batchId: number;
  sectionId: number;
  branchId: number;
  departmentId: number;
}

export class StudentUpdate extends React.Component<IStudentUpdateProps, IStudentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      batchId: 0,
      sectionId: 0,
      branchId: 0,
      departmentId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getBatches();
    this.props.getSections();
    this.props.getBranches();
    this.props.getDepartments();
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

  sectionUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        sectionId: -1
      });
    } else {
      for (const i in this.props.sections) {
        if (id === this.props.sections[i].id.toString()) {
          this.setState({
            sectionId: this.props.sections[i].id
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

  render() {
    const { studentEntity, batches, sections, branches, departments, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.student.home.createOrEditLabel">
              <Translate contentKey="cmsApp.student.home.createOrEditLabel">Create or edit a Student</Translate>
            </h2>
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
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="student-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentNameLabel" for="studentName">
                    <Translate contentKey="cmsApp.student.studentName">Student Name</Translate>
                  </Label>
                  <AvField
                    id="student-studentName"
                    type="text"
                    name="studentName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">
                    <Translate contentKey="cmsApp.student.batch">Batch</Translate>
                  </Label>
                  <AvInput id="student-batch" type="select" className="form-control" name="batchId" onChange={this.batchUpdate}>
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
                  <Label for="section.id">
                    <Translate contentKey="cmsApp.student.section">Section</Translate>
                  </Label>
                  <AvInput id="student-section" type="select" className="form-control" name="sectionId" onChange={this.sectionUpdate}>
                    <option value="" key="0" />
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
                  <Label for="branch.id">
                    <Translate contentKey="cmsApp.student.branch">Branch</Translate>
                  </Label>
                  <AvInput id="student-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
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
                  <Label for="department.id">
                    <Translate contentKey="cmsApp.student.department">Department</Translate>
                  </Label>
                  <AvInput
                    id="student-department"
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
                <Button tag={Link} id="cancel-save" to="/entity/student" replace color="info">
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
  batches: storeState.batch.entities,
  sections: storeState.section.entities,
  branches: storeState.branch.entities,
  departments: storeState.department.entities,
  studentEntity: storeState.student.entity,
  loading: storeState.student.loading,
  updating: storeState.student.updating
});

const mapDispatchToProps = {
  getBatches,
  getSections,
  getBranches,
  getDepartments,
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
