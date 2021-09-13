def paramAValue = "Orchestration"
def API_to_build = "CustomerAPI"

API_modules = ['AlertsAPI','CustomerAPI']

/*@NonCPS
def echo_all(list) {
    def tasks = [:]
    list.each { item ->
        tasks["${item}"] = { ->
            build job: 'test1', parameters: [
                    [$class: 'StringParameterValue', name: 'branch', value: "${item}"]
            ]
        }
    }
}*/

/*@NonCPS
def echo_all(list){
    list.each { item ->
        echo "API Module - ${item}"
        def API_to_build =  "${item}"
            println API_to_build
                build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: 'Orchestration'],
                                                 (string(name: 'API_to_build', value: "${item}" ))]





    }
}*/

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

                    def API_modules = ['AlertsAPI','CustomerAPI']

                    for (int i = 0; i < API_modules.size(); ++i){
                        echo "${API_modules[i]}"
                        build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                                         (string(name: 'API_to_build', value: "${API_modules[i]}" ))]

                    }


                }
            }

            /*steps{

                build job: 'test1', parameters: [[$class: 'StringParameterValue', name: 'ParamA', value: paramAValue],
                                                 (string(name: 'API_to_build', value: API_to_build ))]

            }*/
        }
    }
}