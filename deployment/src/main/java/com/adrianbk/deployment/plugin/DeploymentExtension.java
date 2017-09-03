package com.adrianbk.deployment.plugin;

import com.adrianbk.deployment.model.Application;

import java.util.List;

public class DeploymentExtension {
    private List<Application> applications;
    private String url;

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DeploymentExtension{" +
                "applications=" + applications +
                '}';
    }
}
