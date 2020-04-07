import scala.concurrent.duration.DurationInt

class AddPauseTime extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Video Game DB - 3 calls")
    // hitting the endpoint to get all of the  video games
    .exec(http("Get all video games - 1st call")
    .get("videogames"))
    .pause(5)

      // hitting another endpoint
    .exec(http("Get specific name")
    .get("videogames/1"))
    .pause(1, 20)

    // hitting the first endpoint again
    .exec(http("Get all video games - 2nd call")
    .get("videogames"))
    .pause(3000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
