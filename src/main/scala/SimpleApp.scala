/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.clustering.{LDA, DistributedLDAModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SaveMode



object SimpleApp {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val path = "/user/rmusters/ldaModel2"
    val model = DistributedLDAModel.load(sc, path)
    val matrix = model.topicDistributions
    val df = matrix.toDF()
    df.write.mode(SaveMode.Overwrite).parquet("/user/rmusters/lda_doc_topic")
  }
}