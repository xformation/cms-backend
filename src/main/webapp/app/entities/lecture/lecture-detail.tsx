import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './lecture.reducer';
import { ILecture } from 'app/shared/model/lecture.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILectureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LectureDetail extends React.Component<ILectureDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { lectureEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Lecture [<b>{lectureEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="lecDate">Lec Date</span>
            </dt>
            <dd>
              <TextFormat value={lectureEntity.lecDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="lastUpdatedOn">Last Updated On</span>
            </dt>
            <dd>
              <TextFormat value={lectureEntity.lastUpdatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="lastUpdatedBy">Last Updated By</span>
            </dt>
            <dd>{lectureEntity.lastUpdatedBy}</dd>
            <dt>
              <span id="startTime">Start Time</span>
            </dt>
            <dd>{lectureEntity.startTime}</dd>
            <dt>
              <span id="endTime">End Time</span>
            </dt>
            <dd>{lectureEntity.endTime}</dd>
            <dt>Attendancemaster</dt>
            <dd>{lectureEntity.attendancemasterId ? lectureEntity.attendancemasterId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/lecture" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/lecture/${lectureEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ lecture }: IRootState) => ({
  lectureEntity: lecture.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LectureDetail);
