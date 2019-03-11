package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "fee_remainder", nullable = false)
    private Status feeRemainder;

    @NotNull
    @Column(name = "notice_day", nullable = false)
    private Integer noticeDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "over_due_remainder", nullable = false)
    private Status overDueRemainder;

    @NotNull
    @Column(name = "remainder_recipients", nullable = false)
    private String remainderRecipients;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DueDate dueDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getFeeRemainder() {
        return feeRemainder;
    }

    public PaymentRemainder feeRemainder(Status feeRemainder) {
        this.feeRemainder = feeRemainder;
        return this;
    }

    public void setFeeRemainder(Status feeRemainder) {
        this.feeRemainder = feeRemainder;
    }

    public Integer getNoticeDay() {
        return noticeDay;
    }

    public PaymentRemainder noticeDay(Integer noticeDay) {
        this.noticeDay = noticeDay;
        return this;
    }

    public void setNoticeDay(Integer noticeDay) {
        this.noticeDay = noticeDay;
    }

    public Status getOverDueRemainder() {
        return overDueRemainder;
    }

    public PaymentRemainder overDueRemainder(Status overDueRemainder) {
        this.overDueRemainder = overDueRemainder;
        return this;
    }

    public void setOverDueRemainder(Status overDueRemainder) {
        this.overDueRemainder = overDueRemainder;
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

    public DueDate getDueDate() {
        return dueDate;
    }

    public PaymentRemainder dueDate(DueDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
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
            ", feeRemainder='" + getFeeRemainder() + "'" +
            ", noticeDay=" + getNoticeDay() +
            ", overDueRemainder='" + getOverDueRemainder() + "'" +
            ", remainderRecipients='" + getRemainderRecipients() + "'" +
            "}";
    }
}
