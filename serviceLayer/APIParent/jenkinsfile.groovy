pipeline{
    agent any
    stages{
        stage('check change'){
            when {changeset "*/APIParent/**"}
            steps{
                sh "echo I will build now"
                build (job:'orc',parameters: [
                        [$class: 'StringParameterValue', name: 'PROJECT', value: ${env.PROJECT}],
                        [$class: 'StringParameterValue', name: 'JOB_BASE_NAME', value: ${env.JOB_BASE_NAME}]
                ])
            }
        }
    }
}