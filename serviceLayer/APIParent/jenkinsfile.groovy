pipeline{
    agent any
    stages{
        stage('check change'){

            when {changeset "APIParent"}
            steps{
                sh "echo I will build now"
            }
        }
    }
}