package com.pajelonek.clipwatcher.mybatis;

import com.pajelonek.clipwatcher.domain.test.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employees")
    public List<Employee> findAll();

    @Select("SELECT * FROM employees WHERE id = #{id}")
    public Employee findById(long id);

    @Delete("DELETE FROM employees WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO employees(name, dept, salary) " +
            " VALUES ( #{name}, #{dept}, #{salary})")
    public int insert(Employee employee);

    @Update("Update employees set first_name=#{name}, " +
            " last_name=#{dept}, email_address=#{salary} where id=#{id}")
    public int update(Employee employee);
}