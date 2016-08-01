package controllers

import actors.HBaseActor
import actors.HBaseActor.DailyOrder
import akka.actor.{ActorRef, ActorSystem, Props}
import com.google.inject.{Inject, Singleton}
import play.api.libs.EventSource
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import akka.pattern.ask
import akka.util.Timeout
import play.api.libs.iteratee.Enumerator
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.duration._
import utils.HBaseTables


/**
  * Created by elainetuang on 6/16/16.
  */
@Singleton
class RealtimeBoard @Inject() (system: ActorSystem) extends Controller{


  val hbaseActor: ActorRef = system.actorOf(Props.create(classOf[HBaseActor]))
  implicit val timeout = Timeout(5 seconds)

  def index = Action {
    Ok( views.html.realtime.render() )
  }

  def test = Action {
    Ok( Json.toJson( HBaseTables.getHDailyOrders("2016-07-19") ) )
  }

  // send event to get hbase data
  def updateDailyOrder = Action.async {

    (hbaseActor ? DailyOrder()).mapTo[JsValue].map {
      message => Ok.feed(Enumerator(message) &> EventSource()).as(
        "text/event-stream").withHeaders(("Cache-Control","no-cache"))
    }
  }


  def toJsValue(str: String):JsValue = {
    Json.obj("data" -> str).as[JsValue]
  }


}
