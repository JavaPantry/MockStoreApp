package org.avp.quota.kpi.web.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.configuration.common.ApplicationConfiguration;
import org.avp.quota.kpi.web.service.interfaces.IApplicationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;

import flexjson.JSONDeserializer;

@Service
public class ApplicationConfigurationService implements IApplicationConfigurationService{
	private static Logger logger = Logger.getLogger(ApplicationConfigurationService.class);

	@Autowired
	private Environment env;


	private static ApplicationConfiguration APPLICATION_CONFIGURATION;

	public  ApplicationConfiguration getApplicationConfiguration() {
		return APPLICATION_CONFIGURATION;
	}

	public static final String PROJECT_CONFIG_PATH = "c:/MockStub/MockStub.json";
	public static final String SERVER_CONFIG_PATH = "/AppProperties/MockStub/MockStub.json";
	@PostConstruct
	private void init() {
		//String path = env.getProperty("jboss.server.config.dir")+SERVER_CONFIG_PATH;
		String path = PROJECT_CONFIG_PATH;
		System.out.println("ApplicationConfigurationService.init() path = "+path);
		//initialize(path);
		logger.debug("ApplicationConfigurationService init completed");
	}

	private synchronized static void initialize(String configurationFileName) {
		String jsonString = null;
		try {
			jsonString = Files.toString(new File(configurationFileName), Charsets.UTF_8);
		} catch (IOException e) {
			logger.error(e, e);
		}

		if (!Strings.isNullOrEmpty(jsonString)) {
			APPLICATION_CONFIGURATION = new JSONDeserializer<ApplicationConfiguration>()
					.use("authExcludePatterns", ArrayList.class)
					.use("attachmentContentTypes", ArrayList.class)
					.use("attachmentExtensions", ArrayList.class)
					.deserialize(jsonString, ApplicationConfiguration.class);
		}
	}

}
