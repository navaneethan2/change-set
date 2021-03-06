def JOB_NAME = "${env.JOB_BASE_NAME}"
pipeline {

    agent any

    parameters {
        booleanParam(name:'Build_All',defaultValue: false)

    }

    stages {

        stage('debug') {

            steps {
                script {
                    println "${params.ParamA}"
                    println "${JOB_NAME}"
                }
            }
        }
        stage('changeset') {
            when {
                allOf {
                    changeset "/${JOB_NAME}/**"
                    expression { params.ParamA == 'Orchestration'}
                }}
                steps {
                    script {
                        sh "echo i will build now, and i have a latest commit in ${params.API_to_build}"
                        sh "cd ${env.WORKSPACE} && cd API/${params.API_to_build}/ && ls"
                    }

                }
            }
        stage("Downstream") {
            steps{
                script{
                    if (expression{params.Build_All == true}){
                        build job:'CustomerAPI', parameters: [(string(name:'Build_All',value: "${params.Build_All}"))] }
                    else(expression{params.ParamA == "Orchestration"}){
                        build job:'CustomerAPI', parameters: [(string(name:'ParamA',value: "${params.ParamA}"))] }
                    }

                }
            }
        }
        }
