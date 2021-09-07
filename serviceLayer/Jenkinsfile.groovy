pipeline{
    agent any
    stages{
        stage('check change'){
            steps{
                sh "echo I will build now"
            }
        }
    }
}