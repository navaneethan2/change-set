
pipeline{
    agent any


    stages{
        stage('check change'){
            steps{
                echo "project - ${env.PROJECT}"

            }
        }
    }
}