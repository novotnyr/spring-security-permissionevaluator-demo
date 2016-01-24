package sk.upjs.ics.novotnyr.sspd;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

@Component
public class ProjectPermissionEvaluator implements PermissionEvaluator {
    public static final String[] PUBLIC_PROJECTS = { "cassovia" };

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(! (targetDomainObject instanceof Project)) {
            return false;
        }
        Project project = (Project) targetDomainObject;
        return isAllowed(project);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if(! (targetId instanceof String)) {
            return false;
        }
        if(! targetType.equals("project")) {
            return false;
        }
        String codeName = (String) targetId;
        return isAllowed(codeName);
    }


    private boolean isAllowed(Project project) {
        return Arrays.asList(PUBLIC_PROJECTS).contains(project.getCodeName());
    }

    private boolean isAllowed(String projectCodeName) {
        return Arrays.asList(PUBLIC_PROJECTS).contains(projectCodeName);
    }

}
