import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentAttendanceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class StudentAttendanceDetail extends React.Component<IStudentAttendanceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentAttendanceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.studentAttendance.detail.title">StudentAttendance</Translate> [<b>{studentAttendanceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="attendanceStatus">
                <Translate contentKey="cmsApp.studentAttendance.attendanceStatus">Attendance Status</Translate>
              </span>
            </dt>
            <dd>{studentAttendanceEntity.attendanceStatus}</dd>
            <dt>
              <span id="comments">
                <Translate contentKey="cmsApp.studentAttendance.comments">Comments</Translate>
              </span>
            </dt>
            <dd>{studentAttendanceEntity.comments}</dd>
            <dt>
              <span id="attendanceDate">
                <Translate contentKey="cmsApp.studentAttendance.attendanceDate">Attendance Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={studentAttendanceEntity.attendanceDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="cmsApp.studentAttendance.student">Student</Translate>
            </dt>
            <dd>{studentAttendanceEntity.studentId ? studentAttendanceEntity.studentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.studentAttendance.lecture">Lecture</Translate>
            </dt>
            <dd>{studentAttendanceEntity.lectureId ? studentAttendanceEntity.lectureId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-attendance" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-attendance/${studentAttendanceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentAttendance }: IRootState) => ({
  studentAttendanceEntity: studentAttendance.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentAttendanceDetail);
