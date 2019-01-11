import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './section.reducer';
import { ISection } from 'app/shared/model/section.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISectionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ISectionUpdateState {
  isNew: boolean;
  batchId: number;
}

export class SectionUpdate extends React.Component<ISectionUpdateProps, ISectionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      batchId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getBatches();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { sectionEntity } = this.props;
      const entity = {
        ...sectionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/section');
  };

  render() {
    const { sectionEntity, batches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.section.home.createOrEditLabel">
              <Translate contentKey="cmsApp.section.home.createOrEditLabel">Create or edit a Section</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : sectionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="section-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sectionLabel">
                    <Translate contentKey="cmsApp.section.section">Section</Translate>
                  </Label>
                  <AvInput
                    id="section-section"
                    type="select"
                    className="form-control"
                    name="section"
                    value={(!isNew && sectionEntity.section) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="cmsApp.SectionEnum.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="cmsApp.SectionEnum.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="cmsApp.SectionEnum.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="cmsApp.SectionEnum.D" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">
                    <Translate contentKey="cmsApp.section.batch">Batch</Translate>
                  </Label>
                  <AvInput id="section-batch" type="select" className="form-control" name="batchId">
                    <option value="" key="0" />
                    {batches
                      ? batches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/section" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
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
  batches: storeState.batch.entities,
  sectionEntity: storeState.section.entity,
  loading: storeState.section.loading,
  updating: storeState.section.updating
});

const mapDispatchToProps = {
  getBatches,
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
)(SectionUpdate);
