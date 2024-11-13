package liquibase.command.copy;

import liquibase.exception.UnexpectedLiquibaseException;

import java.io.File;

public class FileSystemProjectCopier implements ProjectCopier {
    @Override
    public int getPriority(String path) {
        if (path == null) {
            return PRIORITY_NOT_APPLICABLE;
        }

        if (path.startsWith("/") || !path.contains(":")) {
            return PRIORITY_DEFAULT;
        }

        if (path.startsWith("file:") || path.matches("^[A-Za-z]:.*")) {
            return PRIORITY_DEFAULT;
        }
        return PRIORITY_NOT_APPLICABLE;
    }

    @Override
    public boolean isRemote() {
        return false;
    }

    /**
     *
     * Create the project directory if it does not exist
     * For the local file system implementation of ProjectCopier this will not
     * be a temporary directory, so the keepTempFiles flag is ignored
     *
     * @param   projectDir                         The project directory
     * @param   keepTempFiles                      *** IGNORED ***
     * @return  File
     *
     */
    @Override
    public File createWorkingStorage(String projectDir, boolean keepTempFiles) {
        File projectDirFile = new File(projectDir);
        boolean b = projectDirFile.mkdirs();
        if (! b && ! projectDirFile.exists()) {
            throw new UnexpectedLiquibaseException("Unable to create project directory '" + projectDir + "'");
        }
        return projectDirFile;
    }

    /**
     *
     * Copy files from the source to the remote location
     * This is a no-op currently for this implementation of ProjectCopier
     *
     * @param source             The source directory
     * @param target             The target directory
     * @param recursive          Flag for copying recursively
     *
     */
    @Override
    public void copy(String source, String target, boolean recursive) {
        throw new UnexpectedLiquibaseException("The command 'init copy' requires s3:// paths and cannot be used with local file system paths. Learn more at https://docs.liquibase.com/commands/init/copy.html");
    }
}