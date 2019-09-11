#!groovy

pipeline {
    agent any

    tools {
        maven 'maven-3.6.1'
        jdk 'jdk8'
    }

    environment {
        PROJECT_NAME = "spring-boot-kubernetes-configmap"
        PROJECT_REPO = "https://github.com/4ebyrek/spring-boot-kubernetes-configmap-example.git"
        PROJECT_GIT_CREDENTIAL_ID = "45172769-dda8-47d2-8177-f3bf97b7958c"
        DOCKER_REGISTRY = "eldarbai/spring-boot-kubernetes-configmap"
        DOCKER_IMAGE_VERSION = "latest"
        DOCKER_REGISTRY_CREDENTIAL_ID = "162577ca-efa9-46f0-911e-8bcc64fa1635"
        KUBERNETES_POD = "example-app-pod.yaml"
    }

    stages {
        stage('********************** Clean Workspace **********************') {
            steps {
                parallel(
                        cleanWorkSpace: {
                            sh "rm -rf $WORKSPACE/$PROJECT_NAME"
                        }
                )
            }
        }

        stage('********************** Extract repos from git **********************') {
            steps {
                dir("$WORKSPACE/$PROJECT_NAME") {
                    git branch: "develop", credentialsId: "$PROJECT_GIT_CREDENTIAL_ID", url: "$PROJECT_REPO"
                }
            }
        }

//        stage('********************** clean install **********************') {
//            steps {
//                sh "mvn clean install -f $WORKSPACE/$PROJECT_NAME/pom.xml"
//            }
//        }

        stage('********************** clean install **********************') {
            steps {
                withRegistry('https://10.254.101.100','928c8a05-3036-48ed-ab5c-0db5b702ee7a'){
                    docker.build("eldarbai/spring-boot-kubernetes-configmap").push('')
                }
            }
        }

        stage('********************** build image **********************') {
            steps {
                sh "docker build -t $DOCKER_REGISTRY:$DOCKER_IMAGE_VERSION $WORKSPACE/$PROJECT_NAME"
            }
        }

        stage('********************** push image **********************') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKER_REGISTRY_CREDENTIAL_ID", url: ""]) {
                    sh "docker push $DOCKER_REGISTRY:$DOCKER_IMAGE_VERSION"
                }
            }
        }

        stage('********************** deploy image **********************') {
            steps {
                sh "kubectl apply -f $KUBERNETES_POD"
            }
        }

    }
}
