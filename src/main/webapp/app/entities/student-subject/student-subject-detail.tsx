import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student-subject.reducer';
import { IStudentSubject } from 'app/shared/model/student-subject.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentSubjectDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class StudentSubjectDetail extends React.Component<IStudentSubjectDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentSubjectEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            StudentSubject [<b>{studentSubjectEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="comments">Comments</span>
            </dt>
            <dd>{studentSubjectEntity.comments}</dd>
            <dt>
              <span id="lastupdatedDate">Lastupdated Date</span>
            </dt>
            <dd>
              <TextFormat value={studentSubjectEntity.lastupdatedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Student</dt>
            <dd>{studentSubjectEntity.studentId ? studentSubjectEntity.studentId : ''}</dd>
            <dt>Subject</dt>
            <dd>{studentSubjectEntity.subjectId ? studentSubjectEntity.subjectId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student-subject" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student-subject/${studentSubjectEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ studentSubject }: IRootState) => ({
  studentSubjectEntity: studentSubject.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentSubjectDetail);
