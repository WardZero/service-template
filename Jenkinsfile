pipeline {
    agent {
        node {
            label 'linux-worker1'
        }
    }  

    environment { 
        
        CURRENTBUILD_DISPLAYNAME = "#$BUILD_NUMBER Service_Template Deploy"
        CURRENT_BUILDDESCRIPTION = "Service_Template"
        GITHUB_URL = "https://code.fs.usda.gov"
        GITHUB_API_URL = "https://code.fs.usda.gov/api/v3/repos"
        GITHUB_TOKEN = credentials('GITHUB_TOKEN_CODEFS_USDA')
        GITHUB_CREDENTIAL = "GITHUB_CREDENTIAL"
        GITHUB_PROJECT_NAME = "FOREST-SERVICE/Service_GIS"      
        SONAR_TOKEN = credentials( "SONAR_TOKEN_SERVICE_TEMPLATE")
        SONAR_HOST = credentials("SONAR_HOST")
        SONAR_PROJECT_NAME = "Service_Template"
        SONARQUBE_URL = "https://sca.fedgovcloud.us/dashboard?id=Service_Template"
        MAILING_LIST = "Ilayaraja.Kumarasamy@usda.gov,mahfuzur.rahman@usda.gov,ward.mckonly@usda.gov,casey.pore@usda.gov,landon.laster@usda.gov,laura.radcliff@usda.gov"
        REPO_OWNER_NAME = credentials("REPO_OWNER_NAME")
        REPO_NAME = "Service_Template"
        
        INSTALL_DEPEDENCIES_STATUS = "Pending"
        RUN_UNIT_TESTS_STATUS = "Pending"
        RUN_E2E_STATUS = "Pending"
        RUN_508_STATUS = "Pending"
        DEPLOY_STATUS = "Pending"
        RUN_SECURITY_SCAN_STATUS = "Pending"

        JOB_NAME ="Service_User"
        JENKINS_URL = credentials("JENKINS_URL")

        CHECKOUT_STATUS ="Pending"
        BUILD_STATUS = "Pending"
        SECURITY_STATUS = "Pending"
    }


    options {      
        timestamps()
        disableConcurrentBuilds()
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }  

    stages {     
        stage('Checkout from SCM'){
            steps {
                script {
                    currentBuild.displayName = "${env.CURRENTBUILD_DISPLAYNAME}"
                    currentBuild.description = "${env.CURRENT_BUILDDESCRIPTION}"
                    checkout([$class: 'GitSCM', branches: [[name: "${env.GIT_BRANCH}"]], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'GIT', submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${env.GITHUB_CREDENTIAL}", url: "${env.GITHUB_URL}/${env.REPO_OWNER_NAME}/${env.REPO_NAME}.git"]]])
                    sh '''
                     curl -XPOST -H "Authorization: token $GITHUB_TOKEN" $GITHUB_API_URL/$REPO_OWNER_NAME/$REPO_NAME/statuses/$(git rev-parse HEAD) -d '{"state": "success","context":"ci/jenkins: checkout-code", "target_url": "https://jenkins.fedgovcloud.us/blue/organizations/jenkins/Service_Template_Multibranch/branches/','description": "Your tests passed on Jenkins!"}'
                    '''			
  		            CHECKOUT_STATUS= 'Success'
                }
            }

            post {
                failure {
			        script {
                        sh '''
                        curl -XPOST -H "Authorization: token $GITHUB_TOKEN" $GITHUB_API_URL/$REPO_OWNER_NAME/$REPO_NAME/statuses/$(git rev-parse HEAD) -d '{"state": "success","context":"ci/jenkins: checkout-code", "target_url": "https://jenkins.fedgovcloud.us/blue/organizations/jenkins/Service_Template_Multibranch/branches/','description": "Your tests failed on Jenkins!"}'
                        '''	
                        CHECKOUT_STATUS= 'Failed'			
        	   	        sh 'echo "FAILED in stage checkout code"'
                    }
                }	
            }
        }
	  
        stage ('run tests'){
            parallel{
                
                stage('Run_Security_Scanning'){
                    steps {
                        script {
                            sh "echo 'Security Scanning'"
                            sh '''
                            curl -XPOST -H "Authorization: token $GITHUB_TOKEN" $GITHUB_API_URL/$REPO_OWNER_NAME/$REPO_NAME/statuses/$(git rev-parse HEAD) -d '{"state": "success","context":"ci/jenkins: run-security-scanning", "target_url": "https://jenkins.fedgovcloud.us/blue/organizations/jenkins/Service_Template_Multibranch/branches/','description": "Your tests passed on Jenkins!"}'
                            '''

                            def scannerhome = tool 'SonarQubeScanner';
                            withSonarQubeEnv('SonarQube') {      
                                sh 'mvn clean compile verify' 
                              sh 'mvn sonar:sonar -Dsonar.projectKey=$SONAR_PROJECT_NAME -Dsonar.login=$SONAR_TOKEN'          
				            sh 'rm -rf sonarqubereports'
                            sh 'mkdir sonarqubereports'
  	                        sh 'sleep 5'
                            sh 'java -jar /home/Jenkins/sonar-cnes-report-3.1.0.jar -t $SONAR_TOKEN -s $SONAR_HOST -p $SONAR_PROJECT_NAME -o sonarqubereports'
                            sh 'cp sonarqubereports/*analysis-report.docx sonarqubereports/sonarqubeanalysisreport.docx'
                            sh 'cp sonarqubereports/*issues-report.xlsx sonarqubereports/sonarqubeissuesreport.xlsx' 
                            archiveArtifacts artifacts: 'sonarqubereports/sonarqubeanalysisreport.docx', fingerprint: true
                            archiveArtifacts artifacts: 'sonarqubereports/sonarqubeissuesreport.xlsx', fingerprint: true
			    
                            sh  '''
                                curl -XPOST -H "Authorization: token $GITHUB_TOKEN" $GITHUB_API_URL/$REPO_OWNER_NAME/$REPO_NAME/statuses/$(git rev-parse HEAD) -d '{"state": "success","context":"ci/jenkins: run-security-scanning", "target_url": "https://jenkins.fedgovcloud.us/blue/organizations/jenkins/Service_Template_Multibranch/branches/','description": "Your tests passed on Jenkins!"}'
                                '''			    
		                            RUN_SECURITY_SCAN_STATUS= 'Success'
                            }    

                                 //   timeout(time: 10, unit: 'MINUTES') {
                                  //      waitForQualityGate abortPipeline: true
                                   // }
                        }
                    }

                    post {
                        failure {
                            script {
        		            RUN_SECURITY_SCAN_STATUS= 'Failed'
                                sh '''
                                curl -XPOST -H "Authorization: token $GITHUB_TOKEN" $GITHUB_API_URL/$REPO_OWNER_NAME/$REPO_NAME/statuses$(git rev-parse HEAD) -d '{"state": "success","context":"ci/jenkins: run-security-scanning", "target_url": "https://jenkins.fedgovcloud.us/blue/organizations/jenkins/Service_Template_Multibranch/branches/','description": "Your tests failed on Jenkins!"}'
                                '''				       
        	   	                sh 'echo "FAILED in stage SonarQube"'
    		                }
                        }
                    }	
                }


                                
                
                    
            }
        }
    }

    
    post{
    success {
	    echo "Checkout Status ${CHECKOUT_STATUS}"     
	    echo "RUN_SECURITY_SCANNING_STATUS  ${RUN_SECURITY_SCAN_STATUS}"  
	    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL} on ${env.BUILD_URL}"	    
	    echo "JENKINS HOME ${env.JENKINS_HOME}"
	    echo "Job Success"
	    script
	    {
	    	env.LCHECKOUT_STATUS = "${CHECKOUT_STATUS}"
		    
		env.LRUN_SECURITY_SCAN_STATUS = "${RUN_SECURITY_SCAN_STATUS}"		    
		    env.LDEPLOY_STATUS = "${DEPLOY_STATUS}"		    
		    env.LGIT_BRANCH = "${GIT_BRANCH}"		        
		    env.LGITHUB_PROJECT_NAME = "${GITHUB_PROJECT_NAME}"		      		        
		    env.BLUE_OCEAN_URL="${env.JENKINS_URL}/blue/organizations/jenkins/Service_Template/activity/${GIT_BRANCH}/${BUILD_NUMBER}/pipeline"	    	    	    		    
		    env.BLUE_OCEAN_URL_SQ_DOCX="${env.BUILD_URL}artifact/sonarqubereports/sonarqubeanalysisreport.docx"	    
		    env.BLUE_OCEAN_URL_SQ_XLSX="${env.BUILD_URL}artifact/sonarqubereports/sonarqubeissuesreport.xlsx"	    
		    env.LSONARQUBE_URL="${SONARQUBE_URL}"
		    echo "${env.BLUE_OCEAN_URL}"        
		    echo "${env.BLUE_OCEAN_URL_SQ_DOCX}"    
		    echo "${env.BLUE_OCEAN_URL_SQ_XLSX}"    
		    echo "${GIT_BRANCH}"		
      	emailext attachLog: false, attachmentsPattern: '', body: '''${SCRIPT, template="forestservice_fam.template"}''', mimeType: 'text/html', replyTo: 'builds@usda.gov', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: "${MAILING_LIST}"
	    }
        }	   	
    failure {
	    echo "Checkout Status ${CHECKOUT_STATUS}"   
	    echo "RUN_SECURITY_SCANNING_STATUS  ${RUN_SECURITY_SCAN_STATUS}"    
	    echo "DEPLOY_STATUS  ${DEPLOY_STATUS}"  
	    echo "Job Failed"  	    	    
	        script
	    {
	    	env.LCHECKOUT_STATUS = "${CHECKOUT_STATUS}"
			env.LRUN_SECURITY_SCAN_STATUS = "${RUN_SECURITY_SCAN_STATUS}"		    
		    env.LGIT_BRANCH = "${GIT_BRANCH}"
		    env.LGITHUB_PROJECT_NAME = "${GITHUB_PROJECT_NAME}"
		    env.BLUE_OCEAN_URL="${env.JENKINS_URL}/blue/organizations/jenkins/Service_Template/activity/${GIT_BRANCH}/${BUILD_NUMBER}/pipeline"	    		        	    
		    env.BLUE_OCEAN_URL_SQ_DOCX="${env.BUILD_URL}artifact/sonarqubereports/sonarqubeanalysisreport.docx"	    
		    env.BLUE_OCEAN_URL_SQ_XLSX="${env.BUILD_URL}artifact/sonarqubereports/sonarqubeissuesreport.docx"	    
		    env.LSONARQUBE_URL="${SONARQUBE_URL}"
	    emailext attachLog: false, attachmentsPattern: '', body: '''${SCRIPT, template="forestservice_fam.template"}''', mimeType: 'text/html', replyTo: 'builds@usda.gov', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: "${MAILING_LIST}"
	    }
        }	
    }

         
}
