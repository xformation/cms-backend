package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PaymentRemainder.
 */
@Entity
@Table(name = "payment_remainder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "paymentremainder")
public class PaymentRemainder implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "is_auto_remainder", nullable = false)
    private String isAutoRemainder;

    @Column(name = "is_first_payment_remainder")
    private String isFirstPaymentRemainder;

    @Column(name = "first_payment_remainder_days")
    private Integer firstPaymentRemainderDays;

    @Column(name = "is_second_payment_remainder")
    private String isSecondPaymentRemainder;

    @Column(name = "second_payment_remainder_days")
    private Integer secondPaymentRemainderDays;

    @Column(name = "is_over_due_payment_remainder")
    private String isOverDuePaymentRemainder;

    @Column(name = "over_due_payment_remainder_after_due_date_or_until_paid")
    private String overDuePaymentRemainderAfterDueDateOrUntilPaid;

    @Column(name = "over_due_payment_remainder_days")
    private Integer overDuePaymentRemainderDays;

    @Column(name = "is_remainder_recipients")
    private String isRemainderRecipients;

    @Column(name = "remainder_recipients")
    private String remainderRecipients;

    @ManyToOne
    @JsonIgnoreProperties("paymentRemainders")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("paymentRemainders")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsAutoRemainder() {
        return isAutoRemainder;
    }

    public PaymentRemainder isAutoRemainder(String isAutoRemainder) {
        this.isAutoRemainder = isAutoRemainder;
        return this;
    }

    public void setIsAutoRemainder(String isAutoRemainder) {
        this.isAutoRemainder = isAutoRemainder;
    }

    public String getIsFirstPaymentRemainder() {
        return isFirstPaymentRemainder;
    }

    public PaymentRemainder isFirstPaymentRemainder(String isFirstPaymentRemainder) {
        this.isFirstPaymentRemainder = isFirstPaymentRemainder;
        return this;
    }

    public void setIsFirstPaymentRemainder(String isFirstPaymentRemainder) {
        this.isFirstPaymentRemainder = isFirstPaymentRemainder;
    }

    public Integer getFirstPaymentRemainderDays() {
        return firstPaymentRemainderDays;
    }

    public PaymentRemainder firstPaymentRemainderDays(Integer firstPaymentRemainderDays) {
        this.firstPaymentRemainderDays = firstPaymentRemainderDays;
        return this;
    }

    public void setFirstPaymentRemainderDays(Integer firstPaymentRemainderDays) {
        this.firstPaymentRemainderDays = firstPaymentRemainderDays;
    }

    public String getIsSecondPaymentRemainder() {
        return isSecondPaymentRemainder;
    }

    public PaymentRemainder isSecondPaymentRemainder(String isSecondPaymentRemainder) {
        this.isSecondPaymentRemainder = isSecondPaymentRemainder;
        return this;
    }

    public void setIsSecondPaymentRemainder(String isSecondPaymentRemainder) {
        this.isSecondPaymentRemainder = isSecondPaymentRemainder;
    }

    public Integer getSecondPaymentRemainderDays() {
        return secondPaymentRemainderDays;
    }

    public PaymentRemainder secondPaymentRemainderDays(Integer secondPaymentRemainderDays) {
        this.secondPaymentRemainderDays = secondPaymentRemainderDays;
        return this;
    }

    public void setSecondPaymentRemainderDays(Integer secondPaymentRemainderDays) {
        this.secondPaymentRemainderDays = secondPaymentRemainderDays;
    }

    public String getIsOverDuePaymentRemainder() {
        return isOverDuePaymentRemainder;
    }

    public PaymentRemainder isOverDuePaymentRemainder(String isOverDuePaymentRemainder) {
        this.isOverDuePaymentRemainder = isOverDuePaymentRemainder;
        return this;
    }

    public void setIsOverDuePaymentRemainder(String isOverDuePaymentRemainder) {
        this.isOverDuePaymentRemainder = isOverDuePaymentRemainder;
    }

    public String getOverDuePaymentRemainderAfterDueDateOrUntilPaid() {
        return overDuePaymentRemainderAfterDueDateOrUntilPaid;
    }

    public PaymentRemainder overDuePaymentRemainderAfterDueDateOrUntilPaid(String overDuePaymentRemainderAfterDueDateOrUntilPaid) {
        this.overDuePaymentRemainderAfterDueDateOrUntilPaid = overDuePaymentRemainderAfterDueDateOrUntilPaid;
        return this;
    }

    public void setOverDuePaymentRemainderAfterDueDateOrUntilPaid(String overDuePaymentRemainderAfterDueDateOrUntilPaid) {
        this.overDuePaymentRemainderAfterDueDateOrUntilPaid = overDuePaymentRemainderAfterDueDateOrUntilPaid;
    }

    public Integer getOverDuePaymentRemainderDays() {
        return overDuePaymentRemainderDays;
    }

    public PaymentRemainder overDuePaymentRemainderDays(Integer overDuePaymentRemainderDays) {
        this.overDuePaymentRemainderDays = overDuePaymentRemainderDays;
        return this;
    }

    public void setOverDuePaymentRemainderDays(Integer overDuePaymentRemainderDays) {
        this.overDuePaymentRemainderDays = overDuePaymentRemainderDays;
    }

    public String getIsRemainderRecipients() {
        return isRemainderRecipients;
    }

    public PaymentRemainder isRemainderRecipients(String isRemainderRecipients) {
        this.isRemainderRecipients = isRemainderRecipients;
        return this;
    }

    public void setIsRemainderRecipients(String isRemainderRecipients) {
        this.isRemainderRecipients = isRemainderRecipients;
    }

    public String getRemainderRecipients() {
        return remainderRecipients;
    }

    public PaymentRemainder remainderRecipients(String remainderRecipients) {
        this.remainderRecipients = remainderRecipients;
        return this;
    }

    public void setRemainderRecipients(String remainderRecipients) {
        this.remainderRecipients = remainderRecipients;
    }

    public College getCollege() {
        return college;
    }

    public PaymentRemainder college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Branch getBranch() {
        return branch;
    }

    public PaymentRemainder branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentRemainder paymentRemainder = (PaymentRemainder) o;
        if (paymentRemainder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentRemainder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentRemainder{" +
            "id=" + getId() +
            ", isAutoRemainder='" + getIsAutoRemainder() + "'" +
            ", isFirstPaymentRemainder='" + getIsFirstPaymentRemainder() + "'" +
            ", firstPaymentRemainderDays=" + getFirstPaymentRemainderDays() +
            ", isSecondPaymentRemainder='" + getIsSecondPaymentRemainder() + "'" +
            ", secondPaymentRemainderDays=" + getSecondPaymentRemainderDays() +
            ", isOverDuePaymentRemainder='" + getIsOverDuePaymentRemainder() + "'" +
            ", overDuePaymentRemainderAfterDueDateOrUntilPaid='" + getOverDuePaymentRemainderAfterDueDateOrUntilPaid() + "'" +
            ", overDuePaymentRemainderDays=" + getOverDuePaymentRemainderDays() +
            ", isRemainderRecipients='" + getIsRemainderRecipients() + "'" +
            ", remainderRecipients='" + getRemainderRecipients() + "'" +
            "}";
    }
}
