class sth {

   var idNumbers = (1 to 10).iterator

  println(idNumbers)

  val customFeeder = Iterator.continually(Map("gameId" -> idNumbers.next()))



}
