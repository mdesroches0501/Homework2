import org.apache.spark.sql.SparkSession
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

object MostPopularUsers {
    // Main Method
    def main(args: Array[String]) {
        val tweetsData = args(0)
        val userData = args(1)
        val outputData = args(2)

        val conf = new SparkConf().setAppName("TopTenMentions")
        // Create spark context
        val sc = new SparkContext(conf)
        // Load input data
        val tweets = sc.textFile(tweetsData)
        val users = sc.textFile(userData)

        // filter out needed data, split and give a value, then count  
        val tweets1 = tweets.filter(line => line.contains("2009-09-16") ||  line.contains("2009-09-17") || line.contains("2009-09-18") || line.contains("2009-09-19") || line.contains("2009-09-20"))
        val tweets2 = tweets1.map(x => (x.split("\t")(0), 1))
        val tweets3 = tweets2.reduceByKey((x, y) => x + y)
        //filter, split, count 
        val users1 = users.filter(line => line.contains("LA") ||  line.contains("Los Angels"))
        val users2 = users1.map(x => (x.split("\t")(0), 1))
        val users3 = users2.reduceByKey((x, y) => x + y)

        // join the two RDD's and map the accordingly 
        val joined = tweets3.join(users3)
        val joined2 = joined.map(item => (item._2._1, item._1))
        
        // sort top ten
        val joined3 = joined2.sortByKey(false).take(10)

        //save to output
        sc.parallelize(joined3).saveAsTextFile(outputData)

    }
}
