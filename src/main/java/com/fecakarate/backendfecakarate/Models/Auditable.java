package com.fecakarate.backendfecakarate.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public  class Auditable <U> {
    @PrePersist
    protected void onCreate(){
        this.createdDate = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastModifiedDate = new Date(System.currentTimeMillis());
    }

    @CreatedBy
    @Column(name = "created_by")
    @JsonIgnore
    private U createdBy;

    @CreatedDate
    @Column(name = "created_at")
    @JsonIgnore
    private Date createdDate = new Date();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    @JsonIgnore
    private Date lastModifiedDate = new Date();
}

