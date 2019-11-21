package ru.amm.fileexplorer.server.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RelativePathValidator implements ConstraintValidator<RelativePath, String> {

    private final static Logger LOG = LogManager.getLogger(RelativePathValidator.class);

    @Value("${pathToPublish}")
    private String rootDirectory;

    @Override
    public void initialize(RelativePath constraintAnnotation) {
    }

    @Override
    public boolean isValid(String relativePath, ConstraintValidatorContext context) {
        if (relativePath == null)
            return true;

        Path absolutePath = Paths.get(rootDirectory, relativePath).normalize();

        return absolutePath.startsWith(rootDirectory);
    }
}
