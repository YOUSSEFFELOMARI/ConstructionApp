package com.ensaa.constructionapp.scheduling;

import com.ensaa.constructionapp.dto.EmployeeDto;
import com.ensaa.constructionapp.dto.MonthDto;
import com.ensaa.constructionapp.service.EmployeeService;
import com.ensaa.constructionapp.service.MonthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

@Component
@AllArgsConstructor
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final MonthService monthService;
    private final EmployeeService employeeService;

        @Scheduled(cron="0 0 0 1 1/1 *")//every first day of every month
//    @Scheduled(cron = "*/20 * * * * ?")//test for every 20 second
    public void reportCurrentTime() throws ParseException {
        log.info("Creat Month for every Employer at: {}", dateFormat.format(new Date()));

        List<EmployeeDto> employeeDtoList=employeeService.getAllEmployees();
        employeeDtoList.forEach(employeeDto ->{
            Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            try {
                monthService.create(new MonthDto(0, mapDateToFormattedDate(date),
                                            false,employeeDto.getName(),employeeDto.getLastName()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String mapDateToFormattedDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        if(date==null) return "";
        formatter.applyPattern("yyyy-MM-dd");
        return formatter.format(date);
    }
}
