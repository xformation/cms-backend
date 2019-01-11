import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './department.reducer';
import { IDepartment } from 'app/shared/model/department.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepartmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class DepartmentDetail extends React.Component<IDepartmentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { departmentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.department.detail.title">Department</Translate> [<b>{departmentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="cmsApp.department.name">Name</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.name}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmsApp.department.description">Description</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.description}</dd>
            <dt>
              <span id="deptHead">
                <Translate contentKey="cmsApp.department.deptHead">Dept Head</Translate>
              </span>
            </dt>
            <dd>{departmentEntity.deptHead}</dd>
            <dt>
              <Translate contentKey="cmsApp.department.student">Student</Translate>
            </dt>
            <dd>{departmentEntity.studentId ? departmentEntity.studentId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.department.college">College</Translate>
            </dt>
            <dd>{departmentEntity.collegeId ? departmentEntity.collegeId : ''}</dd>
            <dt>
              <Translate contentKey="cmsApp.department.academicyear">Academicyear</Translate>
            </dt>
            <dd>{departmentEntity.academicyearId ? departmentEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/department" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/department/${departmentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ department }: IRootState) => ({
  departmentEntity: department.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DepartmentDetail);
