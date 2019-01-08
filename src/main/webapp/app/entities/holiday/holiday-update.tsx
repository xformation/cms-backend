import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './holiday.reducer';
import { IHoliday } from 'app/shared/model/holiday.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHolidayUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHolidayUpdateState {
  isNew: boolean;
}

export class HolidayUpdate extends React.Component<IHolidayUpdateProps, IHolidayUpdateState> {
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
      const { holidayEntity } = this.props;
      const entity = {
        ...holidayEntity,
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
    this.props.history.push('/entity/holiday');
  };

  render() {
    const { holidayEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.holiday.home.createOrEditLabel">
              <Translate contentKey="cmsApp.holiday.home.createOrEditLabel">Create or edit a Holiday</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : holidayEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="holiday-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="srNoLabel" for="srNo">
                    <Translate contentKey="cmsApp.holiday.srNo">Sr No</Translate>
                  </Label>
                  <AvField
                    id="holiday-srNo"
                    type="string"
                    className="form-control"
                    name="srNo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sHolidayLabel" for="sHoliday">
                    <Translate contentKey="cmsApp.holiday.sHoliday">S Holiday</Translate>
                  </Label>
                  <AvField
                    id="holiday-sHoliday"
                    type="text"
                    name="sHoliday"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="aDateLabel" for="aDate">
                    <Translate contentKey="cmsApp.holiday.aDate">A Date</Translate>
                  </Label>
                  <AvField
                    id="holiday-aDate"
                    type="date"
                    className="form-control"
                    name="aDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="cmsApp.holiday.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="holiday-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && holidayEntity.status) || 'PRESENT'}
                  >
                    <option value="PRESENT">
                      <Translate contentKey="cmsApp.Status.PRESENT" />
                    </option>
                    <option value="ABSENT">
                      <Translate contentKey="cmsApp.Status.ABSENT" />
                    </option>
                    <option value="ACTIVE">
                      <Translate contentKey="cmsApp.Status.ACTIVE" />
                    </option>
                    <option value="DEACTIVE">
                      <Translate contentKey="cmsApp.Status.DEACTIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/holiday" replace color="info">
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
  holidayEntity: storeState.holiday.entity,
  loading: storeState.holiday.loading,
  updating: storeState.holiday.updating,
  updateSuccess: storeState.holiday.updateSuccess
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
)(HolidayUpdate);
