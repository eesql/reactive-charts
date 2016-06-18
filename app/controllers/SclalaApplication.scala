package controllers

import actors.{StocksActor, UnwatchStock, UserActor, WatchStock}
import akka.actor.{ActorRef, Props}
import com.fasterxml.jackson.databind.JsonNode
import play.api.mvc._
import play.libs.Akka
import play.mvc.WebSocket

/**
  * Created by elainetuang on 6/18/16.
  */
class SclalaApplication extends Controller {
  def index = Action {
    Ok(views.html.index.render())
  }



}
