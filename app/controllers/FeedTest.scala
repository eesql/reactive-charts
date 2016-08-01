package controllers


import java.util.Random

import play.api.mvc._


import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee._
import play.api.libs.json.JsValue

import play.api.libs.json.Json

import play.api.libs.EventSource


/**
  * Created by chad on 6/19/2016.
  */

//@Singleton
class FeedTest extends Controller {

  //val demoActor = system.actorOf(DemoActor.props)

  val (weightData, demoChannel) = Concurrent.broadcast[JsValue]

  val toStream = Enumeratee.map[JsValue] {
    json:JsValue  => json.as[String]
  }

  def controlActor() = Action {
    Ok

  }

  def clickActor() = Action {
    val randomData =
      List(new Random().nextDouble()*100, new Random().nextDouble()*10)
    demoChannel.push(Json.obj("data" -> randomData).as[JsValue])
    println("data")
    Ok(Json.obj("data" -> randomData))
  }

  def streamActor() = Action {
    Ok.feed(weightData  &> EventSource()).as(
      "text/event-stream").withHeaders(("Cache-Control","no-cache"))

  }


  def dataTest() = Action {
    Ok(Json.obj("userLabelId" -> 20))
  }


  def feedDataTest() = Action {
    Ok(Json.obj(
      "title" -> "地图测试",
      "subtext" -> "畅途网",
      "legend_data" -> List("2016"),
      "map_data" -> List(List( Json.obj("name" -> "南京", "value" -> List(118.78,32.07,1000.0)) ))
    ))
  }
}

//http://www.jdon.com/45515
