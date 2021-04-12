package ru.geekbrains.service.manufacturer;

import ru.geekbrains.controller.DTO.ManufacturerAdminDTO;

import java.util.List;
import java.util.Optional;

public interface ManufacturerAdminService {

    List<ManufacturerAdminDTO> showAllManufacturers();

    Optional<ManufacturerAdminDTO> findManufacturerById(Long id);

    void delete(Long id);

    void save(ManufacturerAdminDTO manufacturerAdminDTO);
}
