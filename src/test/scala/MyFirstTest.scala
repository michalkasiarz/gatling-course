import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation {

  // 1 Http conf
  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", value = "application/json")

  // 2 Scenario def
  val scn = scenario("My First Test")
    .exec(http("Get All Games")
      .get("videogames")
    )

  // 3 Load scenario
  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
