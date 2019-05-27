import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicExamSetting } from 'app/shared/model/academic-exam-setting.model';
import { getEntities as getAcademicExamSettings } from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ITypeOfGrading } from 'app/shared/model/type-of-grading.model';
import { getEntities as getTypeOfGradings } from 'app/entities/type-of-grading/type-of-grading.reducer';
import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-exam-report.reducer';
import { IStudentExamReport } from 'app/shared/model/student-exam-report.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentExamReportUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IStudentExamReportUpdateState {
  isNew: boolean;
  academicExamSettingId: string;
  studentId: string;
  typeOfGradingId: string;
  batchId: string;
  academicyearId: string;
}

export class StudentExamReportUpdate extends React.Component<IStudentExamReportUpdateProps, IStudentExamReportUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicExamSettingId: '0',
      studentId: '0',
      typeOfGradingId: '0',
      batchId: '0',
      academicyearId: '0',
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

    this.props.getAcademicExamSettings();
    this.props.getStudents();
    this.props.getTypeOfGradings();
    this.props.getBatches();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentExamReportEntity } = this.props;
      const entity = {
        ...studentExamReportEntity,
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
    this.props.history.push('/entity/student-exam-report');
  };

  render() {
    const {
      studentExamReportEntity,
      academicExamSettings,
      students,
      typeOfGradings,
      batches,
      academicYears,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentExamReport.home.createOrEditLabel">Create or edit a StudentExamReport</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentExamReportEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="student-exam-report-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="marksObtainedLabel" for="marksObtained">
                    Marks Obtained
                  </Label>
                  <AvField
                    id="student-exam-report-marksObtained"
                    type="string"
                    className="form-control"
                    name="marksObtained"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="student-exam-report-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createdOnLabel" for="createdOn">
                    Created On
                  </Label>
                  <AvField
                    id="student-exam-report-createdOn"
                    type="date"
                    className="form-control"
                    name="createdOn"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="createdByLabel" for="createdBy">
                    Created By
                  </Label>
                  <AvField
                    id="student-exam-report-createdBy"
                    type="text"
                    name="createdBy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="updatedOn">
                    Updated On
                  </Label>
                  <AvField
                    id="student-exam-report-updatedOn"
                    type="date"
                    className="form-control"
                    name="updatedOn"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField
                    id="student-exam-report-updatedBy"
                    type="text"
                    name="updatedBy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="academicExamSetting.id">Academic Exam Setting</Label>
                  <AvInput id="student-exam-report-academicExamSetting" type="select" className="form-control" name="academicExamSettingId">
                    <option value="" key="0" />
                    {academicExamSettings
                      ? academicExamSettings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="student-exam-report-student" type="select" className="form-control" name="studentId">
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
                  <Label for="typeOfGrading.id">Type Of Grading</Label>
                  <AvInput id="student-exam-report-typeOfGrading" type="select" className="form-control" name="typeOfGradingId">
                    <option value="" key="0" />
                    {typeOfGradings
                      ? typeOfGradings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="student-exam-report-batch" type="select" className="form-control" name="batchId">
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
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput id="student-exam-report-academicyear" type="select" className="form-control" name="academicyearId">
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
                <Button tag={Link} id="cancel-save" to="/entity/student-exam-report" replace color="info">
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
  academicExamSettings: storeState.academicExamSetting.entities,
  students: storeState.student.entities,
  typeOfGradings: storeState.typeOfGrading.entities,
  batches: storeState.batch.entities,
  academicYears: storeState.academicYear.entities,
  studentExamReportEntity: storeState.studentExamReport.entity,
  loading: storeState.studentExamReport.loading,
  updating: storeState.studentExamReport.updating,
  updateSuccess: storeState.studentExamReport.updateSuccess
});

const mapDispatchToProps = {
  getAcademicExamSettings,
  getStudents,
  getTypeOfGradings,
  getBatches,
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
)(StudentExamReportUpdate);
