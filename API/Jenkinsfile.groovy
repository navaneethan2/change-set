
def boolean CONTINUE = true
def boolean ORCHESTRATION = (params.ORCHESTRATION != null)
def String DOWNSTREAMJOB = (params.DOWNSTREAMJOB != null)

def JOB_NAME = "${env.JOB_BASE_NAME}"

pipeline{
    agent any

    stages{
        stage('get parameter'){

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
                            sh "echo I am build now as ${JOB_NAME} , ${CONTINUE}"
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
                                build job: "${DOWNSTREAMJOB}", parameters: [string(name: 'ORCHESTRATION',value: "1")]
                            }
                        }
                    }

                }
            }
        }
    }
}