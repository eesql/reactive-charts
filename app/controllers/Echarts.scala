package controllers

import play.api.libs.iteratee.{Enumerator, Iteratee}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.duration._
import javax.inject._
import akka.pattern.ask
import akka.actor._
import actors._

/**
  * Created by elainetuang on 6/16/16.
  */
class Echarts extends Controller{
  def demo = Action {
    Ok( views.html.demo.render() )
  }

  def maleData = Action {

    val dataList:List[List[Double]] = List(List(174.0, 65.6), List(175.3, 71.8), List(193.5, 80.7), List(186.5, 72.6), List(187.2, 78.8),
    List(181.5, 74.8), List(184.0, 86.4), List(184.5, 78.4), List(175.0, 62.0), List(184.0, 81.6),
    List(180.0, 76.6), List(177.8, 83.6), List(192.0, 90.0), List(176.0, 74.6), List(174.0, 71.0),
    List(184.0, 79.6), List(192.7, 93.8), List(171.5, 70.0), List(173.0, 72.4), List(176.0, 85.9),
    List(176.0, 78.8), List(180.5, 77.8), List(172.7, 66.2), List(176.0, 86.4), List(173.5, 81.8),
    List(178.0, 89.6), List(180.3, 82.8), List(180.3, 76.4), List(164.5, 63.2), List(173.0, 60.9),
    List(183.5, 74.8), List(175.5, 70.0), List(188.0, 72.4), List(189.2, 84.1), List(172.8, 69.1),
    List(170.0, 59.5), List(182.0, 67.2), List(170.0, 61.3), List(177.8, 68.6), List(184.2, 80.1),
    List(186.7, 87.8), List(171.4, 84.7), List(172.7, 73.4), List(175.3, 72.1), List(180.3, 82.6),
    List(182.9, 88.7), List(188.0, 84.1), List(177.2, 94.1), List(172.1, 74.9), List(167.0, 59.1),
    List(169.5, 75.6), List(174.0, 86.2), List(172.7, 75.3), List(182.2, 87.1), List(164.1, 55.2),
    List(163.0, 57.0), List(171.5, 61.4), List(184.2, 76.8), List(174.0, 86.8), List(174.0, 72.2),
    List(177.0, 71.6), List(186.0, 84.8), List(167.0, 68.2), List(171.8, 66.1), List(182.0, 72.0),
    List(167.0, 64.6), List(177.8, 74.8), List(164.5, 70.0), List(192.0, 101.6), List(175.5, 63.2),
    List(171.2, 79.1), List(181.6, 78.9), List(167.4, 67.7), List(181.1, 66.0), List(177.0, 68.2),
    List(174.5, 63.9), List(177.5, 72.0), List(170.5, 56.8), List(182.4, 74.5), List(197.1, 90.9),
    List(180.1, 93.0), List(175.5, 80.9), List(180.6, 72.7), List(184.4, 68.0), List(175.5, 70.9),
    List(180.6, 72.5), List(177.0, 72.5), List(177.1, 83.4), List(181.6, 75.5), List(176.5, 73.0),
    List(175.0, 70.2), List(174.0, 73.4), List(165.1, 70.5), List(177.0, 68.9), List(192.0, 102.3),
    List(176.5, 68.4), List(169.4, 65.9), List(182.1, 75.7), List(179.8, 84.5), List(175.3, 87.7),
    List(184.9, 86.4), List(177.3, 73.2), List(167.4, 53.9), List(178.1, 72.0), List(168.9, 55.5),
    List(157.2, 58.4), List(180.3, 83.2), List(170.2, 72.7), List(177.8, 64.1), List(172.7, 72.3),
    List(165.1, 65.0), List(186.7, 86.4), List(165.1, 65.0), List(174.0, 88.6), List(175.3, 84.1),
    List(185.4, 66.8), List(177.8, 75.5), List(180.3, 93.2), List(180.3, 82.7), List(177.8, 58.0),
    List(177.8, 79.5), List(177.8, 78.6), List(177.8, 71.8), List(177.8, 116.4), List(163.8, 72.2),
    List(188.0, 83.6), List(198.1, 85.5), List(175.3, 90.9), List(166.4, 85.9), List(190.5, 89.1),
    List(166.4, 75.0), List(177.8, 77.7), List(179.7, 86.4), List(172.7, 90.9), List(190.5, 73.6),
    List(185.4, 76.4), List(168.9, 69.1), List(167.6, 84.5), List(175.3, 64.5), List(170.2, 69.1),
    List(190.5, 108.6), List(177.8, 86.4), List(190.5, 80.9), List(177.8, 87.7), List(184.2, 94.5),
    List(176.5, 80.2), List(177.8, 72.0), List(180.3, 71.4), List(171.4, 72.7), List(172.7, 84.1),
    List(172.7, 76.8), List(177.8, 63.6), List(177.8, 80.9), List(182.9, 80.9), List(170.2, 85.5),
    List(167.6, 68.6), List(175.3, 67.7), List(165.1, 66.4), List(185.4, 102.3), List(181.6, 70.5),
    List(172.7, 95.9), List(190.5, 84.1), List(179.1, 87.3), List(175.3, 71.8), List(170.2, 65.9),
    List(193.0, 95.9), List(171.4, 91.4), List(177.8, 81.8), List(177.8, 96.8), List(167.6, 69.1),
    List(167.6, 82.7), List(180.3, 75.5), List(182.9, 79.5), List(176.5, 73.6), List(186.7, 91.8),
    List(188.0, 84.1), List(188.0, 85.9), List(177.8, 81.8), List(174.0, 82.5), List(177.8, 80.5),
    List(171.4, 70.0), List(185.4, 81.8), List(185.4, 84.1), List(188.0, 90.5), List(188.0, 91.4),
    List(182.9, 89.1), List(176.5, 85.0), List(175.3, 69.1), List(175.3, 73.6), List(188.0, 80.5),
    List(188.0, 82.7), List(175.3, 86.4), List(170.5, 67.7), List(179.1, 92.7), List(177.8, 93.6),
    List(175.3, 70.9), List(182.9, 75.0), List(170.8, 93.2), List(188.0, 93.2), List(180.3, 77.7),
    List(177.8, 61.4), List(185.4, 94.1), List(168.9, 75.0), List(185.4, 83.6), List(180.3, 85.5),
    List(174.0, 73.9), List(167.6, 66.8), List(182.9, 87.3), List(160.0, 72.3), List(180.3, 88.6),
    List(167.6, 75.5), List(186.7, 101.4), List(175.3, 91.1), List(175.3, 67.3), List(175.9, 77.7),
    List(175.3, 81.8), List(179.1, 75.5), List(181.6, 84.5), List(177.8, 76.6), List(182.9, 85.0),
    List(177.8, 102.5), List(184.2, 77.3), List(179.1, 71.8), List(176.5, 87.9), List(188.0, 94.3),
    List(174.0, 70.9), List(167.6, 64.5), List(170.2, 77.3), List(167.6, 72.3), List(188.0, 87.3),
    List(174.0, 80.0), List(176.5, 82.3), List(180.3, 73.6), List(167.6, 74.1), List(188.0, 85.9),
    List(180.3, 73.2), List(167.6, 76.3), List(183.0, 65.9), List(183.0, 90.9), List(179.1, 89.1),
    List(170.2, 62.3), List(177.8, 82.7), List(179.1, 79.1), List(190.5, 98.2), List(177.8, 84.1),
    List(180.3, 83.2), List(180.3, 83.2)
    )

    Ok( Json.obj("data" -> dataList ) )

    //NotImplemented
  }

  def testData = Action {
    Ok(Json.obj("data" -> List(List(180,82),List(190,90))))
  }

}
