package cn.qianfg.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /**
     * 配置客户和联系人之间的关系
     * 使用注解的形式配置多表关系
     *      1.声明关系
     *          @OneToMany: 配置一对多关系
     *              targetEntity: 对方对象的字节码对象
     *              cascade = CascadeType.ALL： 级联所有操作
     *      2.配置外键（中间表）
     *          @JoinColumn
     *              name: 外键字段名称
     *              referencedColumnName: 主表的主键字段名称
     *
     *      @OneToMany(mappedBy = "customer")
     *          放弃外键维护权
     *
     *      CascadeType.ALL:    级联所有
     *      CascadeType.MERGE:  更新
     *      CascadeType.PERSIST: 保存
     *      CascadeType.REMOVE: 删除
     */
    @OneToMany(targetEntity = LinkMan.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
//    @OneToMany(mappedBy = "customer")   //放弃维护权
    private Set<LinkMan> linkMans = new HashSet<>();

}
