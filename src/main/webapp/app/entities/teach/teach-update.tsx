import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { getEntity, updateEntity, createEntity, reset } from './teach.reducer';
import { ITeach } from 'app/shared/model/teach.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ITeachUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITeachUpdateState {
  isNew: boolean;
  subjectId: number;
  teacherId: number;
}

export class TeachUpdate extends React.Component<ITeachUpdateProps, ITeachUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      subjectId: 0,
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

    this.props.getSubjects();
    this.props.getTeachers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { teachEntity } = this.props;
      const entity = {
        ...teachEntity,
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
    this.props.history.push('/entity/teach');
  };

  subjectUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        subjectId: -1
      });
    } else {
      for (const i in this.props.subjects) {
        if (id === this.props.subjects[i].id.toString()) {
          this.setState({
            subjectId: this.props.subjects[i].id
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
    const { teachEntity, subjects, teachers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.teach.home.createOrEditLabel">Create or edit a Teach</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : teachEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="teach-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="descLabel" for="desc">
                    Desc
                  </Label>
                  <AvField id="teach-desc" type="text" name="desc" />
                </AvGroup>
                <AvGroup>
                  <Label for="subject.id">Subject</Label>
                  <AvInput id="teach-subject" type="select" className="form-control" name="subjectId" onChange={this.subjectUpdate}>
                    <option value="" key="0" />
                    {subjects
                      ? subjects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="teacher.id">Teacher</Label>
                  <AvInput id="teach-teacher" type="select" className="form-control" name="teacherId" onChange={this.teacherUpdate}>
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
                <Button tag={Link} id="cancel-save" to="/entity/teach" replace color="info">
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
  subjects: storeState.subject.entities,
  teachers: storeState.teacher.entities,
  teachEntity: storeState.teach.entity,
  loading: storeState.teach.loading,
  updating: storeState.teach.updating
});

const mapDispatchToProps = {
  getSubjects,
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
)(TeachUpdate);
