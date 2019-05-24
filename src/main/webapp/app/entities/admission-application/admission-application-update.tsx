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
import { getEntity, updateEntity, createEntity, reset } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdmissionApplicationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdmissionApplicationUpdateState {
  isNew: boolean;
  studentId: string;
}

export class AdmissionApplicationUpdate extends React.Component<IAdmissionApplicationUpdateProps, IAdmissionApplicationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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

    this.props.getStudents();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { admissionApplicationEntity } = this.props;
      const entity = {
        ...admissionApplicationEntity,
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
    this.props.history.push('/entity/admission-application');
  };

  render() {
    const { admissionApplicationEntity, students, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.admissionApplication.home.createOrEditLabel">Create or edit a AdmissionApplication</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : admissionApplicationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="admission-application-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="admissionStatusLabel">Admission Status</Label>
                  <AvInput
                    id="admission-application-admissionStatus"
                    type="select"
                    className="form-control"
                    name="admissionStatus"
                    value={(!isNew && admissionApplicationEntity.admissionStatus) || 'RECEIVED'}
                  >
                    <option value="RECEIVED">RECEIVED</option>
                    <option value="INPROCESS">INPROCESS</option>
                    <option value="DECLINED">DECLINED</option>
                    <option value="ACCEPTED">ACCEPTED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="courseLabel">Course</Label>
                  <AvInput
                    id="admission-application-course"
                    type="select"
                    className="form-control"
                    name="course"
                    value={(!isNew && admissionApplicationEntity.course) || 'BTECH'}
                  >
                    <option value="BTECH">BTECH</option>
                    <option value="MTECH">MTECH</option>
                    <option value="BBA">BBA</option>
                    <option value="MBA">MBA</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="admissionDateLabel" for="admissionDate">
                    Admission Date
                  </Label>
                  <AvField
                    id="admission-application-admissionDate"
                    type="date"
                    className="form-control"
                    name="admissionDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="admission-application-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="admission-application-student" type="select" className="form-control" name="studentId">
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
                <Button tag={Link} id="cancel-save" to="/entity/admission-application" replace color="info">
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
  admissionApplicationEntity: storeState.admissionApplication.entity,
  loading: storeState.admissionApplication.loading,
  updating: storeState.admissionApplication.updating,
  updateSuccess: storeState.admissionApplication.updateSuccess
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
)(AdmissionApplicationUpdate);
