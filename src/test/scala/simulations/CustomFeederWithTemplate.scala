package simulations

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

class CustomFeederWithTemplate extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  var idNumbers = (21 to 30).iterator
  val rnd = new Random()
  val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val now = LocalDate.now()

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  def getRandomDate(startDate: LocalDate, random: Random): String = {
    startDate.minusDays(random.nextInt(30)).format(pattern)
  }

  val customFeeder = Iterator.continually(Map(
    "gameId" -> idNumbers.next(),
    "name" -> ("Game-" + randomString(5)),
    "releaseDate" -> getRandomDate(now, rnd),
    "reviewScore" -> rnd.nextInt(100),
    "category" -> ("Category-" + randomString(6)),
    "rating" -> ("Rating-" + randomString(4))
  ))

  def postNewGame() = {
    repeat(5) {
      feed(customFeeder)
        .exec(http("Post new game")
          .post("videogames/")
          .body(ElFileBody("bodies/newGameTemplate.json")).asJson
          .check(status.is(200)))
        .pause(1)
    }
  }

  val scn = scenario("Post new games")
    .exec(postNewGame())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
