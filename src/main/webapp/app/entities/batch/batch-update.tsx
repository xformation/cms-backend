import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { getEntity, updateEntity, createEntity, reset } from './batch.reducer';
import { IBatch } from 'app/shared/model/batch.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
// import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBatchUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBatchUpdateState {
  isNew: boolean;
  departmentId: string;
}

export class BatchUpdate extends React.Component<IBatchUpdateProps, IBatchUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: '0',
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

    this.props.getDepartments();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { batchEntity } = this.props;
      const entity = {
        ...batchEntity,
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
    this.props.history.push('/entity/batch');
  };

  render() {
    const { batchEntity, departments, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.batch.home.createOrEditLabel">Create or edit a Batch</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : batchEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="batch-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="batchLabel">Batch</Label>
                  <AvInput
                    id="batch-batch"
                    type="select"
                    className="form-control"
                    name="batch"
                    value={(!isNew && batchEntity.batch) || 'FIRSTYEAR'}
                  >
                    <option value="FIRSTYEAR">FIRSTYEAR</option>
                    <option value="SECONDYEAR">SECONDYEAR</option>
                    <option value="THIRDYEAR">THIRDYEAR</option>
                    <option value="FOURTHYEAR">FOURTHYEAR</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="batch-department" type="select" className="form-control" name="departmentId">
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/batch" replace color="info">
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
  departments: storeState.department.entities,
  batchEntity: storeState.batch.entity,
  loading: storeState.batch.loading,
  updating: storeState.batch.updating,
  updateSuccess: storeState.batch.updateSuccess
});

const mapDispatchToProps = {
  getDepartments,
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
)(BatchUpdate);
