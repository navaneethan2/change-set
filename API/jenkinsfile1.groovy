
pipeline {

    agent any

    parameters {
        booleanParam(name:'Build_All',defaultValue: false)

    }

    stages {

        stage('debug') {
            when{
                expression{params.Build_All == true}
            }
            steps {
                script {
                    println "${params.ParamA}"
                    println "${params.API_to_build}"
                }
            }
        }
        stage('changeset') {
            when {
                allOf {
                    changeset "*/${params.orch_Api}/**"
                    expression { params.ParamA == 'Orchestration' }
                }}
                steps {
                    script {
                        sh "echo i will build now, and i have a latest commit in ${params.API_to_build}"
                        sh "cd ${env.WORKSPACE} && cd API/${params.API_to_build}/ && ls"
                    }

                }
            }
        }
    }

