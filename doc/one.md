

## 一、预处理数据

#### Scala读取csv

```scala
	
	val conf = new SparkConf().setMaster("local").setAppName("MyApp")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("data/preprocessed_data.csv")
    val result = lines.map {line =>
      val reader = new CSVReader(new StringReader(line));
      reader.readNext();
    }
    result.collect().foreach(x => {x.foreach( y => print(y+" "));println()})


```
