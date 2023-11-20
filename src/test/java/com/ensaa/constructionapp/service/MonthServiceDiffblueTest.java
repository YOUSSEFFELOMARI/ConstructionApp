package com.ensaa.constructionapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ensaa.constructionapp.mapper.MonthMapper;
import com.ensaa.constructionapp.repository.EmployeeRepository;
import com.ensaa.constructionapp.repository.MonthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MonthService.class})
@ExtendWith(SpringExtension.class)
class MonthServiceDiffblueTest {
  @MockBean
  private EmployeeRepository employeeRepository;

  @MockBean
  private MonthMapper monthMapper;

  @MockBean
  private MonthRepository monthRepository;

  @Autowired
  private MonthService monthService;

  /**
   * Method under test: {@link MonthService#count()}
   */
  @Test
  void testCount() {
    when(monthRepository.count()).thenReturn(3L);
    long actualCountResult = monthService.count();
    verify(monthRepository).count();
    assertEquals(3L, actualCountResult);
  }
}
