def paramAValue = "Orchestration"
def API_to_build = "CustomerAPI"

pipeline{

    agent any
    /* parameters{
        choice(name:'API_to_build',choices:['AlertsAPI','CustomerAPI'])
    }*/
    stages{
        stage('pass parameter') {

            steps{
                script{
                    def modulesFile = "modules.json"
                    def modulesList = modules.getListModules(modulesFile)
                    println modulesList
                }
            }

            /*steps{

                build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                                 (string(name: 'API_to_build', value: API_to_build ))]

            }*/
        }
    }
}