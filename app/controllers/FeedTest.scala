package controllers


import java.util.Random

import play.api.mvc._
import akka.actor._
import javax.inject._

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee._
//import actors.DemoActor._
//import actors.DemoActor
import play.api.libs.json.Json
//import akka.pattern.ask
import play.api.libs.EventSource


/**
  * Created by chad on 6/19/2016.
  */

//@Singleton
class FeedTest extends Controller {

  //val demoActor = system.actorOf(DemoActor.props)

  val (weightData, demoChannel) = Concurrent.broadcast[List[Double]]

  val dataToJson = Enumeratee.map[List[Double]] {
    case (d) => "demo-data: "+Json.obj("data" -> d).as[String]+"\n\n"
  }

  def controlActor() = Action {
    Ok

  }

  def clickActor() = Action {
    val randomData =
      List(new Random().nextDouble()*100, new Random().nextDouble()*10)
    demoChannel.push(randomData)
    println("data")
    Ok(Json.obj("data" -> randomData))
  }

  def streamActor() = Action {
    Ok.stream(weightData &> dataToJson &> EventSource()).as(
      "text/event-stream").withHeaders(("Cache-Control","no-cache"))

  }

}

//http://www.jdon.com/45515
