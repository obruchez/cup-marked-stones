package models

import play.db.anorm._
import play.db.anorm.defaults._
import play.db.anorm.SqlParser._

case class Stone(
  id: Pk[Long],
  number: String,
  name: Option[String],
  notes: Option[String],
  location_id: Long,
  characteristics_id: Long,
  typology_id: Long
)

case class StoneLocation(
  id: Pk[Long],
  gps: Boolean = false,
  gps_precision: Option[Double] = None,
  commune: Option[String] = None,
  town: Option[String] = None,
  place_name: Option[String] = None,
  map: Option[Int] = None,
  longitude: Option[Double] = None,
  latitude: Option[Double] = None,
  altitude: Option[Double] = None,
  location_panoramic: Boolean = false,
  location_near_path: Boolean = false,
  location_in_woods: Boolean = false,
  location_in_slope: Boolean = false,
  location_in_meadow: Boolean = false,
  location_in_group: Boolean = false,
  location_comments: Option[String] = None
)

case class StoneCharacteristics(
  id: Pk[Long],
  existing: Boolean = false,
  type_erratic: Boolean = false,
  type_in_scree: Boolean = false,
  type_rock: Boolean = false,
  type_comments: Option[String] = None,
  rock_nature: Option[String] = None,
  size_width: Option[Double] = None,
  size_height: Option[Double] = None,
  size_depth: Option[Double] = None
)

case class StoneTypology(
  id: Pk[Long],
  signs_cup_count: Option[Int] = None,
  signs_canal_count: Option[Int] = None,
  signs_other: Option[String] = None
)

case class Book(
  id: Pk[Long],
  number: String,
  title: String,
  authors: Option[String] = None,
  publication: Option[java.util.Date] = None,
  isbn: Option[String] = None,
  publisher: Option[String] = None,
  publisher_place: Option[String] = None,
  edition: Option[Int] = None,
  page_count: Option[Int] = None,
  notes: Option[String] = None
)

object Stone extends Magic[Stone]

object StoneLocation extends Magic[StoneLocation]

object StoneCharacteristics extends Magic[StoneCharacteristics]

object StoneTypology extends Magic[StoneTypology]

object Book extends Magic[Book]
