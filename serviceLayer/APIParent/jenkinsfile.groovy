def printParameters(){
    println("")
    println("*** Input Parameters ***")
    println("================================")
    println("project                                 - ${project}")
}

def buildNightlyBuild(){
    build job: "orc", propagate: true, wait: true,
            parameters: [
                    string(name: 'project', value: "${params.project}"),
            ]
}


pipeline{
    agent any

    parameters {
        string(name:'project', defaultValue:'serviceLayer')
    }
    stages{
        stage('check change'){
            //when {changeset "*/APIParent/**"}
            steps{
                sh "echo I will build now"
                script {

                    try {
                        printParameters()
                    } catch (error) {
                        echo error.message
                        echo "IGNORE: Failure"
                    } finally {
                    }

                }
                /*build (job:'orc',parameters: [
                        [string(name: 'PROJECT', value: ${params.PROJECT})]
                ])*/
            }
        }

        stage('change set'){
            when {changeset "*/APIParent/**"}
            steps{
                script{
                    buildNightlyBuild()
                }
            }

        }
    }
}