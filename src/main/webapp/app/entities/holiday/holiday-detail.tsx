import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './holiday.reducer';
import { IHoliday } from 'app/shared/model/holiday.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHolidayDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HolidayDetail extends React.Component<IHolidayDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { holidayEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Holiday [<b>{holidayEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="holidayDesc">Holiday Desc</span>
            </dt>
            <dd>{holidayEntity.holidayDesc}</dd>
            <dt>
              <span id="holidayDate">Holiday Date</span>
            </dt>
            <dd>
              <TextFormat value={holidayEntity.holidayDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="holidayStatus">Holiday Status</span>
            </dt>
            <dd>{holidayEntity.holidayStatus}</dd>
            <dt>Academicyear</dt>
            <dd>{holidayEntity.academicyearId ? holidayEntity.academicyearId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/holiday" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/holiday/${holidayEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ holiday }: IRootState) => ({
  holidayEntity: holiday.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HolidayDetail);
