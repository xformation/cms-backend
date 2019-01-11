import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ILecture } from 'app/shared/model/lecture.model';
import { getEntities as getLectures } from 'app/entities/lecture/lecture.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentAttendanceUpdateState {
  isNew: boolean;
  studentId: number;
  lectureId: number;
}

export class StudentAttendanceUpdate extends React.Component<IStudentAttendanceUpdateProps, IStudentAttendanceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: 0,
      lectureId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getStudents();
    this.props.getLectures();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentAttendanceEntity } = this.props;
      const entity = {
        ...studentAttendanceEntity,
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
    this.props.history.push('/entity/student-attendance');
  };

  render() {
    const { studentAttendanceEntity, students, lectures, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentAttendance.home.createOrEditLabel">
              <Translate contentKey="cmsApp.studentAttendance.home.createOrEditLabel">Create or edit a StudentAttendance</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentAttendanceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="student-attendance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="attendanceStatusLabel">
                    <Translate contentKey="cmsApp.studentAttendance.attendanceStatus">Attendance Status</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-attendanceStatus"
                    type="select"
                    className="form-control"
                    name="attendanceStatus"
                    value={(!isNew && studentAttendanceEntity.attendanceStatus) || 'PRESENT'}
                  >
                    <option value="PRESENT">
                      <Translate contentKey="cmsApp.AttendanceStatusEnum.PRESENT" />
                    </option>
                    <option value="ABSENT">
                      <Translate contentKey="cmsApp.AttendanceStatusEnum.ABSENT" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    <Translate contentKey="cmsApp.studentAttendance.comments">Comments</Translate>
                  </Label>
                  <AvField
                    id="student-attendance-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">
                    <Translate contentKey="cmsApp.studentAttendance.student">Student</Translate>
                  </Label>
                  <AvInput id="student-attendance-student" type="select" className="form-control" name="studentId">
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
                <AvGroup>
                  <Label for="lecture.id">
                    <Translate contentKey="cmsApp.studentAttendance.lecture">Lecture</Translate>
                  </Label>
                  <AvInput id="student-attendance-lecture" type="select" className="form-control" name="lectureId">
                    <option value="" key="0" />
                    {lectures
                      ? lectures.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student-attendance" replace color="info">
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
  students: storeState.student.entities,
  lectures: storeState.lecture.entities,
  studentAttendanceEntity: storeState.studentAttendance.entity,
  loading: storeState.studentAttendance.loading,
  updating: storeState.studentAttendance.updating
});

const mapDispatchToProps = {
  getStudents,
  getLectures,
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
)(StudentAttendanceUpdate);
