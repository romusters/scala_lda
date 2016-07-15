sbt package
spark-submit --class "SimpleApp" --master yarn --deploy-mode cluster --num-executors 89 /home/cluster/IdeaProjects/lda_scala/target/scala-2.10/simple-project_2.10-1.0.jar
