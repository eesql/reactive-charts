package actors

import akka.actor._

import scala.concurrent.duration._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.Random

import play.api.libs.iteratee.{Enumerator, Iteratee}
import play.api.libs.iteratee.Concurrent
import play.libs.Akka
import akka.pattern.ask
import akka.util.Timeout

import scala.collection.immutable.HashSet
import scala.concurrent.Future

/**
  * Created by chad on 6/19/2016.
  */

object  demoActor {
  def props = Props[demoActor]

  private lazy val ref =
    Akka.system.actorOf(Props[demoActor])
  implicit val timeout: Timeout = 5.seconds

  def notifications(): Future[Enumerator[List[List[Double]]]] = {
    (ref ? testActor()).mapTo[Enumerator[List[List[Double]]]]
  }

  case class testActor()
  case class updateDemo()
}

class demoActor extends Actor {
  import demoActor._

  var demo = List(0.0,0.0)
  val tick = context.system.scheduler.schedule(Duration.Zero, 1.seconds, self, updateDemo)
  protected[this] var watchers: HashSet[ActorRef] = HashSet.empty[ActorRef]

  def receive = {
    case testActor() =>
      sender() ! Feed.notifications
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
