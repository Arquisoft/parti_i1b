package es.uniovi.asw.business.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.ConfigurationService;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Configuration;
import es.uniovi.asw.persistence.model.ForbiddenWords;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private Factories factories;

	@Override
	public void save(Configuration conf) {
		factories.getPersistenceFactory().getConfigurationRepository().save(conf);
	}

	@Override
	public Configuration actualConfiguration() {
		List<Configuration> confs = factories.getPersistenceFactory().getConfigurationRepository().findAll();
		if (confs.size() == 0) {
			return new Configuration();
		}
		return confs.get(confs.size() - 1);
	}

	@Override
	public void delete(Configuration conf) {
		factories.getPersistenceFactory().getConfigurationRepository().delete(conf);
	}

	@Override
	public Set<ForbiddenWords> getForbiddenWords(Configuration conf) {
		return conf.getForbiddenWords();
	}

}
