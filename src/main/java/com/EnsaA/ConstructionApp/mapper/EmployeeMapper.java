//package com.EnsaA.ConstructionApp.mapper;
//
//import com.EnsaA.ConstructionApp.dto.EmployeeDto;
//import com.EnsaA.ConstructionApp.model.Employee;
//import com.EnsaA.ConstructionApp.model.Month;
//import com.EnsaA.ConstructionApp.repository.EmployeeRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Set;
//
//@Mapper(componentModel = "spring")
//public abstract class EmployeeMapper {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    SimpleDateFormat formatter = new SimpleDateFormat();
//
//    @Mapping(source = "month",target = "month")
//    public abstract EmployeeDto mapToDto(Employee employee);
//
//    @Mapping(source = "month",target = "month")
//    public abstract Employee mapToModel(EmployeeDto employeeDto);
//
//    protected String mapMonthSourceToId(Month month){return month.getDate().toString();}
//
//    protected Month mapIdToMonthSource(String s) {
//        return employeeRepository.findBy(s).orElseThrow(() ->
//                new EntityNotFoundException("Student Language not found - Name : "+s) {});
//    }
//
//    protected String mapDateToFormattedDate(Date date){
//        if(date==null) return "";
//        formatter.applyPattern("yyyy-MM-dd");
//        return formatter.format(date);
//    }
//
//    protected Date mapFormattedDateToDate(String s) throws ParseException {
//        formatter.applyPattern("yyyy-MM-dd HH:mm");
//        Date date= formatter.parse(s);
//        date.setHours(date.getHours()+1);
//        return date;
//    }
//}
