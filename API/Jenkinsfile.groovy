
def boolean CONTINUE = true
def boolean ORCHESTRATION = (params.ORCHESTRATION != NULL)
def string DOWNSTREAMJOB = (env.DOWNSTREAM != NULL)

def JOB_NAME = "${env.JOB_BASE_NAME}"

pipeline{
    agent any

    stages{
        stage('get parameter'){

            println ORCHESTRATION

            stages{
                stage('get params'){
                    when{
                        not{
                            changeset "${JOB_NAME}/**"
                        }
                    }

                    steps{
                        script{
                            CONTINUE = false
                            sh "echo i am skipping as no change is in folder ${JOB_NAME}"
                        }
                    }
                }

                stage('Build'){
                    when{expression {CONTINUE}}
                    steps{
                        script{
                            sh "echo I am build now as , ${CONTINUE}"
                        }
                    }
                }

                stage('Downstrea_build'){
                    when{expression{CONTINUE}}
                    steps{
                        script{
                            if ("${DOWNSTREAMJOB}" == 'null'){
                                echo "No Downstream Job"
                            }
                            else{
                                build job: "${DOWNSTREAMJOB}", parameters: [string(params.ORCHESTRATION)]
                            }
                        }
                    }

                }
            }
        }
    }
}