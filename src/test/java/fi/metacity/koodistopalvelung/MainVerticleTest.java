package fi.metacity.koodistopalvelung;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class MainVerticleTest {

	private Vertx vertx;

	@Before
	public void setUp(TestContext tc) {
		vertx = Vertx.vertx();
		vertx.deployVerticle(MainVerticle.class.getName(), tc.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext tc) {
		vertx.close(tc.asyncAssertSuccess());
	}

	@Test
	public void testThatTheServerIsStarted(TestContext tc) {
		Async async = tc.async();
		vertx.createHttpClient().getNow(8080, "localhost", "/api/v1/code_systems", response -> {
			tc.assertEquals(response.statusCode(), 200);
			response.bodyHandler(body -> {
				tc.assertTrue(body.toString().contains("STH1"));
				async.complete();
			});
		});
	}

}
