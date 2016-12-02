package fi.metacity.julukisilla;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fi.metacity.julukisilla.dto.rest.Area;
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
		router.route(HttpMethod.GET, "/api/v1/areas").handler(ctx -> {
			try {
				ctx.response().setChunked(true).end(mapper.writeValueAsString(Area.values()));
			} catch (JsonProcessingException ex) {
				LoggerFactory.getLogger(MainVerticle.class.getName()).fatal(ex);
			}
		});

		vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(8080);
	}

}
