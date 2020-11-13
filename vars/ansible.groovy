def call(){

    pipeline {
    agent any
    
    stages {
        stage('Lynt') {
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

    }
}

}