def paramAValue = "Orchestration"


pipeline {

    agent any

    stages {

        stage('pass parameter') {

            steps {
                script {

                    def API_modules = ['AlertsAPI', 'CustomerAPI']

                    for (int i = 0; i < API_modules.size(); ++i) {
                        echo "${API_modules[i]}"
                        echo "build job: 'test1',parameters: [ string:(name: 'ParamA', value: paramAValue),(string(name: 'API_to_build', value: '${API_modules[i]}'))]"
                        build job: 'test1',parameters: [ [$class: 'StringParameterValue',name: 'ParamA', value: paramAValue],(string(name: 'API_to_build', value: '${API_modules[i]}'))]

                    }


                }
            }
        }

    }
}