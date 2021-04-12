package ru.geekbrains.service.manufacturer;

import ru.geekbrains.controller.DTO.ManufacturerAdminDTO;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.repo.ManufacturerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ManufacturerAdminServiceImpl implements ManufacturerAdminService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerAdminServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<ManufacturerAdminDTO> showAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(ManufacturerAdminDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ManufacturerAdminDTO> findManufacturerById(Long id) {
        return manufacturerRepository.findById(id)
                .map(ManufacturerAdminDTO::new);
    }

    @Override
    public void delete(Long id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public void save(ManufacturerAdminDTO manufacturerAdminDTO) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerAdminDTO.getId());
        manufacturer.setTitle(manufacturerAdminDTO.getTitle());
        manufacturerRepository.save(manufacturer);

    }
}
