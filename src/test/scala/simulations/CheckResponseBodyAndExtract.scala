package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CheckResponseBodyAndExtract extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Check JSON Path")

    .exec(http("Get specific game")
    .get("videogames/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}