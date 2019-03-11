import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { getEntity, updateEntity, createEntity, reset } from './competitive-exam.reducer';
import { ICompetitiveExam } from 'app/shared/model/competitive-exam.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ICompetitiveExamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICompetitiveExamUpdateState {
  isNew: boolean;
  studentId: number;
}

export class CompetitiveExamUpdate extends React.Component<ICompetitiveExamUpdateProps, ICompetitiveExamUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: 0,
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { competitiveExamEntity } = this.props;
      const entity = {
        ...competitiveExamEntity,
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
    this.props.history.push('/entity/competitive-exam');
  };

  studentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        studentId: -1
      });
    } else {
      for (const i in this.props.students) {
        if (id === this.props.students[i].id.toString()) {
          this.setState({
            studentId: this.props.students[i].id
          });
        }
      }
    }
  };

  render() {
    const { competitiveExamEntity, students, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.competitiveExam.home.createOrEditLabel">Create or edit a CompetitiveExam</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : competitiveExamEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="competitive-exam-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="testNameLabel" for="testName">
                    Test Name
                  </Label>
                  <AvField
                    id="competitive-exam-testName"
                    type="text"
                    name="testName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="testScoreLabel" for="testScore">
                    Test Score
                  </Label>
                  <AvField
                    id="competitive-exam-testScore"
                    type="number"
                    className="form-control"
                    name="testScore"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="enrollmentNoLabel" for="enrollmentNo">
                    Enrollment No
                  </Label>
                  <AvField
                    id="competitive-exam-enrollmentNo"
                    type="number"
                    className="form-control"
                    name="enrollmentNo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="rankLabel" for="rank">
                    Rank
                  </Label>
                  <AvField
                    id="competitive-exam-rank"
                    type="number"
                    className="form-control"
                    name="rank"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput
                    id="competitive-exam-student"
                    type="select"
                    className="form-control"
                    name="studentId"
                    onChange={this.studentUpdate}
                  >
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
                <Button tag={Link} id="cancel-save" to="/entity/competitive-exam" replace color="info">
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
  students: storeState.student.entities,
  competitiveExamEntity: storeState.competitiveExam.entity,
  loading: storeState.competitiveExam.loading,
  updating: storeState.competitiveExam.updating
});

const mapDispatchToProps = {
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
)(CompetitiveExamUpdate);
