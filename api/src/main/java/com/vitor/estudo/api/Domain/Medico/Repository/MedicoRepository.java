package com.vitor.estudo.api.Domain.Medico.Repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitor.estudo.api.Domain.Medico.Especialidade;
import com.vitor.estudo.api.Domain.Medico.Medico;

// Repository JPA == DAO
// JpaRepository<Nome Da Entidade, Tipo do ID>
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medico m 
            where m.ativo = true  
            and
            m.especialidade = :especialidade
            and
            m.id not in (
                select c.medico.id from Consulta c  
                where 
                c.data = :dataConsulta
            )
            order by rand()
            limit 1
            """)
    // :nomeDoArgumento = argumento
    Medico esccolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime dataConsulta);

    @Query("""
        select m.ativo from Medico m
        where m.id = :idMedico

    """)
    Boolean findAtivoById(Long idMedico); // aqui nao carrega a Entidade Medico e sim retorna true ou false.
    
}