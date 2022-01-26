package cn.qianfg.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "RoleId", nullable = false, length = 20)
    private Long roleId;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId", insertable = false, updatable = false)
    private Role role;

}
