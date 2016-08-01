package actors

import akka.actor._

import scala.concurrent.duration._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.{JsValue, Json}
import play.libs.Akka

import akka.util.Timeout
import utils.HBaseTables


/**
  * Created by chad on 6/19/2016.
  */

object HBaseActor {

  def props = Props[HBaseActor]

  private lazy val ref =
    Akka.system.actorOf(Props[HBaseActor])
  implicit val timeout: Timeout = 5.seconds


  case class DailyOrder()
  case class updateDemo()
}

class HBaseActor extends Actor {
  import HBaseActor._
  implicit val timeout: Timeout = 5.seconds
  //val tick = context.system.scheduler.schedule(Duration.Zero, 1.seconds, self, updateDemo)


  def receive = {
    case DailyOrder() =>
      sender() ! Json.toJson( HBaseTables.getHDailyOrders("2016-07-28") )
    case updateDemo() =>
      sender() ! DailyOrder()
  }

}