package com.familia.apifamilia.repository;

import com.familia.apifamilia.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Long> {

    Optional<Miembro> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Miembro> findByRelacion(String relacion);

    List<Miembro> findByCiudad(String ciudad);

    List<Miembro> findByPais(String pais);

    @Query("SELECT m FROM Miembro m WHERE " +
           "LOWER(m.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(m.apellido) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(m.email) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Miembro> buscarPorNombreApellidoOEmail(@Param("busqueda") String busqueda);
}
