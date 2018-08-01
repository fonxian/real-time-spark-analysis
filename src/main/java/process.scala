import java.io.{IOException, StringReader}

import au.com.bytecode.opencsv.CSVReader
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.spark.{SparkConf, SparkContext}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import scala.util.parsing.json._


/**
  * Created by fangzhijie on 2018/7/31.
  */

case class Click(val fromc:String,val title:String,val num:Int,val toc:String)

object process {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("MyApp")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/Users/fangzhijie/Github/dgjq项目/pythonspark/Real-Time-Clickstream-Analysis-Platform/data/preprocessed_data.csv")
    val result = lines.map { line =>
      val reader = new CSVReader(new StringReader(line));
      reader.readNext();
    }



    result.map(x => {
      try {
        val click = new Click(x(0), x(1), Integer.parseInt(x(2)), x(3))
        click
      } catch {
        case ex: IOException => {
          println("IO Exception")
        }
        case ex: Exception => {
          println("Exception")
        }
      }

    }).map( x =>{
      val mapper = new ObjectMapper()
      mapper.writeValueAsString(_)
    }
    ).saveAsTextFile("/Users/fangzhijie/Github/dgjq项目/scalaspark/data/json123")

  }

}
