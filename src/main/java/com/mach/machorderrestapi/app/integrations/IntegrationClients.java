package com.mach.machorderrestapi.app.integrations;

import com.mach.machorderrestapi.shared.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IntegrationClients {
	@Value("${integration.url.api.identity}")
	private String identityURL;

	@Value("${integration.url.api.catalog}")
	private String catalogURL;

	public String getIdentityURL() {
		if(this.identityURL == null)
			throw new IntegrationException("Please, set identity url in application.properties");
		return this.identityURL;
	}

	public String getCatalogURL() {
		if(this.catalogURL == null)
			throw new IntegrationException("Please, set catalog url in application.properties");
		return this.catalogURL;
	}
}
