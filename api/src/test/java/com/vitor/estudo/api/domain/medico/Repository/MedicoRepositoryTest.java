package com.vitor.estudo.api.domain.medico.Repository;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest //
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usar o database real da aplicacao
@ActiveProfiles("test") // application- nome do parametro ---> application-test
public class MedicoRepositoryTest {
    
    @Test
    void esccolherMedicoAleatorioLivreNaData(){
    }
}
