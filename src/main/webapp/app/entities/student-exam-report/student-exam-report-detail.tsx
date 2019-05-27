import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-exam-report.reducer';
import { IStudentExamReport } from 'app/shared/model/student-exam-report.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentExamReportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class StudentExamReportDetail extends React.Component<IStudentExamReportDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentExamReportEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            StudentExamReport [<b>{studentExamReportEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="marksObtained">Marks Obtained</span>
            </dt>
            <dd>{studentExamReportEntity.marksObtained}</dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{studentExamReportEntity.comments}</dd>
            <dt>
              <span id="createdOn">Created On</span>
            </dt>
            <dd>
              <TextFormat value={studentExamReportEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{studentExamReportEntity.createdBy}</dd>
            <dt>
              <span id="updatedOn">Updated On</span>
            </dt>
            <dd>
              <TextFormat value={studentExamReportEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updatedBy">Updated By</span>
            </dt>
            <dd>{studentExamReportEntity.updatedBy}</dd>
            <dt>Academic Exam Setting</dt>
            <dd>{studentExamReportEntity.academicExamSettingId ? studentExamReportEntity.academicExamSettingId : ''}</dd>
            <dt>Student</dt>
            <dd>{studentExamReportEntity.studentId ? studentExamReportEntity.studentId : ''}</dd>
            <dt>Type Of Grading</dt>
            <dd>{studentExamReportEntity.typeOfGradingId ? studentExamReportEntity.typeOfGradingId : ''}</dd>
            <dt>Batch</dt>
            <dd>{studentExamReportEntity.batchId ? studentExamReportEntity.batchId : ''}</dd>
            <dt>Academicyear</dt>
            <dd>{studentExamReportEntity.academicyearId ? studentExamReportEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-exam-report" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-exam-report/${studentExamReportEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentExamReport }: IRootState) => ({
  studentExamReportEntity: studentExamReport.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentExamReportDetail);
