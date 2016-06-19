package actors

import akka.actor._

import scala.concurrent.duration._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.Random

import scala.collection.immutable.HashSet

/**
  * Created by chad on 6/19/2016.
  */

object  demoActor {
  def props = Props[demoActor]

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
      sender() ! List(List(new Random().nextDouble()*100, new Random().nextDouble()*10))
    case updateDemo() =>
      demo = List(new Random().nextDouble()*100, new Random().nextDouble()*10)
      watchers.foreach(_ ! testActor)
  }
}
