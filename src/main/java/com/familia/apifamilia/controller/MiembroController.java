package com.familia.apifamilia.controller;

import com.familia.apifamilia.dto.MiembroDTO;
import com.familia.apifamilia.service.MiembroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/miembros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite conexión desde Angular
public class MiembroController {

    private final MiembroService miembroService;

    // GET /api/miembros
    @GetMapping
    public ResponseEntity<List<MiembroDTO>> obtenerTodos() {
        return ResponseEntity.ok(miembroService.obtenerTodos());
    }

    // GET /api/miembros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MiembroDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(miembroService.obtenerPorId(id));
    }

    // POST /api/miembros
    @PostMapping
    public ResponseEntity<MiembroDTO> crear(@Valid @RequestBody MiembroDTO dto) {
        MiembroDTO creado = miembroService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT /api/miembros/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MiembroDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MiembroDTO dto) {
        return ResponseEntity.ok(miembroService.actualizar(id, dto));
    }

    // DELETE /api/miembros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        miembroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/miembros/relacion/{relacion}
    @GetMapping("/relacion/{relacion}")
    public ResponseEntity<List<MiembroDTO>> buscarPorRelacion(@PathVariable String relacion) {
        return ResponseEntity.ok(miembroService.buscarPorRelacion(relacion));
    }

    // GET /api/miembros/buscar?q=texto
    @GetMapping("/buscar")
    public ResponseEntity<List<MiembroDTO>> buscar(@RequestParam String q) {
        return ResponseEntity.ok(miembroService.buscar(q));
    }
}
