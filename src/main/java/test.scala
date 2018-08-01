/**
  * Created by fangzhijie on 2018/7/17.
  */
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object test {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("MyApp")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/Users/fangzhijie/README.md")
    val rdd4 = lines.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_).collect
    rdd4.take(10).foreach(print)
  }

}
