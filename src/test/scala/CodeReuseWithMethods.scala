import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CodeReuseWithMethods extends Simulation {

  // http config

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  // methods defined

  def getAllVideoGames()= {
    exec(http("Get all video games - 1st call")
      .get("videogames")
     .check(status.is(200)))
  }

  def getSpecificVideoGame() = {
    exec(http("Get specific name")
      .get("videogames/1")
    .check(status.in(200 to 210))
    )
  }

  // scenario defined

  val scn = scenario("Code reuse")
      .exec(getAllVideoGames())

      .pause(5)
      .exec(getSpecificVideoGame())
      .pause(5)
      .exec(getAllVideoGames())

  // scenario load

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
