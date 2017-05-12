package fr.insarennes;

import fr.insarennes.resource.CalendarResource;
import fr.insarennes.utils.MyExceptionMapper;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	// Base URI the Grizzly HTTP server will listen on
	private static final String HTTP_ADDRESS = "http://localhost:8080/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 */
	private static HttpServer startServer() {
		final ResourceConfig rc = new ResourceConfig(CalendarResource.class).
			packages("fr.insarennes.resource").
			register(MyExceptionMapper.class).
			register(io.swagger.jaxrs.listing.ApiListingResource.class).
			register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		return GrizzlyHttpServerFactory.createHttpServer(URI.create(HTTP_ADDRESS), rc);
	}

	// http://localhost:8080/swagger.json to get the REST API in the JSON format
	// http://localhost:8080/myCalendarApp/swag/index.html to see the REST API using Swagger-UI
	// http://localhost:8080/myCalendarApp/index.html
	// http://localhost:8080/myCalendarApp/calendar.html
	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		final StaticHttpHandler handler = new StaticHttpHandler("src/main/webapp");

		// Required to access the web pages stored in the webapp folder.
		server.getServerConfiguration().addHttpHandler(handler, "/myCalendarApp/");

		// Launching the server.
		ClientBuilder.newClient().target(HTTP_ADDRESS);

		// Required to edit HTML and JS files during the execution of the server (dev mode).
		handler.setFileCacheEnabled(false);

		System.in.read();
		server.shutdownNow();
	}
}

