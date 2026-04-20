pipeline{
    agent any

    environment {
        JAVA_HOME = 'C:\\Users\\User\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-25.0.2.10-hotspot\\bin\\java.exe'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend'){
                    bat 'mvnw.cmd clean test package'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend'){
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'backend/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'frontend/dist/**', fingerprint: true
            }
        }

        stage('Simulate Deploy') {
            steps {
                echo 'Pretending to deploy the backend and frontend...'
            }
        }
    }
}