import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './lecture.reducer';
import { ILecture } from 'app/shared/model/lecture.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILectureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

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
            <Translate contentKey="cmsApp.lecture.detail.title">Lecture</Translate> [<b>{lectureEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="lecDate">
                <Translate contentKey="cmsApp.lecture.lecDate">Lec Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={lectureEntity.lecDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="lastUpdatedOn">
                <Translate contentKey="cmsApp.lecture.lastUpdatedOn">Last Updated On</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={lectureEntity.lastUpdatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="lastUpdatedBy">
                <Translate contentKey="cmsApp.lecture.lastUpdatedBy">Last Updated By</Translate>
              </span>
            </dt>
            <dd>{lectureEntity.lastUpdatedBy}</dd>
            <dt>
              <span id="lecStatus">
                <Translate contentKey="cmsApp.lecture.lecStatus">Lec Status</Translate>
              </span>
            </dt>
            <dd>{lectureEntity.lecStatus}</dd>
            <dt>
              <span id="desc">
                <Translate contentKey="cmsApp.lecture.desc">Desc</Translate>
              </span>
            </dt>
            <dd>{lectureEntity.desc}</dd>
            <dt>
              <Translate contentKey="cmsApp.lecture.attendancemaster">Attendancemaster</Translate>
            </dt>
            <dd>{lectureEntity.attendancemasterId ? lectureEntity.attendancemasterId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/lecture" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/lecture/${lectureEntity.id}/edit`} replace color="primary">
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
