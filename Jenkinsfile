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

        stage('Upload and Deploy Backend to EC2') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'ec2-ssh-testkey', keyFileVariable: 'PK')]) {
                    bat 'icacls "%PK%" /inheritance:r'
                    bat 'icacls "%PK%" /grant:r "SYSTEM:R"'
                    
                    //uploading the backend to ec2 as 'backend.jar'
                    bat 'scp -i "%PK%" -o StrictHostKeyChecking=no backend\\target\\backend-0.0.1-SNAPSHOT.jar ec2-user@13.238.201.139:/home/ec2-user/backend-new.jar'

                    //stop old backend
                    //TODO figureout why this causes a syntax error but still works...
                    bat 'ssh -i "%PK%" -o StrictHostKeyChecking=no ec2-user@13.238.201.139 "ps -ef | grep \'[j]ava -jar /home/ec2-user/backend.jar\' | awk \'{print \\$2}\' | xargs -r kill"'

                    //replace the old jar file
                    bat 'ssh -i "%PK%" -o StrictHostKeyChecking=no ec2-user@13.238.201.139 "rm -f /home/ec2-user/backend.jar && mv /home/ec2-user/backend-new.jar /home/ec2-user/backend.jar"'

                    //run the new jar file
                    bat 'ssh -i "%PK%" -o StrictHostKeyChecking=no ec2-user@13.238.201.139 "nohup java -jar /home/ec2-user/backend.jar > /home/ec2-user/app.log 2>&1 < /dev/null &"'
                }
            }
        }

        stage('Upload and Deploy Frontend to s3 DRYRUN') {
            steps {
                dir('frontend') {
                    bat 'aws s3 sync dist s3://fayyadh-frontendfor-jenkinslearningapp --delete --dryrun --profile frontend-deploy-role'
                }
            }
        }
    }
}