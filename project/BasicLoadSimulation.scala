import scala.concurrent.duration._

class BasicLoadSimulation extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def getSpecificGame() = {
    exec(
      http("Get specific game")
        .get("videogames/2")
        .check(status.is(200))
    )
  }

  val scn = scenario("Basic Load Simulation")
    .exec(getAllVideoGames())
    .pause(5)
    .exec(getSpecificGame())
    .pause(5)
    .exec(getAllVideoGames())

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(5),
      rampUsers(10) during (10 seconds)
    ).protocols(httpConf.inferHtmlResources())
  )
}
