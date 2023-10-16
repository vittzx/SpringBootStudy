package com.vitor.estudo.api.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.estudo.api.Medico.Medico;

// Repository JPA == DAO
// JpaRepository<Nome Da Entidade, Tipo do ID>
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    
}
