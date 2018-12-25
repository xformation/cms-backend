import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './departments.reducer';
import { IDepartments } from 'app/shared/model/departments.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepartmentsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export class DepartmentsDetail extends React.Component<IDepartmentsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { departmentsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.departments.detail.title">Departments</Translate> [<b>{departmentsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="cmsApp.departments.name">Name</Translate>
              </span>
            </dt>
            <dd>{departmentsEntity.name}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmsApp.departments.description">Description</Translate>
              </span>
            </dt>
            <dd>{departmentsEntity.description}</dd>
            <dt>
              <span id="deptHead">
                <Translate contentKey="cmsApp.departments.deptHead">Dept Head</Translate>
              </span>
            </dt>
            <dd>{departmentsEntity.deptHead}</dd>
          </dl>
          <Button tag={Link} to="/entity/departments" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/departments/${departmentsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ departments }: IRootState) => ({
  departmentsEntity: departments.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DepartmentsDetail);
