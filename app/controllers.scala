package controllers

import play._
import play.mvc._

import models._

object Application extends Controller {
    
  import views.Application._

  def index = {
    html.index("Your Scala application is ready!")
  }

  def stones = {
    val stones = Stone.find().list()
    html.stones(stones)
  }

  def stone(number: String) = {
    html.index("stone "+number)
  }
}
