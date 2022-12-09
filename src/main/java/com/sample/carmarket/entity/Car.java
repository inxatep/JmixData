package com.sample.carmarket.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "CAR", indexes = {
        @Index(name = "IDX_CAR_MODEL", columnList = "MODEL_ID"),
        @Index(name = "IDX_CAR_UNQ", columnList = "REGISTRATION_NUMBER", unique = true)
})
@Entity
public class Car {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Size(min = 6, max = 6)
    @Column(name = "REGISTRATION_NUMBER", nullable = false, length = 6)
    @NotNull
    private String registration_number;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Model model;

    @NotNull
    @Max(2030)
    @Min(1990)
    @Column(name = "PRODUCTION_YEAR", nullable = false)
    private Integer production_year;

    @Column(name = "STATUS")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_SALE")
    private Date date_of_sale;

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public void setDate_of_sale(Date date_of_sale) {
        this.date_of_sale = date_of_sale;
    }

    public Date getDate_of_sale() {
        return date_of_sale;
    }

    public Integer getProduction_year() {
        return production_year;
    }

    public void setProduction_year(Integer production_year) {
        this.production_year = production_year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}