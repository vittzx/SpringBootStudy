create table consultas(
    id bigint not null auto_increment,
    medico bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_medico_id foreign key(id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(id) references pacientes(id)
)