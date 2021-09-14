@NonCPS
def echo_all(moduleName) {
    moduleName.each { item ->

        echo "Hello ${item}"
        echo "build job: 'test1',parameters: [ string(name: 'ParamA', value: paramAValue),(string(name: 'orch_Api', value: ${item}))]"
        buildJob(${item})
    }
}

def buildJob(moduleName){
    def paramAValue = "Orchestration"
    build job: 'test1',parameters: [ [$class: 'StringParameterValue',name: 'ParamA', value: paramAValue],(string(name: 'orch_Api', value: ${item}))]
}



pipeline {

    agent any

    stages {

        stage('pass parameter') {

            steps {
                script {

                    def API_modules = ['AlertsAPI', 'CustomerAPI']
                    echo_all(API_modules)

                    /*for (int i = 0; i < API_modules.size(); ++i) {
                        echo "${API_modules[i]}"
                        echo "build job: 'test1',parameters: [ string:(name: 'ParamA', value: paramAValue),(string(name: 'API_to_build', value: '${API_modules[i]}'))]"
                        buildJob("${API_modules[i]}")
                    }*/


                }
            }
        }

    }
}