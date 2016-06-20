package controllers


import play.api.mvc._
import akka.actor._
import javax.inject._

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee._
import actors.DemoActor._
import actors.DemoActor
import play.api.libs.json.Json
import akka.pattern.ask


/**
  * Created by chad on 6/19/2016.
  */

@Singleton
class FeedTest @Inject() (system: ActorSystem) extends Controller {

  val demoActor = system.actorOf(DemoActor.props)



  val dataToJson = Enumeratee.map[List[List[Double]]] {
    case (d) => Json.obj("data" -> d)
  }

  def controlActor() = Action.async {
    (demoActor ? TestActor()).mapTo[List[List[Double]]].map { message =>
      Ok(Json.obj("data" -> message))
    }

  }



}
