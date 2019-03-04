import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './batch.reducer';
import { IBatch } from 'app/shared/model/batch.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBatchDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class BatchDetail extends React.Component<IBatchDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { batchEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Batch [<b>{batchEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="batch">Batch</span>
            </dt>
            <dd>{batchEntity.batch}</dd>
            <dt>Department</dt>
            <dd>{batchEntity.departmentId ? batchEntity.departmentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/batch" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/batch/${batchEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ batch }: IRootState) => ({
  batchEntity: batch.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BatchDetail);
