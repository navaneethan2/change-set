
pipeline{
    agent any


    stages{
        stage('check change'){
            steps{
                echo "The build number is ${env.BUILD_NUMBER}"

            }
        }
    }
}