import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubjectDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class SubjectDetail extends React.Component<ISubjectDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { subjectEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.subject.detail.title">Subject</Translate> [<b>{subjectEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="commonSub">
                <Translate contentKey="cmsApp.subject.commonSub">Common Sub</Translate>
              </span>
            </dt>
            <dd>{subjectEntity.commonSub}</dd>
            <dt>
              <span id="electiveSub">
                <Translate contentKey="cmsApp.subject.electiveSub">Elective Sub</Translate>
              </span>
            </dt>
            <dd>{subjectEntity.electiveSub}</dd>
            <dt>
              <Translate contentKey="cmsApp.subject.department">Department</Translate>
            </dt>
            <dd>{subjectEntity.departmentId ? subjectEntity.departmentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.subject.student">Student</Translate>
            </dt>
            <dd>{subjectEntity.studentId ? subjectEntity.studentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.subject.teacher">Teacher</Translate>
            </dt>
            <dd>{subjectEntity.teacherId ? subjectEntity.teacherId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/subject" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/subject/${subjectEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ subject }: IRootState) => ({
  subjectEntity: subject.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SubjectDetail);
