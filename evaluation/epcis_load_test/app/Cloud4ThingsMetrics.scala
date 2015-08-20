import sys.process._
import math._

object Cloud4ThingsMetrics {

  val statistics = Array("Average", "Minimum", "Maximum")

  def get_metric_results(metric_name: String, start_time: String, end_time: String, statistic: String,
    instanceId: String) : String = {
      "./get_ec2_metric.sh " + metric_name + " " + start_time + " " + end_time + " " + statistic + " " + instanceId !!
    }

  def parse_result(result: String, statistic: String, metric_name: String) = {
    var metric_value: Float = 0
    var splitted = result.split("\n")
    for(i <- 1 until splitted.length) {
      val data = splitted(i).split("\\s+")
      metric_value = max(metric_value, data(1).toFloat)
    }
    println(metric_name + " - " + statistic + " : " + metric_value)
  }

  def main(args: Array[String]) = {
    val metric_name = args(0)
    val start_time = args(1)
    val end_time = args(2)
    val instance_id = args(3)
    statistics.foreach { statistic =>
      var result = get_metric_results(metric_name, start_time, end_time, statistic, instance_id)
      parse_result(result, statistic, metric_name)
    }
  }
}
