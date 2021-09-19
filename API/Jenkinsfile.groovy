
def boolean BUILD_ALL = true
def boolean ORCHESTRATION = (params.ORCHESTRATION != null)
//def String DOWNSTREAMJOB = "CustomerAPI"

def JOB_NAME = "${env.JOB_BASE_NAME}"

pipeline {
    agent any

    stages {
        stage('get params') {
            when {
                not {
                    changeset "${JOB_NAME}/**"
                }
            }

            steps {
                script {
                    if (ORCHESTRATION) {
                        BUILD_ALL = false
                    }
                    sh "echo ORCHESTRATION"
                    sh "echo i am skipping as no change is in folder ${JOB_NAME}"
                }
            }
        }


            stage('Build') {
                when { expression { BUILD_ALL } }
                steps {
                    script {
                        sh "echo I am build now as ${JOB_NAME} "
                    }
                }
            }

            /*stage('Downstream_build') {
                steps {
                    script {
                        if ("${DOWNSTREAMJOB}" == 'null') {
                            echo "No Downstream Job"
                        } else {
                            echo " ${DOWNSTREAMJOB}"
                            build job: "${DOWNSTREAMJOB}", parameters: [string(name: 'ORCHESTRATION', value: "1")]
                        }
                    }
                }

            }*/
        }
    }

