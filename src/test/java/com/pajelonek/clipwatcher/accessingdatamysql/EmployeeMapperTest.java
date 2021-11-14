package com.pajelonek.clipwatcher.accessingdatamysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void whenRecordsInDatabase_shouldReturnArticleWithGivenId() {
        Employee article = employeeMapper.findById(1L);

        assertThat(article).isNotNull();
        assertThat(article.getName()).isEqualTo("name");
        assertThat(article.getDept()).isEqualTo("1");
        assertThat(article.getSalary()).isEqualTo(123);
    }
}