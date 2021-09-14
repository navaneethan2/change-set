def paramAValue = "Orchestration"


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

        stage('testing'){

            steps{
                build job: 'test1', parameters: [string(name: 'ParamA', value: 'paramAValue'),string(name:'API_to_build',value:'AlertsAPI')], quietPeriod: 2, wait: false
                build job: 'test1', parameters: [string(name: 'ParamA', value: 'paramAValue'),string(name:'API_to_build',value:'CustomerAPI')], quietPeriod: 2, wait: false

            }

        }
        /*stage('pass parameter') {

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

        /*stage('testing') {
            def branches = [:]

            for(i = 0; i < params.size(); i += 1) {
                def param = params[i]

                branches["Test${i}"] = {
                    build job: 'Test', parameters: [string(name: 'Name', value: param)], quietPeriod: 2
                }
            }
            parallel branches
        }*/
        }
    }
