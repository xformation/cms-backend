import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { getEntity, updateEntity, createEntity, reset } from './periods.reducer';
import { IPeriods } from 'app/shared/model/periods.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IPeriodsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export interface IPeriodsUpdateState {
  isNew: boolean;
  sectionid: any;
  teacherid: any;
}

export class PeriodsUpdate extends React.Component<IPeriodsUpdateProps, IPeriodsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      sectionId: 0,
      teacherId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getSections();
    this.props.getTeachers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { periodsEntity } = this.props;
      const entity = {
        ...periodsEntity,
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
    this.props.history.push('/entity/periods');
  };

  sectionUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        sectionId: -1
      });
    } else {
      for (const i in this.props.sections) {
        if (id === this.props.sections[i].id.toString()) {
          this.setState({
            sectionId: this.props.sections[i].id
          });
        }
      }
    }
  };

  render() {
    const { periodsEntity, sections, teachers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.periods.home.createOrEditLabel">
              <Translate contentKey="cmsApp.periods.home.createOrEditLabel">Create or edit a Periods</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : periodsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="periods-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="periodsLabel">
                    <Translate contentKey="cmsApp.periods.periods">Periods</Translate>
                  </Label>
                  <AvInput
                    id="periods-periods"
                    type="select"
                    className="form-control"
                    name="periods"
                    value={(!isNew && periodsEntity.periods) || 'ONE'}
                  >
                    <option value="ONE">
                      <Translate contentKey="cmsApp.ClassPeriods.ONE" />
                    </option>
                    <option value="TWO">
                      <Translate contentKey="cmsApp.ClassPeriods.TWO" />
                    </option>
                    <option value="THREE">
                      <Translate contentKey="cmsApp.ClassPeriods.THREE" />
                    </option>
                    <option value="FOUR">
                      <Translate contentKey="cmsApp.ClassPeriods.FOUR" />
                    </option>
                    <option value="FIVE">
                      <Translate contentKey="cmsApp.ClassPeriods.FIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="section.id">
                    <Translate contentKey="cmsApp.periods.section">Section</Translate>
                  </Label>
                  <AvInput id="periods-section" type="select" className="form-control" name="sectionId" onChange={this.sectionUpdate}>
                    <option value="" key="0" />
                    {sections
                      ? sections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/periods" replace color="info">
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
  sections: storeState.section.entities,
  teachers: storeState.teacher.entities,
  periodsEntity: storeState.periods.entity,
  loading: storeState.periods.loading,
  updating: storeState.periods.updating
});

const mapDispatchToProps = {
  getSections,
  getTeachers,
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
)(PeriodsUpdate);
