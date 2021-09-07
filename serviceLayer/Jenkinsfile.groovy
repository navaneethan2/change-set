//PROJECT = "${env.PROJECT}"

pipeline{
    agent any


    stages{
        stage('check change'){
            steps{
                script{
                    println("project - ${env.PROJECT}")
                    //println("project- ${PROJECT}")
                }

            }
        }
    }
}