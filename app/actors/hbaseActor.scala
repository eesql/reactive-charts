package actors



import akka.actor._

import scala.concurrent.duration._
import play.api.libs.json.Json
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
  case class updateData()
}

class HBaseActor extends Actor {
  import HBaseActor._
  implicit val timeout: Timeout = 5.seconds


  def receive = {
    case DailyOrder() =>
      sender() ! Json.toJson( HBaseTables.getHDailyOrders(java.time.LocalDate.now.toString) )

  }


}