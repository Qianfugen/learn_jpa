package cn.qianfg.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name  = "cst_linkman")
public class LinkMan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Integer lkmId;

    @Column(name = "lkm_name")
    private String lkmName;

    @Column(name = "lkm_gender")
    private String lkmGender;

    @Column(name = "lkm_phone")
    private String lkmPhone;

    @Column(name = "lkm_mobile")
    private String lkmMobile;

    @Column(name = "lkm_email")
    private String lkmEmail;

    @Column(name = "lkm_position")
    private String lkmPosition;

    @Column(name = "lkm_memo")
    private String lkmMemo;

    @Column(name = "lkm_cust_id",insertable = false,updatable = false)
    private Integer lkmCustId;

    /**
     * 配置联系人到客户的多对一关系
     *      使用注解的形式配置多对一关系
     *          1.配置表关系
     *              @ManyToOne: 配置多对一关系
     *                  targetEntity：对方的实体类字节码
     *          2.配置外键（中间表）
     */
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;

}
