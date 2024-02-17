package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @Column(name = "created_by", updatable = false)
    private Date createdBy;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        status = EStatus.ACTIVE;
//        createdBy = SecurityContextHolder.getContext().getAuthentication()
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
