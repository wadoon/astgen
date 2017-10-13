package edu.kit.iti.formal.astgen;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        name = "generate")
public class MyMojo extends AbstractMojo {
    @Parameter(property = "project.build.directory", required = false)
    private File outputDirectory;

    @Parameter(defaultValue = "src/main/astgen/")
    private File hierarchyDescription = new File("..");

    public void execute() throws MojoExecutionException {
        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdirs();
        }

        File touch = new File(f, "touch.txt");

        FileWriter w = null;
        try {
            w = new FileWriter(touch);

            w.write("touch.txt");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public MyMojo setHierarchyDescription(File hierarchyDescription) {
        this.hierarchyDescription = hierarchyDescription;
        return this;
    }
}
