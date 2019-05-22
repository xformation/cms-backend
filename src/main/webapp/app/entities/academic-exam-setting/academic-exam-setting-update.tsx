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
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { getEntity, updateEntity, createEntity, reset } from './academic-exam-setting.reducer';
import { IAcademicExamSetting } from 'app/shared/model/academic-exam-setting.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAcademicExamSettingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAcademicExamSettingUpdateState {
  isNew: boolean;
  departmentId: string;
  academicyearId: string;
  sectionId: string;
}

export class AcademicExamSettingUpdate extends React.Component<IAcademicExamSettingUpdateProps, IAcademicExamSettingUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: '0',
      academicyearId: '0',
      sectionId: '0',
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

    this.props.getDepartments();
    this.props.getAcademicYears();
    this.props.getSections();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { academicExamSettingEntity } = this.props;
      const entity = {
        ...academicExamSettingEntity,
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
    this.props.history.push('/entity/academic-exam-setting');
  };

  render() {
    const { academicExamSettingEntity, departments, academicYears, sections, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.academicExamSetting.home.createOrEditLabel">Create or edit a AcademicExamSetting</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : academicExamSettingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="academic-exam-setting-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="examTypeLabel" for="examType">
                    Exam Type
                  </Label>
                  <AvField
                    id="academic-exam-setting-examType"
                    type="text"
                    name="examType"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="semesterLabel">Semester</Label>
                  <AvInput
                    id="academic-exam-setting-semester"
                    type="select"
                    className="form-control"
                    name="semester"
                    value={(!isNew && academicExamSettingEntity.semester) || 'SEMESTER1'}
                  >
                    <option value="SEMESTER1">SEMESTER1</option>
                    <option value="SEMESTER2">SEMESTER2</option>
                    <option value="SEMESTER3">SEMESTER3</option>
                    <option value="SEMESTER4">SEMESTER4</option>
                    <option value="SEMESTER5">SEMESTER5</option>
                    <option value="SEMESTER6">SEMESTER6</option>
                    <option value="SEMESTER7">SEMESTER7</option>
                    <option value="SEMESTER8">SEMESTER8</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="subjectLabel" for="subject">
                    Subject
                  </Label>
                  <AvField id="academic-exam-setting-subject" type="text" name="subject" />
                </AvGroup>
                <AvGroup>
                  <Label id="examDateLabel" for="examDate">
                    Exam Date
                  </Label>
                  <AvField
                    id="academic-exam-setting-examDate"
                    type="date"
                    className="form-control"
                    name="examDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dayLabel" for="day">
                    Day
                  </Label>
                  <AvField
                    id="academic-exam-setting-day"
                    type="text"
                    name="day"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="durationLabel" for="duration">
                    Duration
                  </Label>
                  <AvField
                    id="academic-exam-setting-duration"
                    type="text"
                    name="duration"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="startTimeLabel" for="startTime">
                    Start Time
                  </Label>
                  <AvField
                    id="academic-exam-setting-startTime"
                    type="text"
                    name="startTime"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="endTimeLabel" for="endTime">
                    End Time
                  </Label>
                  <AvField
                    id="academic-exam-setting-endTime"
                    type="text"
                    name="endTime"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="totalLabel" for="total">
                    Total
                  </Label>
                  <AvField
                    id="academic-exam-setting-total"
                    type="string"
                    className="form-control"
                    name="total"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="passingLabel" for="passing">
                    Passing
                  </Label>
                  <AvField
                    id="academic-exam-setting-passing"
                    type="string"
                    className="form-control"
                    name="passing"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="actionsLabel" for="actions">
                    Actions
                  </Label>
                  <AvField id="academic-exam-setting-actions" type="text" name="actions" />
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="academic-exam-setting-department" type="select" className="form-control" name="departmentId">
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
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput id="academic-exam-setting-academicyear" type="select" className="form-control" name="academicyearId">
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
                  <Label for="section.id">Section</Label>
                  <AvInput id="academic-exam-setting-section" type="select" className="form-control" name="sectionId">
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
                <Button tag={Link} id="cancel-save" to="/entity/academic-exam-setting" replace color="info">
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
  academicYears: storeState.academicYear.entities,
  sections: storeState.section.entities,
  academicExamSettingEntity: storeState.academicExamSetting.entity,
  loading: storeState.academicExamSetting.loading,
  updating: storeState.academicExamSetting.updating,
  updateSuccess: storeState.academicExamSetting.updateSuccess
});

const mapDispatchToProps = {
  getDepartments,
  getAcademicYears,
  getSections,
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
)(AcademicExamSettingUpdate);
