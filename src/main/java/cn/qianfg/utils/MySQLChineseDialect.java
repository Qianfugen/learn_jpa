package cn.qianfg.utils;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;

public class MySQLChineseDialect extends MySQL5InnoDBDialect {
    //mysql 使JPQL支持中文排序
    public MySQLChineseDialect() {
        super();
        registerFunction("convert", new SQLFunctionTemplate(StringType.INSTANCE, "convert(?1 using gbk)"));
    }
}
