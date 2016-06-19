package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import akka.actor._
import javax.inject._

import akka.pattern.ask
import scala.concurrent.duration._

import actors.demoActor
import actors.demoActor._
import akka.util.Timeout
import play.api.libs.json.Json


/**
  * Created by chad on 6/19/2016.
  */

@Singleton
class FeedTest @Inject() (system: ActorSystem) extends Controller {

  val demoActors = system.actorOf(demoActor.props)



  def controlActor() = Action.async {
    implicit val timeout: Timeout = 5.seconds

    (demoActors ? updateDemo()).mapTo[List[List[Double]]].map {
      message => Ok( Json.obj("data" -> message) )
    }
  }

}
