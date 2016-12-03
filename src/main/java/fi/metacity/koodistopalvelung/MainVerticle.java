package fi.metacity.koodistopalvelung;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fi.metacity.koodistopalvelung.dto.rest.CodeSystem;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * Main verticle used to bootstrap the server.
 */
public class MainVerticle extends AbstractVerticle {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void start() {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		Router router = Router.router(vertx);
		router.route(HttpMethod.GET, "/api/v1/code_systems").handler(ctx -> {
			try {
				CodeSystem sth1 = new CodeSystem("1.2.246.537.6.651.2010", "STH - STH1 Hampaiden numerointi 2010");
				ctx.response().setChunked(true).end(mapper.writeValueAsString(sth1));
			} catch (JsonProcessingException ex) {
				LoggerFactory.getLogger(MainVerticle.class.getName()).fatal(ex);
			}
		});

		vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(8080);
	}

}
