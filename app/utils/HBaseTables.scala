package utils

import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.{Put, Scan}
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.filter.{BinaryComparator, RowFilter, SubstringComparator}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes

import scala.collection.JavaConverters._

/**
  * Created by elainetuang on 7/28/16.
  */
object HBaseTables {
  val testTable = TableName.valueOf("test_realtime")

  def convertTestOrder(triple: (String, Int)) = {
    val p = new Put(Bytes.toBytes(triple._1))
    p.addColumn(Bytes.toBytes("p"),Bytes.toBytes("order"),Bytes.toBytes(triple._2.toString))
    (new ImmutableBytesWritable, p)
  }


  def getHDailyOrders(today:String):List[List[String]] = {

    //setup filter
    val rowFilter = new RowFilter(CompareOp.EQUAL,
      new BinaryComparator(Bytes.toBytes(today)))

    getRealtimeOrders(rowFilter)
  }


  /** 查询分钟级别渠道订单指标 **/
  def getHMinOrders(today:String):List[List[String]] = {

    //setup filter
    val rowFilter = new RowFilter(CompareOp.EQUAL,
      new SubstringComparator(today+" "))

    getRealtimeOrders(rowFilter)
  }


  /** 根据不同filter查询test_realtime订单数据 */
  def getRealtimeOrders( filter:RowFilter):List[List[String]] = {

    val table = HBaseConfig.conn.getTable(HBaseTables.testTable)

    //scan data
    val scan = new Scan()
    scan.addColumn("p".getBytes, "order".getBytes)

    scan.setFilter(filter)

    val scanner = table.getScanner(scan)

    try {

      val results = scanner.iterator().asScala.map( r =>
        List(Bytes.toString(r.getRow()),
          Bytes.toString( r.getValue("p".getBytes, "order".getBytes)
          ))
      )

      //未找到对应ROW KEY时返回空数据标识
      if (results.isEmpty) { List(List("NaN", "NaN")) } else { results.toList }
    }
    finally {
      scanner.close()
    }

  }



}
