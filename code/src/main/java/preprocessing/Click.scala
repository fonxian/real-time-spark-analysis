package preprocessing


/**
  * Created by fangzhijie on 2018/7/31.
  */
class Click(val fromc: String, val titlec: String, val numc: Int, val toc: String) {


  var from: String = fromc
  var to: String = toc
  var num: Int = numc
  var title: String = titlec

  def getFrom(): String = {
    return this.from;
  }

  def getTo(): String = {
    return this.to;
  }

  def getNum(): Int = {
    return this.num;
  }

  def getTitle(): String = {
    return this.title;
  }


}
