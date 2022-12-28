package com.example.employee.presentation;
import com.example.employee.entities.Employee;
import com.example.employee.entities.errorModel.ErrorDetails;
import com.example.employee.entities.exceptions.NotFoundException;
import com.example.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@CrossOrigin
public class EmployeesController {
    private final EmployeeService employeeService;

    public EmployeesController(@Qualifier("mysql") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        var employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable(name="id") int id){
        var employee = employeeService.getOneEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    @PostMapping
    public ResponseEntity<?> createOneEmployee(@RequestBody Employee employee){
        var result =  employeeService.createOneEmployee(employee);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateOneEmployee(@PathVariable(name = "id", required = true) int id,@RequestBody Employee employee){
        employeeService.updateOneEmployee(id,employee);
        return ResponseEntity
                .ok()
                .header("location","http://localhost:8082/api/employees/"+employee.getId())
                .body(employee);
    }
    @DeleteMapping(path = "{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteOneEmployee(@PathVariable(name = "id") int id){
        employeeService.deleteOneEmployee(id);
        return ResponseEntity.noContent().build();
    }

    /*@ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleException(NotFoundException ex, WebRequest request){
        var errorDetails = ErrorDetails
                .builder()
                .statusCode(404)
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }*/
}