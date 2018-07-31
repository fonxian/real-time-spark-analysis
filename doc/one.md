

## 一、预处理数据

#### 1、Scala读取csv

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

#### 2、将记录保存为json

```scala


```



#### 3、异常记录

(1) No serializer found for class scala.runtime.BoxedUnit and no properties discovered to create BeanSerializer

```scala

map(mapper.writeValueAsString(_))

```

改为

```scala

map(x => mapper.writeValueAsString(_))

```


