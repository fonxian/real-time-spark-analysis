package preprocessing

import java.io.{IOException, StringReader}

import au.com.bytecode.opencsv.CSVReader
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by fangzhijie on 2018/7/31.
  */

//case class Click(val fromc:String,val title:String,val num:Int,val toc:String)

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

    })

      .map( x =>{
//      val c = new Click1("1212","dfff",2323,"dfff")
      val mapper = new ObjectMapper()
////      val z = List(1,2,3,4)
//      val s = x.asInstanceOf[Click1]
//      val y = mapper.writeValueAsString(s)
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(Include.ALWAYS);
      val y = mapper.writeValueAsString(x)
    }
    )
//      .saveAsTextFile("json123")


      .saveAsTextFile("/Users/fangzhijie/Github/dgjq项目/scalaspark/data/json123")

  }

}
