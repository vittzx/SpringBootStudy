package com.vitor.estudo.api.Domain.Paciente.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.estudo.api.Domain.Paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    boolean findAtivoById(Long idPaciente);
    
}
