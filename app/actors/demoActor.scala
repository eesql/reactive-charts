package actors

import akka.actor._

import scala.concurrent.duration._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.Random


import play.api.libs.iteratee.Concurrent
import play.libs.Akka

import akka.util.Timeout



/**
  * Created by chad on 6/19/2016.
  */

object DemoActor {

  def props = Props[DemoActor]

  private lazy val ref =
    Akka.system.actorOf(Props[DemoActor])
  implicit val timeout: Timeout = 5.seconds


  case class TestActor()
  case class updateDemo()
}

class DemoActor extends Actor {
  import DemoActor._

  val tick = context.system.scheduler.schedule(Duration.Zero, 1.seconds, self, updateDemo)


  def receive = {
    case TestActor() =>
      sender() ! List(List(new Random().nextDouble()*100, new Random().nextDouble()*10))
    case updateDemo() =>
      Feed.addData()
  }

  object Feed {

    val (notifications, channel) = Concurrent.broadcast[List[List[Double]]]

    def addData():Unit = {
      channel.push(List(List(new Random().nextDouble()*100, new Random().nextDouble()*10)))
    }
  }
}
