pipeline{
    agent any
    stages{
        stage('check change'){
            when {changeser "APIParent"}
            steps{
                sh "echo I will build now"
            }
        }
    }
}