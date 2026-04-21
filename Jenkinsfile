pipeline{
    agent any

    tools {
        jdk 'jdk21'
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

        stage('Test SSH to EC2') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'ec2-ssh-testkey', keyFileVariable: 'PK')]) {
                    bat 'ssh -i "%PK%" -o StrictHostKeyChecking=no ec2-user@13.238.201.139 "hostname"'
                }
            }
        }

        stage('Simulate Deploy') {
            steps {
                echo 'Pretending to deploy the backend and frontend...'
            }
        }
    }
}