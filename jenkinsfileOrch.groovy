def paramAValue = "Orchestration"
def API_to_build = "CustomerAPI"

pipeline{

    agent any

    stages{
        stage('pass parameter') {

            steps{

                build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                                 (string(name: 'API_to_build', value: API_to_build ))]


            }

        }
    }
}