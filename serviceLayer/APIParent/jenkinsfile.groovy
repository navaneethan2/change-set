def printParameters(){
    println("")
    println("*** Input Parameters ***")
    println("================================")
    println("project                                 - ${project}")
}



pipeline{
    agent any

    parameters {
        string(name:'project', defaultValue:'serviceLayer')
    }
    stages{
        stage('check change'){
            when {changeset "*/APIParent/**"}
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
    }
}