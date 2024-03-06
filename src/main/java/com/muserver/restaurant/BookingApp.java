package com.muserver.restaurant;

import java.util.Arrays;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.CORSConfigBuilder;
import io.muserver.rest.RestHandlerBuilder;

/**
 * Server for reservations
 */
public class BookingApp {
	public static void main(String[] args) {
		BookingHandler bookingHandler = new BookingHandler();
		
		MuServer server = MuServerBuilder.httpServer()
				.withHttpPort(8081)
				.addHandler(RestHandlerBuilder.restHandler(bookingHandler)
						.withCORS(CORSConfigBuilder.corsConfig()
								.withAllowedOriginRegex("http(s)?://localhost:[0-9]+")
								.withAllowedHeaders(Arrays.asList(
										"Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"))
								)
						.addCustomWriter(new JacksonJaxbJsonProvider())
	                    .addCustomReader(new JacksonJaxbJsonProvider()))
	            .start();
		
		System.out.println("Started server at " + server.uri());		
    }
}
