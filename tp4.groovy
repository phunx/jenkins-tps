node {
  stage("Afficher les variables d'environnement de l'instance Jenkins") {
    sh "env"
  }

  stage("Afficher des variables propres au job courant") {
    print JOB_NAME
          BUILD_NUMBER
          WORKSPACE
  }

  stage("Récupérer credentials") {
    withCredentials([
      usernamePassword(
        credentialsId: params.SECRET_ID,
        usernameVariable: "USR",
        passwordVariable: "PWS"
      )
    ]) {
      print USR
    }
  }
}
