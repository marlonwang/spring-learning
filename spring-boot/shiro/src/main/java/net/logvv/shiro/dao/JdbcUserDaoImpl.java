package net.logvv.shiro.dao;

import net.logvv.shiro.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by marlon on 2017/1/23.
 */
@Service
public class JdbcUserDaoImpl implements JdbcUserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertPassenger(String name, int age) {
        String sql = "insert into uc_passenger (`name`,`age`) values (?,?)";

        return jdbcTemplate.update(sql,name,age);
    }

    @Override
    public void updatePassenger(int userId, String name, int age) {
        String sql = "update uc_passenger set `name` = ?, `age` = ? where `id` = ?";

        jdbcTemplate.update(sql);
    }

    @Override
    public void deletePassenger(int userId) {
        jdbcTemplate.update("delete from uc_passenger where `id` = ?",userId);
    }

    @Override
    public List<Passenger> queryPassengers() {
        return jdbcTemplate.queryForList("select `name`,`age`,`check_state` from uc_passenger",Passenger.class);
    }
}
