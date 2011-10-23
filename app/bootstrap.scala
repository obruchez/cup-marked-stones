import play.db.anorm._
import play.jobs._
import play.test._
import play.vfs._
import scala.xml._
import models._
    
@OnApplicationStart class BootStrap extends Job {
  override def doJob {
    if (Stone.count().single() == 0) {
      migrateStones()
      println(Stone.count().single()+" stone(s) written to the database")
    }

    if (Book.count().single() == 0) {
      migrateBooks()
      println(Book.count().single()+" book(s) written to the database")
    }
  }

  def migrateStones(): Seq[Stone] = {
    val vf = VirtualFile.fromRelativePath("/conf/stones.xml");
    val stones = XML.loadFile(vf.getRealFile())
    (for (stoneElem <- stones \\ "Pierres_x0020_à_x0020_cupules") yield {
      def getString(elem: String): Option[String] =
        Option((stoneElem \\ elem).text).filter(_.length > 0)
      def getInt(elem: String): Option[Int] =
        Option((stoneElem \\ elem).text).filter(_.length > 0).map(_.toInt)
      def getDouble(elem: String): Option[Double] =
        Option((stoneElem \\ elem).text).filter(_.length > 0).map(_.toDouble)
      def getBoolean(elem: String): Boolean =
        Option((stoneElem \\ elem).text).map(v => if (v.toInt != 0) true else false).getOrElse(false)

      val stone = new Stone(
        id = NotAssigned,
        number = getString("Numéro").getOrElse(""),
        name = getString("Nom"),
        notes = getString("Notes"),
        gps = getBoolean("GPS"),
        gps_precision = getDouble("Précision").filter(_ > 0),
        commune = getString("Commune"),
        town = getString("Localité"),
        place_name = getString("Lieu"),
        map = getInt("Carte").filter(_ > 0),
        longitude = getDouble("Longitude").filter(_ > 0),
        latitude = getDouble("Latitude").filter(_ > 0),
        altitude = getDouble("Altitude").filter(_ > 0),
        location_panoramic = getBoolean("EmplacementPanoramique"),
        location_near_path = getBoolean("EmplacementPrèsSentier"),
        location_in_woods = getBoolean("EmplacementDansBois"),
        location_in_slope = getBoolean("EmplacementSurPente"),
        location_in_meadow = getBoolean("EmplacementDansPré"),
        location_in_group = getBoolean("EmplacementEnGroupe"),
        location_comments = getString("EmplacementCommentaire"),
        existing = getBoolean("Existante"),
        type_erratic = getBoolean("TypeErratique"),
        type_in_scree = getBoolean("TypeDansEboulement"),
        type_rock = getBoolean("TypeRocher"),
        type_comments = getString("TypeCommentaire"),
        rock_nature = getString("NatureRoche"),
        size_width = getDouble("DimensionLargeur").filter(_ > 0),
        size_height = getDouble("DimensionHauteur").filter(_ > 0),
        size_depth = getDouble("DimensionLongueur").filter(_ > 0),
        signs_cup_count = getInt("SignesCupules"),
        signs_canal_count = getInt("SignesCanaux"),
        signs_other = getString("SignesAutres")
      )

      Stone.create(stone)

      stone
    }).sortBy(_.number)
  }

  def migrateBooks(): Seq[Book] = {
    val vf = VirtualFile.fromRelativePath("/conf/books.xml");
    val books = XML.loadFile(vf.getRealFile())
    (for (bookElem <- books \\ "Bibliographie") yield {
      def getString(elem: String): Option[String] =
        Option((bookElem \\ elem).text).filter(_.length > 0)
      def getInt(elem: String): Option[Int] =
        Option((bookElem \\ elem).text).filter(_.length > 0).map(_.toInt)

      val book = Book(
        id = NotAssigned,
        number = getString("RéfLivre").getOrElse(""),
        title = getString("Titre").getOrElse(""),
        authors = getString("Auteur"),
        publication = getString("AnnéeCopyright") map { y =>
          (new java.util.GregorianCalendar(y.toInt, java.util.Calendar.JANUARY, 1)).getTime()
        },
        isbn = getString("NuméroISBN"),
        publisher = getString("NomÉditeur"),
        publisher_place = getString("LieuPublication"),
        edition = getInt("NuméroÉdition"),
        page_count = getInt("Pages"),
        notes = getString("Remarques")
      )

      Book.create(book)

      book
    }).sortBy(_.number)
  }
}
