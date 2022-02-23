package com.pajelonek.clipwatcher.mybatis;

import com.pajelonek.clipwatcher.domain.test.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("LOCAL")
@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void whenRecordsInDatabase_shouldReturnArticleWithGivenId() {
        Employee article = employeeMapper.findById(1L);

        assertThat(article).isNotNull();
        assertThat(article.getName()).isEqualTo("wiktor");
        assertThat(article.getDept()).isEqualTo("1");
        assertThat(article.getSalary()).isEqualTo(new BigDecimal("1234.0"));
    }
}