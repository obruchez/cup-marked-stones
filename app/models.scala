package models

import play.db.anorm._
import play.db.anorm.SqlParser._

case class Stone(
  id: Pk[Long],
  number: String,
  name: Option[String],
  notes: Option[String]
)

case class StoneLocation(
  gps: Boolean,
  gps_precision: Option[Double],
  commune: String,
  town: String,
  place_name: Option[String],
  map: Option[Int],
  longitude: Option[Double],
  latitude: Option[Double],
  altitude: Option[Double],
  location_panoramic: Boolean,
  location_near_path: Boolean,
  location_in_woods: Boolean,
  location_in_slope: Boolean,
  location_in_meadow: Boolean,
  location_in_group: Boolean,
  location_comments: Option[String]
)

case class StoneCharacteristics(
  existing: Boolean,
  type_erratic: Boolean,
  type_in_scree: Boolean,
  type_rock: Boolean,
  type_comments: Option[String],
  rock_nature: Option[String],
  size_width: Option[Double],
  size_height: Option[Double],
  size_depth: Option[Double]
)

case class StoneTypology(
  signs_cup_count: Option[Int],
  signs_canal_count: Option[Int],
  signs_other_count: Option[Int]
)

case class Book(
  id: Pk[Long],
  title: String,
  publication: java.util.Date,
  isbn: Option[String],
  publisher: Option[String],
  publisher_place: Option[String],
  edition: Option[Int],
  page_count: Option[Int],
  notes: Option[String],
  authors: Option[String]
)