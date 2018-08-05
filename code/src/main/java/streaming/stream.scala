package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by fangzhijie on 2018/8/1.
  */
object stream {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("MyApp")
    val ssc = new StreamingContext(conf,Seconds(1))

    val lines = ssc.socketTextStream("localhost",7777)
    val errorLines = lines.filter(_.contains("error"))
    errorLines.print()

    
    ssc.start()
    ssc.awaitTermination()

  }

}
