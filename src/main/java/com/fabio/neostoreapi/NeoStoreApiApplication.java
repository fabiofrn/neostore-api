package com.fabio.neostoreapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
public class NeoStoreApiApplication extends ResourceConfig {

	public NeoStoreApiApplication() {
		packages(true, getClass().getPackageName());
		property(ServerProperties.BV_FEATURE_DISABLE, false);
	}
}
