package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("SELECT CASE WHEN m.activo IS NOT NULL THEN m.activo ELSE FALSE END " +
            "FROM Medico m WHERE m.id = :idMedico")
    boolean findActivoById(@Param("idMedico") Long idMedico);

@Query("SELECT m FROM Medico m WHERE m.activo = TRUE AND m.especialidad = :especialidad " +
       "AND m.id NOT IN (SELECT c.medico.id FROM consulta c WHERE c.fecha = :fecha) " +
       "ORDER BY RAND()")
Medico elegirMedicoAleatorioDisponibleParaLaFecha(@Param("especialidad") Especialidad especialidad,
                                                  @Param("fecha") LocalDateTime fecha);
}