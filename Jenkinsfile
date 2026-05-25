pipeline {
  agent any

  stages {
    stage('Checkout Source') {
      steps {
        checkout scm
      }
    }
    stage('NPM Install') {
      steps {
        sh 'npm ci'
      }
    }
    stage('Run Command') {
      steps {
        sh 'this is coming from the builder... hello world...'
      }
    }
  }
}
