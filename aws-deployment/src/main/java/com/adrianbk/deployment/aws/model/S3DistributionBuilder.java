package com.adrianbk.deployment.aws.model;

import com.adrianbk.deployment.version.VersionProvider;

public final class S3DistributionBuilder {
    private final String bucket;
    private final String key;
    private S3Distribution.Format format;
    private VersionProvider versionProvider;

    private S3DistributionBuilder(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public static S3DistributionBuilder create3Distribution(String bucket, String key) {
        return new S3DistributionBuilder(bucket, key);
    }

    public S3DistributionBuilder format(S3Distribution.Format format) {
        this.format = format;
        return this;
    }

    public S3DistributionBuilder versionProvider(VersionProvider versionProvider) {
        this.versionProvider = versionProvider;
        return this;
    }

    public S3Distribution build() {
        return new S3Distribution(bucket, key, format, versionProvider);
    }
}
