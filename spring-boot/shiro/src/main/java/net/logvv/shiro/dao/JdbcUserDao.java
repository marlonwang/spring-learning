package net.logvv.shiro.dao;

import net.logvv.shiro.model.Passenger;

import java.util.List;

/**
 * Created by marlon on 2017/1/23.
 */
public interface JdbcUserDao {

    /** 添加用户 */
    int insertPassenger(String name,int age);

    /** 修改用户 */
    void updatePassenger(int userId,String name,int age);

    /** 删除用户 */
    void deletePassenger(int userId);

    /** 获取用户 */
    List<Passenger> queryPassengers();
}
