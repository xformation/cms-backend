import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './periods.reducer';
import { IPeriods } from 'app/shared/model/periods.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPeriodsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PeriodsDetail extends React.Component<IPeriodsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { periodsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.periods.detail.title">Periods</Translate> [<b>{periodsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="periods">
                <Translate contentKey="cmsApp.periods.periods">Periods</Translate>
              </span>
            </dt>
            <dd>{periodsEntity.periods}</dd>
            <dt>
              <Translate contentKey="cmsApp.periods.section">Section</Translate>
            </dt>
            <dd>{periodsEntity.sectionId ? periodsEntity.sectionId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/periods" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/periods/${periodsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ periods }: IRootState) => ({
  periodsEntity: periods.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PeriodsDetail);
