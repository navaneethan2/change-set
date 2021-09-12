def getListModules(String modulesFile){

    return sh(script: "cat modulesFile", returnStdout: true)
}