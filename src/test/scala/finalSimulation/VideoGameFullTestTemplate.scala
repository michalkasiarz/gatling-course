package finalSimulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.util.Random

class VideoGameFullTestTemplate extends Simulation {

  // URL changed due to Travis CI testing
  val httpConf = http.baseUrl("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/")
    .header("Accept", "application/json")

  // Variables
  def userCount: Int = getProperty("USERS", "3").toInt

  def rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt

  def testDuration: Int = getProperty("DURATION", "60").toInt

  var idNumbers = (20 to 1000).iterator
  var rnd = new Random()
  var now = LocalDate.now()
  var pattern = DateTimeFormatter.ofPattern("yyy-MM-dd")

  // Helper methods

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  def getRandomDate(startDate: LocalDate, random: Random): String = {
    startDate.minusDays(random.nextInt(30)).format(pattern)
  }

  // Custom feeder

  val customFeeder = Iterator.continually(Map(
    "gameId" -> idNumbers.next(),
    "name" -> ("Game-" + randomString(5)),
    "releaseDate" -> getRandomDate(now, rnd),
    "reviewScore" -> rnd.nextInt(100),
    "category" -> ("Category-" + randomString(6)),
    "rating" -> ("Rating-" + randomString(4))
  ))

  // Before

  before {
    println(s"Running out test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Total test duration: ${testDuration} seconds")
  }

  // HTTP calls

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def postNewGame() = {
    feed(customFeeder).
      exec(http("Post new game")
        .post("videogames")
        .body(ElFileBody("bodies/newGameTemplate.json")).asJson
        .check(status.is(200)))
  }

  def getLastPostedGame() = {

    exec(http("Get last posted game")
      .get("videogames/${gameId}")
      .check(jsonPath("$.name").is("${name}"))
      .check(status.is(200))
    )
  }

  def deleteLastPostedGame() = {
    exec(http("Delete last posted game")
      .delete("videogames/${gameId}")
      .check(status.is(200))
    )
  }

  // Scenario design

  val scn = scenario("Final scenario")
    .forever() {
      exec(getAllVideoGames())
        .pause(2)
        .exec(postNewGame())
        .pause(2)
        .exec(getLastPostedGame())
        .pause(2)
        .exec(deleteLastPostedGame())
        .pause(2)
        .exec(getLastPostedGame())
    }

  // Setup load simulation

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      rampUsers(userCount) during (rampDuration seconds)
    )
  ).protocols(httpConf)
    .maxDuration(testDuration seconds)
      .assertions(
        global.responseTime.max.lt(2),
        global.successfulRequests.percent.gt(70)
      )
  // After

  after {
    println("Stress test completed.")
  }

}
