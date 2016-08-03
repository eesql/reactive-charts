package controllers

import actors.HBaseActor
import akka.actor.{ActorRef, Props}
import akka.util.Timeout
import com.google.inject.Singleton
import play.api.libs.EventSource
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.{Concurrent, Enumeratee}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.libs.Akka
import utils.HBaseTables

import scala.concurrent.duration._


/**
  * Created by elainetuang on 6/16/16.
  */
@Singleton
class RealtimeChart  extends Controller{

  val hbaseActor: ActorRef = Akka.system.actorOf(Props.create(classOf[HBaseActor]))

  implicit val timeout = Timeout(5 seconds)


  val tick = Akka.system.scheduler.schedule(Duration.Zero, 2.seconds ) {
    Pool.addData()
  }


  def index = Action {
    Ok( views.html.realtime.render() )
  }

  def test = Action {
    Ok( Json.toJson( HBaseTables.getHDailyOrders("2016-07-19") ) )
  }

  // send event to get hbase data
  def updateMinOrder = Action { req=>

    /**
    (hbaseActor ? DailyOrder()).mapTo[JsValue].map {
      message => Ok.chunked(Enumerator(message)
        &> connDeathWatch(req.remoteAddress)
        &> EventSource()).as(
        "text/event-stream").withHeaders(("Cache-Control","no-cache"))
    }**/

    Ok.feed(Pool.data
      &> connDeathWatch(req.remoteAddress)
      &> EventSource()).as(
      "text/event-stream").withHeaders(("Cache-Control","no-cache"))
  }

  /** Enumeratee for detecting disconnect of SSE stream */
  def connDeathWatch(addr: String): Enumeratee[JsValue, JsValue] =
    Enumeratee.onIterateeDone{ () => println(addr + " - SSE disconnected") }


  /** object hold updated hbase data **/
  object Pool {

    val (data, channel) = Concurrent.broadcast[JsValue]

    def addData():Unit = {
      channel.push( Json.toJson( HBaseTables.getHMinOrders(java.time.LocalDate.now.toString)) )
    }
  }


}
