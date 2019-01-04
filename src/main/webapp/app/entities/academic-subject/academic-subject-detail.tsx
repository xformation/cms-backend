import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './academic-subject.reducer';
import { IAcademicSubject } from 'app/shared/model/academic-subject.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicSubjectDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AcademicSubjectDetail extends React.Component<IAcademicSubjectDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { academicSubjectEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.academicSubject.detail.title">AcademicSubject</Translate> [<b>{academicSubjectEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="subjectName">
                <Translate contentKey="cmsApp.academicSubject.subjectName">Subject Name</Translate>
              </span>
            </dt>
            <dd>{academicSubjectEntity.subjectName}</dd>
            <dt>
              <span id="electiveSub">
                <Translate contentKey="cmsApp.academicSubject.electiveSub">Elective Sub</Translate>
              </span>
            </dt>
            <dd>{academicSubjectEntity.electiveSub ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="cmsApp.academicSubject.academicDepartment">Academic Department</Translate>
            </dt>
            <dd>{academicSubjectEntity.academicDepartmentId ? academicSubjectEntity.academicDepartmentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/academic-subject" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/academic-subject/${academicSubjectEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ academicSubject }: IRootState) => ({
  academicSubjectEntity: academicSubject.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicSubjectDetail);
