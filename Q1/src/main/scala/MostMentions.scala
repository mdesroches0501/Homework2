import org.apache.spark.sql.SparkSession
import org.apache.spark._
import org.apache.spark.SparkContext._

object MostMentions {
    // Main Method
    def main(args: Array[String]) {
        val inputData = args(0)
        val outputData = args(1)
        val conf = new SparkConf().setAppName("TopTenTweets")
        // Create spark context
        val sc = new SparkContext(conf)
        // Load input data
        val input = sc.textFile(inputData)
        // Splitting sentences into words based on empty spaces
        val all_words = input.flatMap(x => x.split(" "))
        // collect all that start with @
        val collect_special_case = all_words.collect { case s if s.startsWith("@") => s.replaceAll("[,.;!?:]", "").toLowerCase} //part 2 of the number 1
        // map them and count them
        val mentions = collect_special_case
            .map(word => (word, 1))
            .reduceByKey((x, y) => x + y)
            .takeOrdered(10)(Ordering[Int].reverse.on(x=>x._2))

        // // sort in ascending order
        // val ascending_mentions = mentions.sortBy(_._2, false)
        // // top ten mentions
        // val top_ten_mentions = ascending_mentions.top(10)
        // //save to outputData
        sc.parallelize(mentions).saveAsTextFile(outputData)
    }
}
