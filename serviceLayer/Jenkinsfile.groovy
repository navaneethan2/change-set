pipeline{
    agent any

    parameters{
        string(name:'project', defaultValue:'serviceLayer')

    }
    stages{
        stage('check change'){
            steps{
                script{
                    //println("project- ${params.PROJECT}")
                    println("project- ${PROJECT}")
                }
                sh "echo I will build now"

            }
        }
    }
}