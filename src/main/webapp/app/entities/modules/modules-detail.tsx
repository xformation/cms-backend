import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './modules.reducer';
import { IModules } from 'app/shared/model/modules.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IModulesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ModulesDetail extends React.Component<IModulesDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { modulesEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Modules [<b>{modulesEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moduleName">Module Name</span>
            </dt>
            <dd>{modulesEntity.moduleName}</dd>
            <dt>
              <span id="subModuleName">Sub Module Name</span>
            </dt>
            <dd>{modulesEntity.subModuleName}</dd>
            <dt>
              <span id="url">Url</span>
            </dt>
            <dd>{modulesEntity.url}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{modulesEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/modules" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/modules/${modulesEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ modules }: IRootState) => ({
  modulesEntity: modules.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ModulesDetail);
