package com.adrianbk.deployment.aws.model;

import com.adrianbk.deployment.model.config.ConfigurationValues;
import com.adrianbk.deployment.model.InfrastructureComponent;

public class CloudFormationStack implements InfrastructureComponent {
    String stackName;
    String region;

    //File or a location within the distribution

    ConfigurationValues tags;
    ConfigurationValues stackParameters;
}
