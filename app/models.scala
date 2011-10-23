package models

import play.db.anorm._
import play.db.anorm.defaults._
import play.db.anorm.SqlParser._

class Stone(
  val id: Pk[Long],
  val number: String,
  val name: Option[String],
  val notes: Option[String],
  val gps: Boolean = false,
  val gps_precision: Option[Double] = None,
  val commune: Option[String] = None,
  val town: Option[String] = None,
  val place_name: Option[String] = None,
  val map: Option[Int] = None,
  val longitude: Option[Double] = None,
  val latitude: Option[Double] = None,
  val altitude: Option[Double] = None,
  val location_panoramic: Boolean = false,
  val location_near_path: Boolean = false,
  val location_in_woods: Boolean = false,
  val location_in_slope: Boolean = false,
  val location_in_meadow: Boolean = false,
  val location_in_group: Boolean = false,
  val location_comments: Option[String] = None,
  val existing: Boolean = false,
  val type_erratic: Boolean = false,
  val type_in_scree: Boolean = false,
  val type_rock: Boolean = false,
  val type_comments: Option[String] = None,
  val rock_nature: Option[String] = None,
  val size_width: Option[Double] = None,
  val size_height: Option[Double] = None,
  val size_depth: Option[Double] = None,
  val signs_cup_count: Option[Int] = None,
  val signs_canal_count: Option[Int] = None,
  val signs_other: Option[String] = None
) {}

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

object Book extends Magic[Book]
