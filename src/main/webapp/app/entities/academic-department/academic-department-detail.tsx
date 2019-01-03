import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './academic-department.reducer';
import { IAcademicDepartment } from 'app/shared/model/academic-department.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicDepartmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AcademicDepartmentDetail extends React.Component<IAcademicDepartmentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { academicDepartmentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.academicDepartment.detail.title">AcademicDepartment</Translate> [<b>
              {academicDepartmentEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="departmentName">
                <Translate contentKey="cmsApp.academicDepartment.departmentName">Department Name</Translate>
              </span>
            </dt>
            <dd>{academicDepartmentEntity.departmentName}</dd>
            <dt>
              <span id="university">
                <Translate contentKey="cmsApp.academicDepartment.university">University</Translate>
              </span>
            </dt>
            <dd>{academicDepartmentEntity.university}</dd>
          </dl>
          <Button tag={Link} to="/entity/academic-department" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/academic-department/${academicDepartmentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ academicDepartment }: IRootState) => ({
  academicDepartmentEntity: academicDepartment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicDepartmentDetail);
