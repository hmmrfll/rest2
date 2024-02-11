package com.example.service.impl;

import com.example.dto.EmployeeDto;
import com.example.entity.Employee;
import com.example.exeption.ResourceNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return EmployeeMapper.MapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee ->
                EmployeeMapper.MapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + employeeId));
        if(updateEmployee.getFirstName() != null){
            employee.setFirstName(updateEmployee.getFirstName());
        }
        if(updateEmployee.getLastName() != null){
            employee.setLastName(updateEmployee.getLastName());
        }
        if(updateEmployee.getEmail() != null){
            employee.setEmail(updateEmployee.getEmail());
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
