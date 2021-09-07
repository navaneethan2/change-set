PROJECT = "${env.PROJECT}"

pipeline{
    agent any


    stages{
        stage('check change'){
            steps{
                script{
                    println("project -  ${PROJECT}")
                    //println("project- ${PROJECT}")
                }

            }
        }
    }
}