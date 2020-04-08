import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RuntimeParameters extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  val scn = scenario("Get all videogames")
    .forever() {
      exec(getAllVideoGames())
    }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(1) during (1 second)
    )
  ).protocols(httpConf)
    .maxDuration(20 seconds)

}
