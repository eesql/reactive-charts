package controllers

import actors.HBaseActor

import akka.actor.{ActorRef, Props}
import com.google.inject.Singleton
import play.api.libs.EventSource
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import akka.util.Timeout
import play.api.libs.iteratee.{Concurrent, Enumeratee, Enumerator}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.libs.Akka
import scala.concurrent.duration._
import utils.HBaseTables


/**
  * Created by elainetuang on 6/16/16.
  */
@Singleton
class RealtimeBoard  extends Controller{

  val hbaseActor: ActorRef = Akka.system.actorOf(Props.create(classOf[HBaseActor]))

  implicit val timeout = Timeout(5 seconds)


  val tick = Akka.system.scheduler.schedule(Duration.Zero, 2.seconds ) {
    Pool.addData()
  }


  def index = Action {
    Ok( views.html.realtime.render() )
  }

  def test = Action {
    Ok( Json.toJson( HBaseTables.getRealtimePV("20160810") ) )
  }

  // send event to get hbase data
  def updateDailyKPI = Action { req=>

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
      channel.push( Json.toJson( HBaseTables.getDailyKPI(java.time.LocalDate.now.toString)) )
    }
  }


}
