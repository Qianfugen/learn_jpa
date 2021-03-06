package cn.qianfg.dao;

import cn.qianfg.pojo.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkManDao extends JpaRepository<LinkMan,Integer>, JpaSpecificationExecutor<LinkMan> {
}
