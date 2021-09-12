def getListModules(string modulesFile){

    return sh(script: "cat modulesFile", returnStdout: true)
}