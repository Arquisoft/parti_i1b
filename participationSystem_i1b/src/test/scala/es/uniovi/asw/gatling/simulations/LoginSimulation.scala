package es.uniovi.asw.gatling.simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoginSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0")

	val headers0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers1 = Map(
		"Accept" -> "application/xml, text/xml, */*; q=0.01",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Faces-Request" -> "partial/ajax",
		"X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("LoginSimulation")
		.exec(http("request0")
			.get("/")
			.headers(headers0))
		.pause(1)
		.exec(http("request1")
			.post("/login.xhtml")
			.headers(headers1)
			.formParam("javax.faces.partial.ajax", "true")
			.formParam("javax.faces.source", "j_idt4:j_idt10")
			.formParam("javax.faces.partial.execute", "@all")
			.formParam("j_idt4:j_idt10", "j_idt4:j_idt10")
			.formParam("j_idt4", "j_idt4")
			.formParam("j_idt4:j_idt7", "admin")
			.formParam("j_idt4:j_idt9", "admin")
			.formParam("javax.faces.ViewState", "-1809769687834094923:-918236950799562813")
			.resources(http("request2")
			.get("/proposalListAdmin.xhtml")
			.headers(headers0)))

	setUp(scn.inject(atOnceUsers(50))).protocols(httpProtocol)
}