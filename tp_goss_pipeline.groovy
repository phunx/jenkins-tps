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
      steps {
        withCredentials([file(credentialsId: 'GOSS_FILE', variable: 'gossFile')]) {
          sh 'goss --gossfile $gossFile validate --format tap 2>&1 > goss_test.txt'
        }
      }
    }
  }

  post {
    always {
      archiveArtifacts 'goss_test.txt'
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
