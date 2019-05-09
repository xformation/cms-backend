import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './meta-lecture.reducer';
import { IMetaLecture } from 'app/shared/model/meta-lecture.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMetaLectureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class MetaLectureDetail extends React.Component<IMetaLectureDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { metaLectureEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            MetaLecture [<b>{metaLectureEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="weekDay">Week Day</span>
            </dt>
            <dd>{metaLectureEntity.weekDay}</dd>
            <dt>
              <span id="startTime">Start Time</span>
            </dt>
            <dd>{metaLectureEntity.startTime}</dd>
            <dt>
              <span id="endTime">End Time</span>
            </dt>
            <dd>{metaLectureEntity.endTime}</dd>
            <dt>Branch</dt>
            <dd>{metaLectureEntity.branchId ? metaLectureEntity.branchId : ''}</dd>
            <dt>Department</dt>
            <dd>{metaLectureEntity.departmentId ? metaLectureEntity.departmentId : ''}</dd>
            <dt>Subject</dt>
            <dd>{metaLectureEntity.subjectId ? metaLectureEntity.subjectId : ''}</dd>
            <dt>Teacher</dt>
            <dd>{metaLectureEntity.teacherId ? metaLectureEntity.teacherId : ''}</dd>
            <dt>Term</dt>
            <dd>{metaLectureEntity.termId ? metaLectureEntity.termId : ''}</dd>
            <dt>Academicyear</dt>
            <dd>{metaLectureEntity.academicyearId ? metaLectureEntity.academicyearId : ''}</dd>
            <dt>Section</dt>
            <dd>{metaLectureEntity.sectionId ? metaLectureEntity.sectionId : ''}</dd>
            <dt>Batch</dt>
            <dd>{metaLectureEntity.batchId ? metaLectureEntity.batchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/meta-lecture" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/meta-lecture/${metaLectureEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ metaLecture }: IRootState) => ({
  metaLectureEntity: metaLecture.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MetaLectureDetail);
