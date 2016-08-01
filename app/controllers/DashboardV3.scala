package controllers

import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by elainetuang on 6/16/16.
  */
class DashboardV3 extends Controller{
  def index = Action {
    Ok( views.html.index2.render() )
  }

  def maleData = Action {

    NotImplemented
  }

  def testData = Action {
    Ok(Json.obj("data" -> List(List(180,82),List(190,90))))
  }

}
