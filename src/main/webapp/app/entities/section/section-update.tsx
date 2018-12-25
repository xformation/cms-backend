import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudentYear } from 'app/shared/model/student-year.model';
import { getEntities as getStudentYears } from 'app/entities/student-year/student-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './section.reducer';
import { ISection } from 'app/shared/model/section.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ISectionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export interface ISectionUpdateState {
  isNew: boolean;
  studentyearid: any;
}

export class SectionUpdate extends React.Component<ISectionUpdateProps, ISectionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentyearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getStudentYears();
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

  studentyearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        studentyearId: -1
      });
    } else {
      for (const i in this.props.studentYears) {
        if (id === this.props.studentYears[i].id.toString()) {
          this.setState({
            studentyearId: this.props.studentYears[i].id
          });
        }
      }
    }
  };

  render() {
    const { sectionEntity, studentYears, loading, updating } = this.props;
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
                      <Translate contentKey="cmsApp.ClassSection.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="cmsApp.ClassSection.B" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="studentyear.id">
                    <Translate contentKey="cmsApp.section.studentyear">Studentyear</Translate>
                  </Label>
                  <AvInput
                    id="section-studentyear"
                    type="select"
                    className="form-control"
                    name="studentyearId"
                    onChange={this.studentyearUpdate}
                  >
                    <option value="" key="0" />
                    {studentYears
                      ? studentYears.map(otherEntity => (
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
  studentYears: storeState.studentYear.entities,
  sectionEntity: storeState.section.entity,
  loading: storeState.section.loading,
  updating: storeState.section.updating
});

const mapDispatchToProps = {
  getStudentYears,
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
