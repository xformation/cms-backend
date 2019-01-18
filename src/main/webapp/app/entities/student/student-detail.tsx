import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class StudentDetail extends React.Component<IStudentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.student.detail.title">Student</Translate> [<b>{studentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentName">
                <Translate contentKey="cmsApp.student.studentName">Student Name</Translate>
              </span>
            </dt>
            <dd>{studentEntity.studentName}</dd>
            <dt>
              <Translate contentKey="cmsApp.student.department">Department</Translate>
            </dt>
            <dd>{studentEntity.departmentId ? studentEntity.departmentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.student.batch">Batch</Translate>
            </dt>
            <dd>{studentEntity.batchId ? studentEntity.batchId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.student.section">Section</Translate>
            </dt>
            <dd>{studentEntity.sectionId ? studentEntity.sectionId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.student.branch">Branch</Translate>
            </dt>
            <dd>{studentEntity.branchId ? studentEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/student" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/student/${studentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ student }: IRootState) => ({
  studentEntity: student.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentDetail);
