import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './due-date.reducer';
import { IDueDate } from 'app/shared/model/due-date.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDueDateDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DueDateDetail extends React.Component<IDueDateDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { dueDateEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            DueDate [<b>{dueDateEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="paymentMethod">Payment Method</span>
            </dt>
            <dd>{dueDateEntity.paymentMethod}</dd>
            <dt>
              <span id="installments">Installments</span>
            </dt>
            <dd>{dueDateEntity.installments}</dd>
            <dt>
              <span id="dayDesc">Day Desc</span>
            </dt>
            <dd>{dueDateEntity.dayDesc}</dd>
            <dt>
              <span id="paymentDate">Payment Date</span>
            </dt>
            <dd>
              <TextFormat value={dueDateEntity.paymentDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="frequency">Frequency</span>
            </dt>
            <dd>{dueDateEntity.frequency}</dd>
            <dt>College</dt>
            <dd>{dueDateEntity.collegeId ? dueDateEntity.collegeId : ''}</dd>
            <dt>Branch</dt>
            <dd>{dueDateEntity.branchId ? dueDateEntity.branchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/due-date" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/due-date/${dueDateEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ dueDate }: IRootState) => ({
  dueDateEntity: dueDate.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DueDateDetail);
