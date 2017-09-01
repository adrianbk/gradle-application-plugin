package com.adrianbk.deployment.aws.model;

import com.adrianbk.deployment.model.Distribution;
import com.adrianbk.deployment.version.VersionProvider;

public class S3Distribution implements Distribution {
    private final String bucket;
    private final String key;
    private final Format format;
    private final VersionProvider versionProvider;

    S3Distribution(String bucket, String key, Format format, VersionProvider versionProvider) {
        this.bucket = bucket;
        this.key = key;
        this.format = format;
        this.versionProvider = versionProvider;
    }

    @Override
    public String getVersion() {
        return versionProvider.get();
    }

    enum Format {
        EXPANDED, COMPRESSED
    }
}
