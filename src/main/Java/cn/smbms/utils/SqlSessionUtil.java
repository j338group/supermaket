package cn.smbms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory ssf = null;

static {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
        ssf = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
        e.printStackTrace();

        }
        }
public static SqlSession getSqlSession() {
        return ssf.openSession();
        }
        }
