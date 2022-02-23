package com.pajelonek.clipwatcher.mybatis;

import com.pajelonek.clipwatcher.domain.user.User;
import com.pajelonek.clipwatcher.domain.test.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT count(*) from users WHERE email = #{email}")
    public boolean checkIfUserExist(String email);

    @Select("SELECT * FROM user WHERE email = #{email}")
    public User find(String email);

    @Delete("DELETE FROM user WHERE email = #{email}")
    public int delete(long id);

    @Insert("INSERT INTO user(email, password) " +
            " VALUES (#{email}, #{password}")
    public int insert(User user);

    @Update("UPDATE users SET email=#{email} WHERE id=#{id}")
    public int update(Employee employee);
}