package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.advice.CustumMessage;
import axxentis.intenship.laboratoireapi.advice.CustumStatus;
import axxentis.intenship.laboratoireapi.dto.responses.CustumApiResponse;
import axxentis.intenship.laboratoireapi.dto.responses.DepartmentResponseDto;
import axxentis.intenship.laboratoireapi.entities.Department;
import axxentis.intenship.laboratoireapi.exception.ResourceNotFoundException;
import axxentis.intenship.laboratoireapi.repositories.DepartmentRepository;
import axxentis.intenship.laboratoireapi.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/department")
public class DepartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;
    public static String message;

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
   }

//    @DeleteMapping("/delete")
//    public void deleteDepartment(@RequestParam Long idDepartment) {
//           return departmentRepository.deleteById(idDepartment);
//        }

    @GetMapping(value = "/department/find/{id}")
    public ResponseEntity<?> findDepartmentById(@Valid @PathVariable(value = "id") @Min(1) Long id) {
        LOGGER.trace("entering getdepartmentById() method");
        Optional<Department> elementFound = departmentService.findDepartmentById(id);
        if (elementFound.isEmpty()) {
            message = CustumMessage.DONNEE_INTROUVABLE;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        } else {
            message = CustumMessage.OPERATION_SUCCESS;
            LOGGER.info(message);
            DepartmentResponseDto departmentDtoFound = mapDepartmentToDepartmentResponseDTO(elementFound.get());
            return ResponseEntity.ok(new CustumApiResponse(departmentDtoFound, message, CustumStatus.OK));
        }

    }

    private DepartmentResponseDto mapDepartmentToDepartmentResponseDTO(Department department) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(department, DepartmentResponseDto.class);
    }

}


