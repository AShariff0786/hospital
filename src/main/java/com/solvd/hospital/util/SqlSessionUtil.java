package com.solvd.hospital.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlSessionUtil {
    private final static Logger LOGGER = LogManager.getLogger(SqlSessionUtil.class);
    private final static String MYBATIS_CONFIG = "mybatis_config.xml";

    public static SqlSessionFactory getSession(){
        Properties props = PropertiesUtil.getProperties();
        try(InputStream stream = Resources.getResourceAsStream(MYBATIS_CONFIG);){
            return new SqlSessionFactoryBuilder().build(stream,props);
        } catch (IOException e) {
            LOGGER.error("File not found.");
            throw new RuntimeException(e);
        }
    }
}
