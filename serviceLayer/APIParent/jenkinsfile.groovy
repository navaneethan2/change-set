pipeline{
    agent any

    parameters {
        string(name:'project', defaultValue:'serviceLayer')
    }
    stages{
        stage('check change'){
            when {changeset "*/APIParent/**"}
            steps{
                sh "echo I will build now"
                build (job:'orc',parameters: [
                        [$class: 'StringParameterValue', name: 'PROJECT', value: ${params.PROJECT}]
                ])
            }
        }
    }
}