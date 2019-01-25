import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import com.cloudbees.jenkins.plugins.sshslaves.verification.*
import com.cloudbees.jenkins.plugins.sshslaves.SSHConnectionDetails

// e.g. addSshAgent('name', '10.1.1.2', 'credential id', 'label')
def call(String agentName, String ipAddress, String credentialsId, String labelString) {

    ServerKeyVerificationStrategy serverKeyVerificationStrategy = new TrustInitialConnectionVerificationStrategy(false)

    ComputerLauncher launcher = new com.cloudbees.jenkins.plugins.sshslaves.SSHLauncher(
        ipAddress, // Host
        new SSHConnectionDetails(
                credentialsId,
                22, // port
                (String)null, // JavaPath
                (String)null, // JVM Options
                (String)null, // Prefix Start Slave Command
                (String)null, // Suffix Start Slave Command
                (boolean)false, // Log environment on initial connect
                (ServerKeyVerificationStrategy) serverKeyVerificationStrategy // Host Key Verification Strategy
        )
    )

    Slave agent = new DumbSlave(agentName, '/Users/jenkins', launcher)
    agent.nodeDescription = agentName
    agent.numExecutors = 1
    agent.labelString = "macos"
    agent.retentionStrategy = new RetentionStrategy.Always()

    // TODO: try-catch here?
    Jenkins.instance.addNode(agent)
}

