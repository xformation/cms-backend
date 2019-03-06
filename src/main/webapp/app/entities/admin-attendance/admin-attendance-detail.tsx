import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admin-attendance.reducer';
import { IAdminAttendance } from 'app/shared/model/admin-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdminAttendanceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdminAttendanceDetail extends React.Component<IAdminAttendanceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { adminAttendanceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdminAttendance [<b>{adminAttendanceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="attendanceStatus">Attendance Status</span>
            </dt>
            <dd>{adminAttendanceEntity.attendanceStatus}</dd>
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{adminAttendanceEntity.comments}</dd>
            <dt>Student</dt>
            <dd>{adminAttendanceEntity.studentId ? adminAttendanceEntity.studentId : ''}</dd>
            <dt>Lecture</dt>
            <dd>{adminAttendanceEntity.lectureId ? adminAttendanceEntity.lectureId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admin-attendance" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/admin-attendance/${adminAttendanceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ adminAttendance }: IRootState) => ({
  adminAttendanceEntity: adminAttendance.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdminAttendanceDetail);
