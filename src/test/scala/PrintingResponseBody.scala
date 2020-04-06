import io.gatling.core.Predef._
import io.gatling.http.Predef._



class PrintingResponseBody extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Check JSON Path")

    .exec(http("Get specific game")
      .get("videogames/1")
      .check(jsonPath("$.name").is("Resident Evil 4")))

    .exec(http("Get all video games")
    .get("videogames")
    .check(jsonPath("$[1].id").saveAs("gameId")))
    .exec { session => println(session); session}


    .exec(http("Get specific game")
    .get("videogames/${gameId}")
    .check(jsonPath("$.name").is("Gran Turismo 3"))
    .check(bodyString.saveAs("responseBody")))
    // printing response body
      .exec { session => println(session("responseBody").as[String]); session}

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}