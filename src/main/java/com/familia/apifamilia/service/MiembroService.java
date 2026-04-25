package com.familia.apifamilia.service;

import com.familia.apifamilia.dto.MiembroDTO;
import com.familia.apifamilia.exception.MiembroNotFoundException;
import com.familia.apifamilia.model.Miembro;
import com.familia.apifamilia.repository.MiembroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MiembroService {

    private final MiembroRepository miembroRepository;

    // ── Obtener todos ──────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<MiembroDTO> obtenerTodos() {
        return miembroRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ── Obtener por ID ─────────────────────────────────────────
    @Transactional(readOnly = true)
    public MiembroDTO obtenerPorId(Long id) {
        Miembro miembro = miembroRepository.findById(id)
                .orElseThrow(() -> new MiembroNotFoundException(id));
        return toDTO(miembro);
    }

    // ── Crear ──────────────────────────────────────────────────
    public MiembroDTO crear(MiembroDTO dto) {
        if (miembroRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un miembro con el email: " + dto.getEmail());
        }
        Miembro miembro = toEntity(dto);
        return toDTO(miembroRepository.save(miembro));
    }

    // ── Actualizar ─────────────────────────────────────────────
    public MiembroDTO actualizar(Long id, MiembroDTO dto) {
        Miembro existente = miembroRepository.findById(id)
                .orElseThrow(() -> new MiembroNotFoundException(id));

        // Verificar email duplicado (solo si cambió)
        if (!existente.getEmail().equals(dto.getEmail()) &&
            miembroRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un miembro con el email: " + dto.getEmail());
        }

        existente.setNombre(dto.getNombre());
        existente.setApellido(dto.getApellido());
        existente.setEmail(dto.getEmail());
        existente.setTelefono(dto.getTelefono());
        existente.setRelacion(dto.getRelacion());
        existente.setFechaNacimiento(dto.getFechaNacimiento());
        existente.setDireccion(dto.getDireccion());
        existente.setCiudad(dto.getCiudad());
        existente.setPais(dto.getPais());

        return toDTO(miembroRepository.save(existente));
    }

    // ── Eliminar ───────────────────────────────────────────────
    public void eliminar(Long id) {
        if (!miembroRepository.existsById(id)) {
            throw new MiembroNotFoundException(id);
        }
        miembroRepository.deleteById(id);
    }

    // ── Buscar por relación ────────────────────────────────────
    @Transactional(readOnly = true)
    public List<MiembroDTO> buscarPorRelacion(String relacion) {
        return miembroRepository.findByRelacion(relacion)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── Buscar general ─────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<MiembroDTO> buscar(String query) {
        return miembroRepository.buscarPorNombreApellidoOEmail(query)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ── Mappers ────────────────────────────────────────────────
    private MiembroDTO toDTO(Miembro m) {
        return MiembroDTO.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .apellido(m.getApellido())
                .email(m.getEmail())
                .telefono(m.getTelefono())
                .relacion(m.getRelacion())
                .fechaNacimiento(m.getFechaNacimiento())
                .direccion(m.getDireccion())
                .ciudad(m.getCiudad())
                .pais(m.getPais())
                .build();
    }

    private Miembro toEntity(MiembroDTO dto) {
        return Miembro.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .relacion(dto.getRelacion())
                .fechaNacimiento(dto.getFechaNacimiento())
                .direccion(dto.getDireccion())
                .ciudad(dto.getCiudad())
                .pais(dto.getPais())
                .build();
    }
}
