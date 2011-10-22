import play._
import play.db.anorm._
import play.test._

import org.scalatest._
import org.scalatest.junit._
import org.scalatest.matchers._

import models._

class BasicTests extends UnitFlatSpec with ShouldMatchers {
  it should "create and retrieve a Stone" in {
    val stoneLocation = StoneLocation(
      id = NotAssigned,
      town = Some("town")
    )

    val stoneLocationId: Long = StoneLocation.create(stoneLocation).fold(e => 0, sl => sl.id())

    stoneLocationId should not be (0)

    val stoneCharacteristics = StoneCharacteristics(
      id = NotAssigned,
      type_comments = Some("type_comments")
    )

    val stoneCharacteristicsId: Long = StoneCharacteristics.create(stoneCharacteristics).fold(e => 0, sc => sc.id())

    stoneCharacteristicsId should not be (0)

    val stoneTypology = StoneTypology(
      id = NotAssigned,
      signs_other = Some("signs_other")
    )

    val stoneTypologyId: Long = StoneTypology.create(stoneTypology).fold(e => 0, st => st.id())

    stoneTypologyId should not be (0)

    val stone = Stone(
      id = NotAssigned,
      number = "100.1",
      name = Some("name"),
      notes = Some("notes"),
      location_id = stoneLocationId,
      characteristics_id = stoneCharacteristicsId,
      typology_id = stoneTypologyId
    )

    val stoneId: Long = Stone.create(stone).fold(e => 0, s => s.id())

    stoneId should not be (0)

    val foundStone = Stone.find("number={number}").on("number" -> "100.1").first()

    foundStone should not be (None)
    foundStone.get.number should be ("100.1")
    foundStone.get.name should be (Some("name"))
    foundStone.get.notes should be (Some("notes"))
  }
}
