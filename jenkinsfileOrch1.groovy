def paramAValue = "Orchestration"
def API_to_build = "CustomerAPI"

API_modules = ['AlertsAPI','CustomerAPI']

@NonCPS
def echo_all(list){
    list.each { item ->
        echo "Hello ${item}"
        build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                         (string(name: 'API_to_build', value: ${item} ))]

    }
}

pipeline{

    agent any
    /* parameters{
        choice(name:'API_to_build',choices:['AlertsAPI','CustomerAPI'])
    }*/
    stages{

        stage('LOAD') {
            steps {
                script {

                    repos = load 'scripts/modules.groovy'

                }
            }
        }
        stage('pass parameter') {

            steps{
                script{
                    //def modulesFile = "modules.json"

                    //def modulesList = repos.getListModules(modulesFile)
                    echo_all(API_modules)



                }
            }

            /*steps{

                build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                                 (string(name: 'API_to_build', value: API_to_build ))]

            }*/
        }
    }
}