

class CsvFeederToCustom extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  var idNumbers = (1 to 10).iterator

  val customFeeder = Iterator.continually(Map("gameId" -> idNumbers.next()))

  def getSpecificVideoGame() = {
    repeat(10) {
      feed(customFeeder)
        .exec(http("Get specific video game")
          .get("videogames/${gameId}")
          .check(status.is(200)))
        .pause(1)
    }
  }

  val scn = scenario("CSV feeder test")
    .exec(getSpecificVideoGame())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
