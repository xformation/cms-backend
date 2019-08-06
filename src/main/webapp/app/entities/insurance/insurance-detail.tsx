import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './insurance.reducer';
import { IInsurance } from 'app/shared/model/insurance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInsuranceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InsuranceDetail extends React.Component<IInsuranceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { insuranceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Insurance [<b>{insuranceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="insuranceCompany">Insurance Company</span>
            </dt>
            <dd>{insuranceEntity.insuranceCompany}</dd>
            <dt>
              <span id="typeOfInsurance">Type Of Insurance</span>
            </dt>
            <dd>{insuranceEntity.typeOfInsurance}</dd>
            <dt>
              <span id="dateOfInsurance">Date Of Insurance</span>
            </dt>
            <dd>
              <TextFormat value={insuranceEntity.dateOfInsurance} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="validTill">Valid Till</span>
            </dt>
            <dd>
              <TextFormat value={insuranceEntity.validTill} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/insurance" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/insurance/${insuranceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ insurance }: IRootState) => ({
  insuranceEntity: insurance.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InsuranceDetail);
