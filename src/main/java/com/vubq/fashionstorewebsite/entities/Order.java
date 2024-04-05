package com.vubq.fashionstorewebsite.entities;

import com.vubq.fashionstorewebsite.enums.EOrderStatus;
import com.vubq.fashionstorewebsite.enums.EOrderType;
import com.vubq.fashionstorewebsite.enums.EPayType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@SuperBuilder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EOrderType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_type")
    private EPayType payType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private EOrderStatus orderStatus;

    @Column(name = "voucher_id")
    private String voucherId;

    @Column(name = "full_name_customer")
    private String fullNameCustomer;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "address")
    private String address;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "note_by_customer")
    private String noteByCustomer;

    @Column(name = "note_by_admin")
    private String noteByAdmin;

    @Column(name = "completed_at")
    private Date completedAt;
}

