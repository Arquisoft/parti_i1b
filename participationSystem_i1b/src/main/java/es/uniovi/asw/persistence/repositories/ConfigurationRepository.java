package es.uniovi.asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.persistence.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{

}
