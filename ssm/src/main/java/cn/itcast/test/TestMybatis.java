package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class TestMybatis {

    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void run1() throws Exception{
        //加载mybatis配置文件
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession session=factory.openSession();
        //获取代理对象
        AccountDao dao=session.getMapper(AccountDao.class);
        //查询所有数据
        List<Account> list=dao.findAll();
        for(Account account:list){
            System.out.println(account.toString());
        }
        //关闭资源
        session.close();
        in.close();
    }

    /**
     * 测试保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception{
        //加载mybatis配置文件
        InputStream in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession session=factory.openSession();
        //获取代理对象
        AccountDao dao=session.getMapper(AccountDao.class);
        //创建Account对象
        Account account=new Account();
        account.setName("zhao");
        account.setMoney(300.0);
        //保存数据所有数据
        dao.saveAccount(account);
        //提交事务
        session.commit();
        //关闭资源
        session.close();
        in.close();
    }
}
