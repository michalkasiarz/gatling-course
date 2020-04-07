import scala.concurrent.duration.DurationInt

class CheckResponseCode extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Get video game db - 3 calls")

    .exec(http("Get all video games - 1st call")
      .get("videogames")
        .check(status.is(200)))
    .pause(5)

    .exec(http("Get specific game")
        .get("videogames/1")
          .check(status.in(200 to 210)))
    .pause(1, 20)

    .exec(http("Get all video games - 2nd call")
      .get("videogames")
        .check(status.not(404), status.not(500)))
    .pause(3000.milliseconds)

    setUp(
      scn.inject(atOnceUsers(1))
    ).protocols(httpConf)



}
