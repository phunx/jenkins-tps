pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        deleteDir()
        checkout scm
      }
    }
    stage('Tests') {

      parallel { 
    
        stage('Unit Test') {
          agent { label 'master'}
          steps {
            echo "Running the unit test..."
            sh "sleep 10"
          }
        }

        stage('Integration test') {
          steps {
            echo "Running the integration test..."
            sh "sleep 10"
          }
        }
      } // end parallel
    } // end stage

    stage('Validation manuelle') {
      steps {
        input('Do you want to proceed?')
        echo "Branch name $GIT_BRANCH"
      }
    }
    stage('Check branch') {
      when {
        not {
          branch "master"
        }
      }
      steps {
        echo "Condition validé $params.BRANCH_NAME => exécution du code"
      }
    }
  } // stages

  post {
    always {
        echo 'Always todo'
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


} // end pipeline
