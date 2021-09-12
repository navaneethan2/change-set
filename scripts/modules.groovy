def getListModules(String modulesFile){

    return sh(script: "cat ${modulesFile} | jq -r .'repositories.sublists[] | .repos[].name'", returnStdout: true)
}

return this;