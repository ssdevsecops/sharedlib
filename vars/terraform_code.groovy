def call(){

    pipeline {
    agent any
    tools {
        terraform 'terraform07'
    } 
    stages {
        stage('init') {
            steps {
                sh 'terraform init'
            }
        }
        stage('Plan') {
            steps {
                withAWS(credentials: 'awskeys', region: 'usveast-1') {
                    sh "terraform plan --var=dev.tfvars"
                }
                
            }
        }

        stage('Apply') {
            steps {
                withAWS(credentials: 'awskeys', region: 'usveast-1') {
                    sh "terraform apply --var=dev.tfvars"
                }
            }
        }

    }
}

}