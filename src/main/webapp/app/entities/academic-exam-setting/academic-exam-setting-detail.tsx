import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './academic-exam-setting.reducer';
import { IAcademicExamSetting } from 'app/shared/model/academic-exam-setting.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicExamSettingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AcademicExamSettingDetail extends React.Component<IAcademicExamSettingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { academicExamSettingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AcademicExamSetting [<b>{academicExamSettingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="examType">Exam Type</span>
            </dt>
            <dd>{academicExamSettingEntity.examType}</dd>
            <dt>
              <span id="subject">Subject</span>
            </dt>
            <dd>{academicExamSettingEntity.subject}</dd>
            <dt>
              <span id="date">Date</span>
            </dt>
            <dd>
              <TextFormat value={academicExamSettingEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="day">Day</span>
            </dt>
            <dd>{academicExamSettingEntity.day}</dd>
            <dt>
              <span id="duration">Duration</span>
            </dt>
            <dd>{academicExamSettingEntity.duration}</dd>
            <dt>
              <span id="startTime">Start Time</span>
            </dt>
            <dd>{academicExamSettingEntity.startTime}</dd>
            <dt>
              <span id="endTime">End Time</span>
            </dt>
            <dd>{academicExamSettingEntity.endTime}</dd>
            <dt>
              <span id="total">Total</span>
            </dt>
            <dd>{academicExamSettingEntity.total}</dd>
            <dt>
              <span id="passing">Passing</span>
            </dt>
            <dd>{academicExamSettingEntity.passing}</dd>
            <dt>
              <span id="actions">Actions</span>
            </dt>
            <dd>{academicExamSettingEntity.actions}</dd>
            <dt>Department</dt>
            <dd>{academicExamSettingEntity.departmentId ? academicExamSettingEntity.departmentId : ''}</dd>
            <dt>Academic Year</dt>
            <dd>{academicExamSettingEntity.academicYearId ? academicExamSettingEntity.academicYearId : ''}</dd>
            <dt>Attendance Master</dt>
            <dd>{academicExamSettingEntity.attendanceMasterId ? academicExamSettingEntity.attendanceMasterId : ''}</dd>
            <dt>Section</dt>
            <dd>{academicExamSettingEntity.sectionId ? academicExamSettingEntity.sectionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/academic-exam-setting" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/academic-exam-setting/${academicExamSettingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ academicExamSetting }: IRootState) => ({
  academicExamSettingEntity: academicExamSetting.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicExamSettingDetail);
