def call(){

    pipeline {
    agent any
    
    stages {
        stage('ansible_lynt') {
            steps {
                sh "ansible-playbook apply_role.yml --syntax-check"
            }
        }

        stage('Ansible_Apply') {
            steps {
                sshagent(['ec2-user']) {
                    sh "ansible-playbook apply_role.yml"
                }
            }
        }

        stage('test') {
            steps {
                sh "echo ansible run complete"
            }
        }

    }
}

}