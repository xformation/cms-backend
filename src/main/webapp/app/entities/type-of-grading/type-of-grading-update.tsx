import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicExamSetting } from 'app/shared/model/academic-exam-setting.model';
import { getEntities as getAcademicExamSettings } from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
import { getEntity, updateEntity, createEntity, reset } from './type-of-grading.reducer';
import { ITypeOfGrading } from 'app/shared/model/type-of-grading.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITypeOfGradingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITypeOfGradingUpdateState {
  isNew: boolean;
  academicExamSettingId: string;
}

export class TypeOfGradingUpdate extends React.Component<ITypeOfGradingUpdateProps, ITypeOfGradingUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicExamSettingId: '0',
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

    this.props.getAcademicExamSettings();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { typeOfGradingEntity } = this.props;
      const entity = {
        ...typeOfGradingEntity,
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
    this.props.history.push('/entity/type-of-grading');
  };

  render() {
    const { typeOfGradingEntity, academicExamSettings, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.typeOfGrading.home.createOrEditLabel">Create or edit a TypeOfGrading</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : typeOfGradingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="type-of-grading-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="minMarksLabel" for="minMarks">
                    Min Marks
                  </Label>
                  <AvField
                    id="type-of-grading-minMarks"
                    type="string"
                    className="form-control"
                    name="minMarks"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="maxMarksLabel" for="maxMarks">
                    Max Marks
                  </Label>
                  <AvField
                    id="type-of-grading-maxMarks"
                    type="string"
                    className="form-control"
                    name="maxMarks"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="gradesLabel">Grades</Label>
                  <AvInput
                    id="type-of-grading-grades"
                    type="select"
                    className="form-control"
                    name="grades"
                    value={(!isNew && typeOfGradingEntity.grades) || 'Aplus'}
                  >
                    <option value="Aplus">Aplus</option>
                    <option value="A">A</option>
                    <option value="Bplus">Bplus</option>
                    <option value="B">B</option>
                    <option value="Cplus">Cplus</option>
                    <option value="C">C</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicExamSetting.id">Academic Exam Setting</Label>
                  <AvInput id="type-of-grading-academicExamSetting" type="select" className="form-control" name="academicExamSettingId">
                    <option value="" key="0" />
                    {academicExamSettings
                      ? academicExamSettings.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/type-of-grading" replace color="info">
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
  academicExamSettings: storeState.academicExamSetting.entities,
  typeOfGradingEntity: storeState.typeOfGrading.entity,
  loading: storeState.typeOfGrading.loading,
  updating: storeState.typeOfGrading.updating,
  updateSuccess: storeState.typeOfGrading.updateSuccess
});

const mapDispatchToProps = {
  getAcademicExamSettings,
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
)(TypeOfGradingUpdate);
