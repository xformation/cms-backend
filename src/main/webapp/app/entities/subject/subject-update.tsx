import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { getEntity, updateEntity, createEntity, reset } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ISubjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ISubjectUpdateState {
  isNew: boolean;
  departmentId: number;
  teacherId: number;
}

export class SubjectUpdate extends React.Component<ISubjectUpdateProps, ISubjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: 0,
      teacherId: 0,
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
    this.props.getTeachers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { subjectEntity } = this.props;
      const entity = {
        ...subjectEntity,
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
    this.props.history.push('/entity/subject');
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

  teacherUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        teacherId: -1
      });
    } else {
      for (const i in this.props.teachers) {
        if (id === this.props.teachers[i].id.toString()) {
          this.setState({
            teacherId: this.props.teachers[i].id
          });
        }
      }
    }
  };

  render() {
    const { subjectEntity, departments, teachers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.subject.home.createOrEditLabel">
              <Translate contentKey="cmsApp.subject.home.createOrEditLabel">Create or edit a Subject</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : subjectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="subject-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="subjectCodeLabel" for="subjectCode">
                    <Translate contentKey="cmsApp.subject.subjectCode">Subject Code</Translate>
                  </Label>
                  <AvField
                    id="subject-subjectCode"
                    type="text"
                    name="subjectCode"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="subjectTypeLabel">
                    <Translate contentKey="cmsApp.subject.subjectType">Subject Type</Translate>
                  </Label>
                  <AvInput
                    id="subject-subjectType"
                    type="select"
                    className="form-control"
                    name="subjectType"
                    value={(!isNew && subjectEntity.subjectType) || 'COMMON'}
                  >
                    <option value="COMMON">
                      <Translate contentKey="cmsApp.SubTypeEnum.COMMON" />
                    </option>
                    <option value="ELECTIVE">
                      <Translate contentKey="cmsApp.SubTypeEnum.ELECTIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="subjectDescLabel" for="subjectDesc">
                    <Translate contentKey="cmsApp.subject.subjectDesc">Subject Desc</Translate>
                  </Label>
                  <AvField
                    id="subject-subjectDesc"
                    type="text"
                    name="subjectDesc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">
                    <Translate contentKey="cmsApp.subject.department">Department</Translate>
                  </Label>
                  <AvInput
                    id="subject-department"
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
                  <Label for="teacher.id">
                    <Translate contentKey="cmsApp.subject.teacher">Teacher</Translate>
                  </Label>
                  <AvInput id="subject-teacher" type="select" className="form-control" name="teacherId" onChange={this.teacherUpdate}>
                    <option value="" key="0" />
                    {teachers
                      ? teachers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/subject" replace color="info">
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
  departments: storeState.department.entities,
  teachers: storeState.teacher.entities,
  subjectEntity: storeState.subject.entity,
  loading: storeState.subject.loading,
  updating: storeState.subject.updating
});

const mapDispatchToProps = {
  getDepartments,
  getTeachers,
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
)(SubjectUpdate);
