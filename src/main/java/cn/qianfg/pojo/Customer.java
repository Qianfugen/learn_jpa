package cn.qianfg.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_source")
    private String custSource;


}
