package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.advice.CustumMessage;
import axxentis.intenship.laboratoireapi.advice.CustumStatus;
import axxentis.intenship.laboratoireapi.dto.responses.CityResponseDto;
import axxentis.intenship.laboratoireapi.dto.responses.CustumApiResponse;
import axxentis.intenship.laboratoireapi.dto.responses.DepartmentResponseDto;
import axxentis.intenship.laboratoireapi.entities.City;
import axxentis.intenship.laboratoireapi.entities.Department;
import axxentis.intenship.laboratoireapi.exception.ResourceNotFoundException;
import axxentis.intenship.laboratoireapi.repositories.CityRepository;
import axxentis.intenship.laboratoireapi.services.CityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/city")
public class CityController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    CityService cityService;
    CityRepository cityRepository;
    public static String message;

    @PostMapping("/add")
    public City addCity(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @PutMapping("/update")
    public City updateCity(@RequestBody City city) {
        return cityRepository.save(city);
    }


    @GetMapping(value = "/city/find/{id}")
    public ResponseEntity<?> findCityById(@Valid @PathVariable(value = "id") @Min(1) Long id) {
        LOGGER.trace("entering getCityById() method");
        Optional<City> elementFound = cityService.findCityById(id);
        if (elementFound.isEmpty()) {
            message = CustumMessage.DONNEE_INTROUVABLE;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        } else {
            message = CustumMessage.OPERATION_SUCCESS;
            LOGGER.info(message);
            CityResponseDto cityDtoFound = mapCityToCityResponseDTO(elementFound.get());
            return ResponseEntity.ok(new CustumApiResponse(cityDtoFound, message, CustumStatus.OK));
        }

    }

    private CityResponseDto mapCityToCityResponseDTO(City city) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(city, CityResponseDto.class);
    }


}
