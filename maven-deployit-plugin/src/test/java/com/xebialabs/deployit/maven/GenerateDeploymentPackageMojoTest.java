package com.xebialabs.deployit.maven;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugin.testing.stubs.ArtifactStub;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.junit.Test;

import java.io.File;
import java.util.Collections;

public class GenerateDeploymentPackageMojoTest extends AbstractMojoTestCase {

    private GenerateDeploymentPackageMojo mojo;
    private DeployableArtifactItem configurationFiles;


    public void setUp() throws Exception {
        super.setUp();

        configurationFiles = new DeployableArtifactItem();
        configurationFiles.setType("ConfigurationFiles");
        configurationFiles.setLabel("ConfigurationFilesCI");
        configurationFiles.setLocation("src/main/resources");

        mojo = new GenerateDeploymentPackageMojo();
    }

    @Test
    public void testPackageOne() throws Exception {
        MavenProjectStub project = new MavenProjectStub();
        ArtifactStub mainArtifact = new ArtifactStub();
        mainArtifact.setType("War");
        mainArtifact.setArtifactId("com.test.tomcat");
        mainArtifact.setGroupId("test");
        mainArtifact.setVersion("1.0");
        mainArtifact.setFile(new File("main.war"));
        project.setArtifact(mainArtifact);

        setVariableValueToObject(mojo, "project", project);
        setVariableValueToObject(mojo, "outputDirectory", new File("target/"));
        setVariableValueToObject(mojo, "artifactId", "com.test.tomcat");
        setVariableValueToObject(mojo, "version", "1.0");
        setVariableValueToObject(mojo, "generateManifestOnly", true);
        try {
            mojo.execute();
        } finally {
            System.out.println(mojo.getScript());
        }


    }

    @Test
    public void testPackageOneWithConfigurationFiles() throws Exception {
        MavenProjectStub project = new MavenProjectStub();
        ArtifactStub mainArtifact = new ArtifactStub();
        mainArtifact.setType("War");
        mainArtifact.setArtifactId("com.test.tomcat.configurationsfiles");
        mainArtifact.setGroupId("test");
        mainArtifact.setVersion("1.0");
        mainArtifact.setFile(new File("main.war"));
        project.setArtifact(mainArtifact);

        setVariableValueToObject(mojo, "project", project);
        setVariableValueToObject(mojo, "outputDirectory", new File("target/"));
        setVariableValueToObject(mojo, "artifactId", "com.test.tomcat.configurationsfiles");
        setVariableValueToObject(mojo, "deployableArtifacts", Collections.singletonList(configurationFiles));

        setVariableValueToObject(mojo, "version", "1.0");
        setVariableValueToObject(mojo, "generateManifestOnly", true);


        try {
            mojo.execute();
        } finally {
            System.out.println(mojo.getScript());
        }


    }

}
