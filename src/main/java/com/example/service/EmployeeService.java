package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.mapper.EmployeeMapper;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee);
    void deleteEmployee(Long employeeId);
}
