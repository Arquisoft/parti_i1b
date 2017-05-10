package dashboard.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class IndexSimulation10000 extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")

	val headers0 = Map("Upgrade-Insecure-Requests" -> "1")

	val scn = scenario("IndexSimulation")
		.exec(http("request0")
			.get("/")
			.headers(headers0))

	setUp(scn.inject(rampUsers(10000) over(60 seconds))).protocols(httpProtocol)
}