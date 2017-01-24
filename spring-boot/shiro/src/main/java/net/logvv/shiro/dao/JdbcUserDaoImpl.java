package net.logvv.shiro.dao;

import net.logvv.shiro.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by marlon on 2017/1/23.
 */
@Repository
public class JdbcUserDaoImpl implements JdbcUserDao{

      // 单数据源配置
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    // 多数据源配置
    @Autowired
    @Qualifier("developJdbcTemplate")
    private JdbcTemplate developJdbcTemplate;

    @Autowired
    @Qualifier("productJdbcTemplate")
    private JdbcTemplate productJdbcTemplate;


    @Override
    public int insertPassenger(String name, int age) {
        String sql = "insert into uc_passenger (`name`,`age`) values (?,?)";

        // return jdbcTemplate.update(sql,name,age);
        developJdbcTemplate.update(sql,name,age);
        return productJdbcTemplate.update(sql,name,age);
    }

    @Override
    public void updatePassenger(int userId, String name, int age) {
        String sql = "update uc_passenger set `name` = ?, `age` = ? where `id` = ?";

        // jdbcTemplate.update(sql,name,age,userId);
        developJdbcTemplate.update(sql,name,age,userId);
        productJdbcTemplate.update(sql,name,age,userId);
    }

    @Override
    public void deletePassenger(int userId) {
        // jdbcTemplate.update("delete from uc_passenger where `id` = ?",userId);
        developJdbcTemplate.update("delete from uc_passenger where `id` = ?",userId);
        productJdbcTemplate.update("delete from uc_passenger where `id` = ?",userId);

    }

    @Override
    public List<Passenger> queryPassengers() {
        // return jdbcTemplate.queryForList("select `name`,`age`,`check_state` from uc_passenger",Passenger.class);
        return developJdbcTemplate.queryForList("select `name`,`age`,`check_state` from uc_passenger",Passenger.class);
    }
}
