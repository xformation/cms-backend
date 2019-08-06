import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './modules.reducer';
import { IModules } from 'app/shared/model/modules.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IModulesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IModulesUpdateState {
  isNew: boolean;
}

export class ModulesUpdate extends React.Component<IModulesUpdateProps, IModulesUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { modulesEntity } = this.props;
      const entity = {
        ...modulesEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/modules');
  };

  render() {
    const { modulesEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.modules.home.createOrEditLabel">Create or edit a Modules</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : modulesEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="modules-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="moduleNameLabel" for="moduleName">
                    Module Name
                  </Label>
                  <AvField id="modules-moduleName" type="text" name="moduleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="subModuleNameLabel" for="subModuleName">
                    Sub Module Name
                  </Label>
                  <AvField id="modules-subModuleName" type="text" name="subModuleName" />
                </AvGroup>
                <AvGroup>
                  <Label id="urlLabel" for="url">
                    Url
                  </Label>
                  <AvField id="modules-url" type="text" name="url" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">Status</Label>
                  <AvInput
                    id="modules-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && modulesEntity.status) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                    <option value="DRAFT">DRAFT</option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/modules" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  modulesEntity: storeState.modules.entity,
  loading: storeState.modules.loading,
  updating: storeState.modules.updating,
  updateSuccess: storeState.modules.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ModulesUpdate);
