import _root_.models._
import play._
import play.db.anorm._
import play.test._

import org.scalatest._
import org.scalatest.junit._
import org.scalatest.matchers._

class BasicTests extends UnitFlatSpec with ShouldMatchers {
  it should "create and retrieve a Stone" in {
    val stone = new Stone(
      id = NotAssigned,
      number = "100.1",
      name = Some("name"),
      notes = Some("notes"),
      town = Some("town"),
      type_comments = Some("type_comments"),
      signs_other = Some("signs_other")
    )

    val stoneId: Long = Stone.create(stone).fold(e => 0, s => s.id())

    stoneId should not be (0)

    val foundStone = Stone.find("number={number}").on("number" -> "100.1").first()

    foundStone should not be (None)
    foundStone.get.number should be ("100.1")
    foundStone.get.name should be (Some("name"))
    foundStone.get.notes should be (Some("notes"))
    foundStone.get.town should be (Some("town"))
    foundStone.get.type_comments should be (Some("type_comments"))
    foundStone.get.signs_other should be (Some("signs_other"))
  }
}
