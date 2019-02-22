import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fee-category.reducer';
import { IFeeCategory } from 'app/shared/model/fee-category.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFeeCategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FeeCategoryDetail extends React.Component<IFeeCategoryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { feeCategoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FeeCategory [<b>{feeCategoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="categoryName">Category Name</span>
            </dt>
            <dd>{feeCategoryEntity.categoryName}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{feeCategoryEntity.description}</dd>
          </dl>
          <Button tag={Link} to="/entity/fee-category" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/fee-category/${feeCategoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ feeCategory }: IRootState) => ({
  feeCategoryEntity: feeCategory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FeeCategoryDetail);
