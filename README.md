_________________________________________________________________________________________________________________________________________________
------------------------------------------------------------------------README-------------------------------------------------------------------

|Q1|
In Q1 directory type following commands
sbt package
spark-submit --class "MostMentions" --master local[4] target/scala-2.12/most-mentioned_2.12-1.0.jar InputFile OutputFile 

InputFile = location of which the input file you wish to test is
OutputFile = location of which you wish to send the output file 
##
|Q2|
sbt package
spark-submit --class "MostPopularUsers" --master local[4] target/scala-2.12/most-popular_2.12-1.0.jar TweetsInputFile UserInputFile OutputFile

TweetsInputFile = location of tweet.txt input file
UserInputFile = location of user.txt input file
OutputFile = location of where desired location of output 

|NOTES|
copy of output files are located in hdfs directry under mdesroches username. There is also an output directory in the server in the 
corresponding folder to where I have made another copy extension .txt 
_________________________________________________________________________________________________________________________________________________
