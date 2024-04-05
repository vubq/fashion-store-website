package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.config.security.service.UserDetailsImpl;
import com.vubq.fashionstorewebsite.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class BaseEntity implements Serializable {

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", updatable = false)
    private EStatus status;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createdBy = userDetails != null ? userDetails.getId() : null;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedBy = userDetails != null ? userDetails.getId() : null;
    }
}
