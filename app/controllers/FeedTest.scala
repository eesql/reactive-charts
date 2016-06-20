package controllers


import play.api.mvc._
import akka.actor._
import javax.inject._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import play.api.libs.iteratee._
import actors.demoActor
import play.api.libs.json.{JsObject, Json}


/**
  * Created by chad on 6/19/2016.
  */

@Singleton
class FeedTest @Inject() (system: ActorSystem) extends Controller {

  val demoActors = system.actorOf(demoActor.props)

  val dataToJson = Enumeratee.map[List[List[Double]]] {
    case (d) => Json.obj("data" -> d)
  }

  def controlActor() = Action {

    val notifications: Enumerator[List[List[Double]]] = demoActor.notifications
    val fNotifications =
      notifications &> dataToJson

    Ok.chunked(fNotifications).as(EVENT_STREAM)
  }

}
