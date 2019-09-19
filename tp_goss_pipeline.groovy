pipeline {

  agent { label 'master' }

  stages {

    stage ('Clean'){
      steps {
        deleteDir()
        checkout scm
      }
    }

    stage('Run test cred'){
      // Récuperer le crédential file

      // lancer la commande
    }
  }

  post {
    always {
      // Sauvegarder le fichier de résultat
    }
    success {
        echo 'I succeeeded!'
    }
    unstable {
        echo 'I am unstable :/'
    }
    failure {
        echo 'I failed :('
    }
    changed {
        echo 'Things were different before...'
    }
  }
}