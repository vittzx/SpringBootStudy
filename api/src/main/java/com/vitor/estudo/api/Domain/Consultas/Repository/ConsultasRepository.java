package com.vitor.estudo.api.Domain.Consultas.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.estudo.api.Domain.Consultas.Consulta;

public interface ConsultasRepository extends JpaRepository<Consulta,Long> {

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime dataConsulta);
    
}
