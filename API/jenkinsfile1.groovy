pipeline{

    agent any

    parameters{

        choice(name:'API_to_build',choices: ['AlertsAPI','CustomerAPI'])
    }

    stages{

        stage('debug'){
            steps{
                script{
                    println "${params.ParamA}"
                    println "${params.API_to_build}"
                }
            }
        }
        stage('changeset'){
            when {
                changeset "*/${params.API_to_build}/**"
                //expression { params.ParamA == 'Orchestration' }
            }
                steps{
                    script{
                        sh "echo i will build now, and i have a latest commit in ${params.API_to_build}"
                        sh "cd ${env.WORKSPACE} && cd API/${params.API_to_build}/ && ls"
                    }

                }
            }
        }
    }
